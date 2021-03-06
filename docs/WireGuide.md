---
title: Wire Guide
nav_order: 5
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

Wire Guide
==========

Inside of a BarrageRecordBatch there are three different wire types that you
will need to become familiar with. Additionally, setting a viewport requires
that you communicate which position-space you want to receive updates to.

Index Wire Format
-----------------

An Index is serialized as a series of commands. Each command is one-byte
split into a 5-bit (high) value and a 3-bit (low) value.

Possible Command Types (most significant 5 bits):
```
OFFSET      = 1;
SHORT_ARRAY = 2;
BYTE_ARRAY  = 3;
END         = 4;
```

Possible Value Types (low 3 bits):
```
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
reconstruct an Index. Since an Index is an ordered set, all of the values that
we insert, should always be increasing and thus positive. The algorithm then
uses the sign to encode a single value (a positive value) vs a rangle
(a positive value followed by a negative value).

To reconstruct the Index run the parsed values through this pseudo code:
```
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

IndexShiftData Wire Format
--------------------------

Hopefully you were able to follow the previous section. `IndexShiftData`'s
binary encoding is three `Index` encodings without any padding in-between.

The three Indexes are `starts`, `ends` and `dests`. Each Index will have the
same length. Let `s_i = starts[i]`, `e_i = ends[i]`, `d_i = dests[i]`, then this
triplet represents the notification that all data in keyspace `[s_i, e_i]` (inclusive)
moved to `[d_i, d_i + e_i - s_i]`. Note that not all rows are required to exist
within the shift; it is highly recommended to intersect each shift with the
state that you have on hand to avoid iterating through the entire range.

BitSet Wire Format
------------------

The bitset is represented in little-endian byte-ordered bits. Assume
that all omitted bits are zero.

