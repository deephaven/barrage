---
title: Concepts
nav_order: 3
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

Concepts
========

Key Space
---------

To effectively communicate changes to rows of a table, we need a way to
consistently and uniquely identify them. Additionally, our operations
often depend on input ordering or output ordering. Thus, we map rows for
a given table to the range `[0, Long.MAX_VALUE]`. Adjacent rows are not
guaranteed to be adjacent in key-space. In fact, this mapping can change
without any of the data in the row changing.

Position Space
--------------

If a table has `n` rows then it has a position space of `[0, n)`. This is
an alternative naming for the rows in the table. However, which row is the
ith row depends on the context and current state of the table. The function
`i => key_i` for i in `[0, n)` maps from positon space to key space.

Viewport
--------

Depending on the final destination and use of the data, the developer may
not necessarily want to have all of the data at once. If, for example, the
data is destined for a human-facing widget then you may only need a slice
of the data. We call these slices viewports.

Viewports are in position space because:
- difficult to synchronize with the server as the keyspace may vary tick to tick
- the api more closely resembles a pagination over `n` records

Index
-----

An Index is an ordered set of rows and may represent either the table's key-space
or the table's position-space.

Indexes have a variety of uses:
- describe which rows exist in a table (key-space)
- describe which rows were added/removed/modified in a table (key-space)
- describe a viewport (position-space)

IndexShiftData
--------------

Some operations need to be able to rearrange the keyspace to accommodate
changes in data. For example, a sort may need to move rows that are adjancent in
keyspace out of the way to accommodate a newly inserting row that sorts
directly between them. When rows change in keyspace, we communicate these
changes independently from additions, removals, and modifications.

We call a single transformation a `shift`. A shift is defined by a `start`, `end`,
and `delta`. All rows in the range `[start, end]` (inclusive) change in key-space by
`delta` and are now located  at `[start + delta, end + delta]`.

Things get pretty tricky quickly, for practical reasons, there are a few restrictions:
- shift origins are not allowed to overlap in pre-shift keyspace
- shift destinations are not allowed to overlap in post-shift keyspace
- shifts are unable to alter the ordering of rows in position space
- positive deltas must be applied in high-to-low keyspace order
- negative deltas must be applied in low-to-high keyspace order

Update Model
------------

In pseudo-code, an update (roughly) looks like:

```
class Update {
  Index added;  // post shift key-space
  Index removed; // pre shift key-space
  IndexShiftData shifted;
  Index[] modifiedColumns; // post shift key-space
}
```

To properly apply an update you must take care to apply it in order:
1. remove data for all rows that were removed on this update
2. apply the index shift data to your current state; your state is now in post-shift keyspace
  - note: often, positive deltas must be applied in highest to lowest order to avoid losing state
  - note: often, negative deltas must be applied in lowest to highest order to avoid losing state
3. add data for all rows that were added on this update
4. apply any modification that affects your state

Modified columns are independent of each other and may
have different sets of modified rows.

Snapshot
--------

New subscriptions, and subscription changes, warrant a refresh of data that
is in the view of their subscription. If the subscription is made without
a viewport, it is assumed to be a full table subscription. Due to the
asynchronous nature of table subscription, not every subscription change request
is required to be responded to. Instead, the message will be marked as being
a snapshot.

Snapshots:
- do not remove rows
- do not modify rows
- do not shift rows
- the server may omit data the client has from an existing subscription
- include the acknowledged viewport and subscribed column set

Scoped Rows
-----------

Since viewports are in position-space, we need to be able to communicate data that
shifts into view but is otherwise not new.

Imagine the scenario:
1. Let `T` be our imaginary table.
2. Let `C` be a viewport client of `T` subscribed to position space `[100, 199]`.
3. `T` ticks and removes rows in position space `[0, 19]`.
4. The client now has data in position space `[80, 179]`; the server must send the missing `[180, 199]`.

We call these rows `scoped` rows. They are integrated with `added` rows in an update,
but are distinguishable via the `addedRowsIncluded` field. See the [Wire Guide](WireGuide.md)
for more details.
