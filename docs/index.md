---
title: Barrage
slug: /docs
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

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { vsGithubInverted } from '@deephaven/icons';

<div className="comment-title">

Barrage is an extension of Apache Arrow Flight, with a particular focus on incrementally updating data sets.

</div>

We at Deephaven believe that it is Arrow's and similar projects' drive  for efficient standards that enables a lot of 
the innovation we see in data science.

We want to share the way we see data at Deephaven.  We believe that live use cases are better served, both in 
latency and in throughput, by an iterative update model rather than a periodic refresh.

Barrage introduces flatbuffer metadata types that are sent to/from a Barrage server via FlightData's metadata. The 
`BarrageMessageWrapper` is the expected outer type, but is intended to be a cheap model that is sensitive to custom
user needs. The `BarrageUpdateMetadata` is the inner-type that contains the information
needed to apply the update model. See [concepts](./concepts.md) for more information on the update model.

<a className="button button--success" href="https://github.com/deephaven/barrage"><FontAwesomeIcon icon={vsGithubInverted} /> Barrage Github Repo</a>
