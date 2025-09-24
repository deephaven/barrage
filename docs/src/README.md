---
title: Barrage
slug: /docs
---

Test

<div className="comment-title">

Barrage is an extension of Apache Arrow Flight, with a particular focus on incrementally updating data sets.

</div>

We at Deephaven believe that it is Arrow's and similar projects' drive for efficient standards that enables a lot of
the innovation we see in data science.

We want to share the way we see data at Deephaven. We believe that live use cases are better served, both in
latency and in throughput, by an iterative update model rather than a periodic refresh.

Barrage introduces flatbuffer metadata types that are sent to/from a Barrage server via FlightData's metadata. The
`BarrageMessageWrapper` is the expected outer type, but is intended to be a cheap model that is sensitive to custom
user needs. The `BarrageUpdateMetadata` is the inner-type that contains the information
needed to apply the update model. See [concepts](./concepts.md) for more information on the update model.

<a className="button button--success" href="https://github.com/deephaven/barrage">Barrage Github Repo</a>
