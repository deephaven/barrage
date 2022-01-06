---
title: Wire Guide
---

<!---
  Copyright 2020 Deephaven Data Labs

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->

There are three different wire types that you will need to become familiar with to parse and process a 
`BarrageUpdateMetadata`. You will also need to know how to write your own  Row Set / Index to set a viewport.

## Row Set Wire Format

A Row Set is serialized as a series of commands. Each command is one-byte
split into a 5-bit (high) value and a 3-bit (low) value.

:::note

[Deephaven Enterprise](https://deephaven.io/enterprise/) uses the term `Index` instead of `Row Set` to refer to this concept.

:::

Possible Command Types (most significant 5 bits):

```java
OFFSET      = 1;
SHORT_ARRAY = 2;
BYTE_ARRAY  = 3;
END         = 4;
```

Possible Value Types (low 3 bits):

```java
SHORT_VALUE = 1; // 2 bytes
INT_VALUE   = 2; // 4 bytes
LONG_VALUE  = 3; // 8 bytes
BYTE_VALUE  = 4; // 1 byte
```

To parse, continue reading until receiving a command type of `END`. Immediately
following a non-end command is the little-endian-encoded value with length
denoted by provided value type. If command type is byte array or short array,
then the value is the number of elements that follow. These elements are either
shorts or bytes depending on the command type. The offset command is a single
value (then followed by the next command).

The series of values parsed from the previous paragraph can be used to
reconstruct a Row Set. Since a Row Set is an ordered set, all of the values that
we insert, should always be increasing and thus positive. The algorithm then
uses the sign to encode a single value (a positive value) vs a rangle
(a positive value followed by a negative value).

To reconstruct the Row Set run the parsed values through this pseudo code:

```java
long pending = -1;
long lastValue = 0;
void consume(long nextOffset) {
    if (nextOffset < 0) {
        assert(pending != -1);
        lastValue = lastValue - nextOffset;
        addRowsInRange(pending, lastValue);
        pending = -1;
    } else {
        if (pending != -1) {
            addRowAt(pending);
        }
        lastValue = pending = lastValue + nextOffset;
    }
}
```

## IndexShiftData Wire Format

Hopefully you were able to follow the previous section. `IndexShiftData`'s
binary encoding is three `Row Set` encodings without any padding in-between.

The three Row Sets are `starts`, `ends` and `dests`. Each Row Set will have the
same length. Let `s_i = starts[i]`, `e_i = ends[i]`, `d_i = dests[i]`, then this
triplet represents the notification that all data in keyspace `[s_i, e_i]` (inclusive)
moved to `[d_i, d_i + e_i - s_i]`. 

:::note 

Note that not all rows are required to exist within the shift; it is recommended to avoid iterating through 
the entire range. See [IndexShiftData](https://github.com/deephaven/deephaven-core/blob/main/DB/src/main/java/io/deephaven/db/v2/utils/IndexShiftData.java)
for insipration.

:::

## BitSet Wire Format

The bitset is represented in little-endian byte-ordered bits. Assume
that all omitted bits are zero.
