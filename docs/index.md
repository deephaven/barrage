---
title: Barrage
nav_order: 1
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

Barrage
=======

Barrage is an extension of Apache Arrow Flight, with a particular focus on
intermediary data sets that change over time.

We at Deephaven believe that it is Arrow's, and similar projects', drive
for efficient standards, that enables a lot of the innovation we see in
data science.

The way we see data at Deephaven doesn't quite fit into the model that
Arrow Flight proposes; our tables change over time. We believe that live
use cases are better served (in latency and throughput) by an iterative
update model rather than a periodic refresh. See [Motivation](Motivation.md)
for more information.

Barrage introduces a new ArrowMessage header type, the `BarrageRecordBatch`.
Inside contains all of the information needed to apply the update model. See
[Concepts](Concepts.md) for more information on the update model.
