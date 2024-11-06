/*
 * Copyright 2020-2024 Deephaven Data Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.deephaven.barrage.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.BooleanVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.DoubleVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.ShortVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Struct;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.UnionVector;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class BarrageSubscriptionOptions extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarrageSubscriptionOptions getRootAsBarrageSubscriptionOptions(ByteBuffer _bb) { return getRootAsBarrageSubscriptionOptions(_bb, new BarrageSubscriptionOptions()); }
  public static BarrageSubscriptionOptions getRootAsBarrageSubscriptionOptions(ByteBuffer _bb, BarrageSubscriptionOptions obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarrageSubscriptionOptions __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * Deephaven reserves a value in the range of primitives as a custom NULL value. This enables more efficient transmission
   * by eliminating the additional complexity of the validity buffer.
   */
  public boolean useDeephavenNulls() { int o = __offset(6); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * Explicitly set the update interval for this subscription. Note that subscriptions with different update intervals
   * cannot share intermediary state with other subscriptions and greatly increases the footprint of the non-conforming subscription.
   *
   * Note: if not supplied (default of zero) then the server uses a consistent value to be efficient and fair to all clients
   */
  public int minUpdateIntervalMs() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  /**
   * Specify a preferred batch size. Server is allowed to be configured to restrict possible values. Too small of a
   * batch size may be dominated with header costs as each batch is wrapped into a separate RecordBatch. Too large of
   * a payload and it may not fit within the maximum payload size. A good default might be 4096.
   *
   * a batch_size of -1 indicates that the server should avoid batching a single logical message
   */
  public int batchSize() { int o = __offset(10); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  /**
   * Specify a maximum allowed message size. Server will enforce this limit by reducing batch size (to a lower limit
   * of one row per batch). If the message size limit cannot be met due to large row sizes, the server will throw a
   * `Status.RESOURCE_EXHAUSTED` exception
   */
  public int maxMessageSize() { int o = __offset(12); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  /**
   * If true, the server will wrap columns with a list. This is useful for clients that do not support modified batches
   * with columns of differing lengths.
   */
  public boolean columnsAsList() { int o = __offset(14); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * If zero, list lengths will not be limited. If greater than zero, the server will limit the length of any encoded
   * list / array to the first n elements, where n is the specified value. If the column value has length less than
   * zero, the server will send the last n elements of the list / array. If the column value has length greater than
   * the limit, the server will encode the list up to the limit and append an arbitrary value to indicate truncation.
   */
  public int previewListLengthLimit() { int o = __offset(16); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createBarrageSubscriptionOptions(FlatBufferBuilder builder,
      boolean useDeephavenNulls,
      int minUpdateIntervalMs,
      int batchSize,
      int maxMessageSize,
      boolean columnsAsList,
      int previewListLengthLimit) {
    builder.startTable(7);
    BarrageSubscriptionOptions.addPreviewListLengthLimit(builder, previewListLengthLimit);
    BarrageSubscriptionOptions.addMaxMessageSize(builder, maxMessageSize);
    BarrageSubscriptionOptions.addBatchSize(builder, batchSize);
    BarrageSubscriptionOptions.addMinUpdateIntervalMs(builder, minUpdateIntervalMs);
    BarrageSubscriptionOptions.addColumnsAsList(builder, columnsAsList);
    BarrageSubscriptionOptions.addUseDeephavenNulls(builder, useDeephavenNulls);
    return BarrageSubscriptionOptions.endBarrageSubscriptionOptions(builder);
  }

  public static void startBarrageSubscriptionOptions(FlatBufferBuilder builder) { builder.startTable(7); }
  public static void addUseDeephavenNulls(FlatBufferBuilder builder, boolean useDeephavenNulls) { builder.addBoolean(1, useDeephavenNulls, false); }
  public static void addMinUpdateIntervalMs(FlatBufferBuilder builder, int minUpdateIntervalMs) { builder.addInt(2, minUpdateIntervalMs, 0); }
  public static void addBatchSize(FlatBufferBuilder builder, int batchSize) { builder.addInt(3, batchSize, 0); }
  public static void addMaxMessageSize(FlatBufferBuilder builder, int maxMessageSize) { builder.addInt(4, maxMessageSize, 0); }
  public static void addColumnsAsList(FlatBufferBuilder builder, boolean columnsAsList) { builder.addBoolean(5, columnsAsList, false); }
  public static void addPreviewListLengthLimit(FlatBufferBuilder builder, int previewListLengthLimit) { builder.addInt(6, previewListLengthLimit, 0); }
  public static int endBarrageSubscriptionOptions(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarrageSubscriptionOptions get(int j) { return get(new BarrageSubscriptionOptions(), j); }
    public BarrageSubscriptionOptions get(BarrageSubscriptionOptions obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

