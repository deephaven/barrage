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

/**
 * Describes the snapshot the client would like to acquire.
 */
@SuppressWarnings("unused")
public final class BarrageSnapshotRequest extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarrageSnapshotRequest getRootAsBarrageSnapshotRequest(ByteBuffer _bb) { return getRootAsBarrageSnapshotRequest(_bb, new BarrageSnapshotRequest()); }
  public static BarrageSnapshotRequest getRootAsBarrageSnapshotRequest(ByteBuffer _bb, BarrageSnapshotRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarrageSnapshotRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * Ticket for the source data set.
   */
  public byte ticket(int j) { int o = __offset(4); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int ticketLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector ticketVector() { return ticketVector(new ByteVector()); }
  public ByteVector ticketVector(ByteVector obj) { int o = __offset(4); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer ticketAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer ticketInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  /**
   * The bitset of columns to request. If not provided then all columns are requested.
   */
  public byte columns(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int columnsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector columnsVector() { return columnsVector(new ByteVector()); }
  public ByteVector columnsVector(ByteVector obj) { int o = __offset(6); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer columnsAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer columnsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  /**
   * This is an encoded and compressed RowSet in position-space to subscribe to. If not provided then the entire
   * table is requested.
   */
  public byte viewport(int j) { int o = __offset(8); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int viewportLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector viewportVector() { return viewportVector(new ByteVector()); }
  public ByteVector viewportVector(ByteVector obj) { int o = __offset(8); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer viewportAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer viewportInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }
  /**
   * Options to configure your subscription.
   */
  public io.deephaven.barrage.flatbuf.BarrageSnapshotOptions snapshotOptions() { return snapshotOptions(new io.deephaven.barrage.flatbuf.BarrageSnapshotOptions()); }
  public io.deephaven.barrage.flatbuf.BarrageSnapshotOptions snapshotOptions(io.deephaven.barrage.flatbuf.BarrageSnapshotOptions obj) { int o = __offset(10); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  /**
   * When this is set the viewport RowSet will be inverted against the length of the table. That is to say
   * every position value is converted from `i` to `n - i - 1` if the table has `n` rows.
   */
  public boolean reverseViewport() { int o = __offset(12); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createBarrageSnapshotRequest(FlatBufferBuilder builder,
      int ticketOffset,
      int columnsOffset,
      int viewportOffset,
      int snapshotOptionsOffset,
      boolean reverseViewport) {
    builder.startTable(5);
    BarrageSnapshotRequest.addSnapshotOptions(builder, snapshotOptionsOffset);
    BarrageSnapshotRequest.addViewport(builder, viewportOffset);
    BarrageSnapshotRequest.addColumns(builder, columnsOffset);
    BarrageSnapshotRequest.addTicket(builder, ticketOffset);
    BarrageSnapshotRequest.addReverseViewport(builder, reverseViewport);
    return BarrageSnapshotRequest.endBarrageSnapshotRequest(builder);
  }

  public static void startBarrageSnapshotRequest(FlatBufferBuilder builder) { builder.startTable(5); }
  public static void addTicket(FlatBufferBuilder builder, int ticketOffset) { builder.addOffset(0, ticketOffset, 0); }
  public static int createTicketVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createTicketVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startTicketVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addColumns(FlatBufferBuilder builder, int columnsOffset) { builder.addOffset(1, columnsOffset, 0); }
  public static int createColumnsVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createColumnsVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startColumnsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addViewport(FlatBufferBuilder builder, int viewportOffset) { builder.addOffset(2, viewportOffset, 0); }
  public static int createViewportVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createViewportVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startViewportVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addSnapshotOptions(FlatBufferBuilder builder, int snapshotOptionsOffset) { builder.addOffset(3, snapshotOptionsOffset, 0); }
  public static void addReverseViewport(FlatBufferBuilder builder, boolean reverseViewport) { builder.addBoolean(4, reverseViewport, false); }
  public static int endBarrageSnapshotRequest(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarrageSnapshotRequest get(int j) { return get(new BarrageSnapshotRequest(), j); }
    public BarrageSnapshotRequest get(BarrageSnapshotRequest obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

