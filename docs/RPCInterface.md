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

Barrage is an RPC interface for high-performance data services based on Arrow,
for ticking data sets built on top of gRPC.

Barrage is an extension of Apache Arrow Flight. The extension works by sending
additional metadata via the `app_metadata` field on `FlightData`. This metadata
is used to communicate the necessary additional information between server
and client. These types are flatbuffers, so that we may more easily lift the
`app_metadata` into the `RecordBatch` flatbuffer once Arrow supports byte-array
metadata, at that layer.

The main subscription mechanism is initiated via a `DoExchange`. The client
sends a SubscriptionRequest (or as many as they like) and the server sends
barrage updates to satisfy their subscription's requirements.


Flat Buffer Definitions
-----------------------

```fbs
// Copyright 2020 Deephaven Data Labs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

namespace io.deephaven.barrage.flatbuf;

enum BarrageMessageType : byte {
  None = 0,

  // for session management
  NewSessionRequest = 1,
  RefreshSessionRequest = 2,
  SessionInfoResponse = 3,

  // for subscription parsing/management
  BarrageSubscriptionRequest = 4,
  BarrageUpdateMetadata = 5,
}

/// The message wrapper used for all barrage app_metadata fields.
table BarrageMessageWrapper {
  /// Used to identify this type of app_metadata vs other applications.
  /// The magic value is '0x6E687064'. It is the numerical representation of the ASCII "dhvn".
  magic: uint;

  /// The msg type being sent.
  msg_type: BarrageMessageType;

  /// The msg payload.
  msg_payload: [byte];

  /// Used in client-side requests to tie this message to an export id so that it can be referenced in future out-of-band methods.
  /// Not required if using a client-streaming rpc.
  rpc_ticket: [byte];

  /// Used in client-side requests to sequence this message w.r.t. other messages sent to the same rpcTicket. Not required if using a client-streaming rpc.
  sequence: long;
}

/// Establish a new session.
table NewSessionRequest {
  /// empty for future compatibility
}

/// Refresh the provided session.
table RefreshSessionRequest {
  /// this session token is only required if it is the first request of an rpc stream
  session: [byte];
}

/// Information about the current session state.
table SessionInfoResponse {
  /// this is the metadata header to identify this session with future requests; it must be lower-case and remain static for the life of the session
  metadata_header: [byte];

  /// this is the session_token; note that it may rotate
  session_token: [byte];

  /// a suggested time for the user to refresh the session if they do not do so earlier; value is denoted in milliseconds since epoch
  token_refresh_deadline_ms: long;
}

/// Describes the subscription the client would like to acquire.
table BarrageSubscriptionRequest {
  /// Ticket for the source data set.
  ticket: [byte];

  /// The bitset of columns to subscribe to. An empty bitset unsubscribes from all columns.
  columns: [byte];

  /// This is an encoded Index of rows in position-space to subscribe to.
  viewport: [byte];

  /// Explicitly set the update interval for this subscription. Note that subscriptions with different update intervals
  /// cannot share intermediary state with other subscriptions and greatly increases the footprint of the non-conforming subscription.
  update_interval_ms: int64;

  /// Ticket to export this subscription request for out-of-band updates.
  export_ticket: [byte];

  /// Deephaven reserves a value in the range of primitives as a custom NULL value. This enables more efficient transmission
  /// by eliminating the additional complexity of the validity buffer.
  use_deephaven_nulls: bool;
}

/// Holds all of the index data structures for the column being modified.
table BarrageModColumnMetadata {
  /// This is an encoded Index of rows for this column (within the viewport) that were modified.
  /// There is no notification for modifications outside of the viewport.
  modified_rows: [byte];
}

/// A data header describing the shared memory layout of a "record" or "row"
/// batch for a ticking barrage table.
table BarrageUpdateMetadata {
  /// The number of record batches that describe rows added (may be zero).
  num_add_batches: ushort;

  /// The number of record batches that describe rows modified (may be zero).
  num_mod_batches: ushort;

  /// This batch is generated from an upstream table that ticks independently of the stream. If
  /// multiple events are coalesced into one update, the server may communicate that here for
  /// informational purposes.
  first_seq: long;
  last_seq: long;

  /// Indicates if this message was sent due to upstream ticks or due to a subscription change.
  is_snapshot: bool;

  /// If this is a snapshot and the subscription is a viewport, then the effectively subscribed viewport
  /// will be included in the payload. It is an encoded Index.
  effective_viewport: [byte];

  /// If this is a snapshot, then the effectively subscribed column set will be included in the payload.
  effective_column_set: [byte];

  /// This is an encoded Index of rows that were added in this update.
  added_rows: [byte];

  /// This is an encoded Index of rows that were removed in this update.
  removed_rows: [byte];

  /// This is an encoded IndexShiftData describing how the keyspace of unmodified rows changed.
  shift_data: [byte];

  /// This is an encoded Index of rows that were included with this update.
  /// (the server may include rows not in addedRows if this is a viewport subscription to refresh
  ///  unmodified rows that were scoped into view)
  added_rows_included: [byte];

  /// The list of modified column data are in the same order as the field nodes on the schema.
  nodes: [BarrageModColumnMetadata];
}
```
