---
title: RPC Interface
nav_order: 4
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

RPC Interface
=============

Barrage is an RPC framework for high-performance data services based on Arrow data,
for ticking data sets built on top of gRPC.

Barrage is an extension of Apache Arrow Flight. The extension works by sending
`BarrageRecordBatch` messages instead of `RecordBatch` messages. Structurally, the
messages look very similar, but a BarrageRecordBatch requires a series of steps to
be performed to ensure that local state is updated appropriately to reflect the
remote table.

`DoSubscribe` is a new BarrageService rpc. It is similar to `DoGet` in that it is
how a client gets access to the data in the table, but instead of only receiving the
set of existing data a barrage subscription will also is also receive future updates.


Protocol Buffer Definitions
---------------------------

```proto
/*
 * A barrage service is an endpoint for retrieving or storing ticking Arrow data.
 * Implementations should also implement FlightService.
 */
service BarrageService {

  /*
   * Create a table subscription. You can send a new request to update the subscription.
   */
  rpc DoSubscribe(stream SubscriptionRequest) returns (stream BarrageData) {}

}
*/

/*
 * An opaque identifier that the service can use to retrieve a particular
 * portion of a stream.
 */
message Ticket {
  bytes ticket = 1;
}

/*
 * A batch of Arrow data as part of a stream of batches.
 */
message BarrageData {

  /*
   * The descriptor of the data. This is only relevant when a client is
   * starting a new DoPut stream.
   */
  BarrageDescriptor barrage_descriptor = 1;

  /*
   * Header for message data as described in Message.fbs::Message.
   */
  bytes data_header = 2;

  /*
   * Application-defined metadata.
   */
  bytes app_metadata = 3;

  /*
   * The actual batch of Arrow data. Preferably handled with minimal-copies
   * coming last in the definition to help with sidecar patterns (it is
   * expected that some implementations will fetch this field off the wire
   * with specialized code to avoid extra memory copies).
   */
  bytes data_body = 1000;
}

message SubscriptionRequest {
  // The ticket identifying this data set.
  Ticket ticket = 1;

  // A bitset of columns to subscribe to. An empty bitset unsubscribes from everything.
  bytes columns = 2;

  // The viewport in position-space of rows to subscribe to. An empty viewport empties the viewport. You will only
  // receive index updates.
  bytes viewport = 3;

  // Use field ID >= 20 for any custom subscription parameters.
}

/*
 * The name or tag for a Flight. May be used as a way to retrieve or generate
 * a flight or be used to expose a set of previously defined flights.
 */
message BarrageDescriptor {

  /*
   * Describes what type of descriptor is defined.
   */
  enum DescriptorType {

    // Protobuf pattern, not used.
    UNKNOWN = 0;

    /*
     * A named path that identifies a dataset. A path is composed of a string
     * or list of strings describing a particular dataset. This is conceptually
     *  similar to a path inside a filesystem.
     */
    PATH = 1;

    /*
     * An opaque command to generate a dataset.
     */
    CMD = 2;
  }

  DescriptorType type = 1;

  /*
   * Opaque value used to express a command. Should only be defined when
   * type = CMD.
   */
  bytes cmd = 2;

  /*
   * List of strings identifying a particular dataset. Should only be defined
   * when type = PATH.
   */
  repeated string path = 3;
}
```

Flat Buffer Definitions
-----------------------

```fbs
/// ----------------------------------------------------------------------
/// Data structures for describing a barrage row batch (an update to a ticking table).

/// Metadata about a field at some level of a nested type tree (but not
/// its children).
///
/// This is similar to arrow's FieldNode, with additional fields to support modified rows.
table BarrageFieldNode {
  /// The number of value slots in the Arrow array at this level of a nested
  /// tree.
  length: long;

  /// The number of observed nulls. Fields with null_count == 0 may choose not
  /// to write their physical validity bitmap out as a materialized buffer,
  /// instead setting the length of the bitmap buffer to 0.
  null_count: long;

  /// If this field node is for an outer-most modified column then these fields may be included:

  /// This is an encoded Index of rows for this column that were modified:
  modifiedRows: [byte];

  /// If this is a viewport, this is an encoded Index of rows for this column that were included in the buffer.
  includedRows: [byte];
}

/// A data header describing the shared memory layout of a "record" or "row"
/// batch for a ticking barrage table.
table BarrageRecordBatch {

  /// This batch is generated from an upstream table that ticks independently of the stream. If
  /// multiple events are coalesced into one update, the server may communicate that here for
  /// informational purposes.
  firstSeq: long;
  lastSeq: long;

  /// indicates if this message was sent due to upstream ticks or due to a subscription change
  isSnapshot: bool;

  /// If this is a snapshot and the subscription is a viewport, then the effectively subscribed viewport
  /// will be included in the payload. It is an encoded Index.
  effectiveViewport: [byte];

  /// If this is a snapshot, then the effectively subscribed column set will be included in the payload.
  effectiveColumnSet: [byte];

  /// This is an encoded Index of rows that were added in this update.
  addedRows: [byte];

  /// This is an encoded Index of rows that were removed in this update.
  removedRows: [byte];

  /// This is an encoded IndexShiftData describing how the keyspace of unmodified rows changed.
  shiftData: [byte];

  /// This is an encoded bitset of added columns that are included in this update; it will match the most
  /// recently received effectiveColumnSet.
  addedColumnSet: [byte];

  /// This is an encoded bitset of modified columns that are included in this update. If this is a
  /// snapshot, then this bitset will be empty.
  modifiedColumnSet: [byte];

  /// This is an encoded Index of rows that were included with this update
  /// (the server may include rows not in addedRows if this is a viewport subscription to refresh
  ///  unmodified rows that were scoped into view)
  addedRowsIncluded: [byte];

  /// The list of nodes are first any added columns (if addedRows is nonempty), followed by any modified
  /// columns as indicated by modifiedColumnSet.
  nodes: [BarrageFieldNode];

  /// Buffers correspond to the pre-ordered flattened buffer tree
  ///
  /// The number of buffers appended to this list depends on the schema and which nodes were included in the update.
  /// Refer to Arrow Flight's specification for buffer schema.
  buffers: [org.apache.arrow.flatbuf.Buffer];

  /// Optional compression of the message body
  compression: org.apache.arrow.flatbuf.BodyCompression;
}
```
