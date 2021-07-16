---
title: Motivation
nav_order: 2
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

Motivation
==========

At Deephaven, we believe that iterative updating of intermediary datasets
provides superior performance and lower latencies when compared to an
otherwise identical periodically refreshing of that computation. We also
believe that open standards help drive innovation in data science.

Problems we need to solve that are not already solved by Flight:
- provide some way to identify which rows were removed or modified
- provide cross section of columns and rows that were modified in addition to data in added rows

We admire Arrow's goals, and would like to encourage the data science ecosystem
to continue to innovate together. This is why Barrage, like Flight, aims
to define a minimal-copy, zero-translation, IPC protocol.
