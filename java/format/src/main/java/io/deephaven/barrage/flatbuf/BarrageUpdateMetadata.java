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
 * A data header describing the shared memory layout of a "record" or "row"
 * batch for a ticking barrage table.
 */
@SuppressWarnings("unused")
public final class BarrageUpdateMetadata extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarrageUpdateMetadata getRootAsBarrageUpdateMetadata(ByteBuffer _bb) { return getRootAsBarrageUpdateMetadata(_bb, new BarrageUpdateMetadata()); }
  public static BarrageUpdateMetadata getRootAsBarrageUpdateMetadata(ByteBuffer _bb, BarrageUpdateMetadata obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarrageUpdateMetadata __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * This batch is generated from an upstream table that ticks independently of the stream. If
   * multiple events are coalesced into one update, the server may communicate that here for
   * informational purposes.
   */
  public long firstSeq() { int o = __offset(4); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }
  public long lastSeq() { int o = __offset(6); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }
  /**
   * Indicates if this message was sent due to upstream ticks or due to a subscription change.
   */
  public boolean isSnapshot() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * If this is a snapshot and the subscription is a viewport, then the effectively subscribed viewport
   * will be included in the payload. It is an encoded and compressed RowSet.
   */
  public byte effectiveViewport(int j) { int o = __offset(10); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int effectiveViewportLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector effectiveViewportVector() { return effectiveViewportVector(new ByteVector()); }
  public ByteVector effectiveViewportVector(ByteVector obj) { int o = __offset(10); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer effectiveViewportAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public ByteBuffer effectiveViewportInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 10, 1); }
  /**
   * When this is set the viewport RowSet will be inverted against the length of the table. That is to say
   * every position value is converted from `i` to `n - i - 1` if the table has `n` rows.
   */
  public boolean effectiveReverseViewport() { int o = __offset(12); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  /**
   * If this is a snapshot, then the effectively subscribed column set will be included in the payload.
   */
  public byte effectiveColumnSet(int j) { int o = __offset(14); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int effectiveColumnSetLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector effectiveColumnSetVector() { return effectiveColumnSetVector(new ByteVector()); }
  public ByteVector effectiveColumnSetVector(ByteVector obj) { int o = __offset(14); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer effectiveColumnSetAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }
  public ByteBuffer effectiveColumnSetInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 1); }
  /**
   * This is an encoded and compressed RowSet that was added in this update.
   */
  public byte addedRows(int j) { int o = __offset(16); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int addedRowsLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector addedRowsVector() { return addedRowsVector(new ByteVector()); }
  public ByteVector addedRowsVector(ByteVector obj) { int o = __offset(16); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer addedRowsAsByteBuffer() { return __vector_as_bytebuffer(16, 1); }
  public ByteBuffer addedRowsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 16, 1); }
  /**
   * This is an encoded and compressed RowSet that was removed in this update.
   */
  public byte removedRows(int j) { int o = __offset(18); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int removedRowsLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector removedRowsVector() { return removedRowsVector(new ByteVector()); }
  public ByteVector removedRowsVector(ByteVector obj) { int o = __offset(18); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer removedRowsAsByteBuffer() { return __vector_as_bytebuffer(18, 1); }
  public ByteBuffer removedRowsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 18, 1); }
  /**
   * This is an encoded and compressed RowSetShiftData describing how the keyspace of unmodified rows changed.
   */
  public byte shiftData(int j) { int o = __offset(20); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int shiftDataLength() { int o = __offset(20); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector shiftDataVector() { return shiftDataVector(new ByteVector()); }
  public ByteVector shiftDataVector(ByteVector obj) { int o = __offset(20); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer shiftDataAsByteBuffer() { return __vector_as_bytebuffer(20, 1); }
  public ByteBuffer shiftDataInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 20, 1); }
  /**
   * This is an encoded and compressed RowSet that was included with this update.
   * (the server may include rows not in addedRows if this is a viewport subscription to refresh
   *  unmodified rows that were scoped into view)
   */
  public byte addedRowsIncluded(int j) { int o = __offset(22); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int addedRowsIncludedLength() { int o = __offset(22); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector addedRowsIncludedVector() { return addedRowsIncludedVector(new ByteVector()); }
  public ByteVector addedRowsIncludedVector(ByteVector obj) { int o = __offset(22); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer addedRowsIncludedAsByteBuffer() { return __vector_as_bytebuffer(22, 1); }
  public ByteBuffer addedRowsIncludedInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 22, 1); }
  /**
   * The list of modified column data are in the same order as the field nodes on the schema.
   */
  public io.deephaven.barrage.flatbuf.BarrageModColumnMetadata modColumnNodes(int j) { return modColumnNodes(new io.deephaven.barrage.flatbuf.BarrageModColumnMetadata(), j); }
  public io.deephaven.barrage.flatbuf.BarrageModColumnMetadata modColumnNodes(io.deephaven.barrage.flatbuf.BarrageModColumnMetadata obj, int j) { int o = __offset(24); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int modColumnNodesLength() { int o = __offset(24); return o != 0 ? __vector_len(o) : 0; }
  public io.deephaven.barrage.flatbuf.BarrageModColumnMetadata.Vector modColumnNodesVector() { return modColumnNodesVector(new io.deephaven.barrage.flatbuf.BarrageModColumnMetadata.Vector()); }
  public io.deephaven.barrage.flatbuf.BarrageModColumnMetadata.Vector modColumnNodesVector(io.deephaven.barrage.flatbuf.BarrageModColumnMetadata.Vector obj) { int o = __offset(24); return o != 0 ? obj.__assign(__vector(o), 4, bb) : null; }
  /**
   * The current size of the table.
   */
  public long tableSize() { int o = __offset(26); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }

  public static int createBarrageUpdateMetadata(FlatBufferBuilder builder,
      long firstSeq,
      long lastSeq,
      boolean isSnapshot,
      int effectiveViewportOffset,
      boolean effectiveReverseViewport,
      int effectiveColumnSetOffset,
      int addedRowsOffset,
      int removedRowsOffset,
      int shiftDataOffset,
      int addedRowsIncludedOffset,
      int modColumnNodesOffset,
      long tableSize) {
    builder.startTable(12);
    BarrageUpdateMetadata.addTableSize(builder, tableSize);
    BarrageUpdateMetadata.addLastSeq(builder, lastSeq);
    BarrageUpdateMetadata.addFirstSeq(builder, firstSeq);
    BarrageUpdateMetadata.addModColumnNodes(builder, modColumnNodesOffset);
    BarrageUpdateMetadata.addAddedRowsIncluded(builder, addedRowsIncludedOffset);
    BarrageUpdateMetadata.addShiftData(builder, shiftDataOffset);
    BarrageUpdateMetadata.addRemovedRows(builder, removedRowsOffset);
    BarrageUpdateMetadata.addAddedRows(builder, addedRowsOffset);
    BarrageUpdateMetadata.addEffectiveColumnSet(builder, effectiveColumnSetOffset);
    BarrageUpdateMetadata.addEffectiveViewport(builder, effectiveViewportOffset);
    BarrageUpdateMetadata.addEffectiveReverseViewport(builder, effectiveReverseViewport);
    BarrageUpdateMetadata.addIsSnapshot(builder, isSnapshot);
    return BarrageUpdateMetadata.endBarrageUpdateMetadata(builder);
  }

  public static void startBarrageUpdateMetadata(FlatBufferBuilder builder) { builder.startTable(12); }
  public static void addFirstSeq(FlatBufferBuilder builder, long firstSeq) { builder.addLong(0, firstSeq, 0L); }
  public static void addLastSeq(FlatBufferBuilder builder, long lastSeq) { builder.addLong(1, lastSeq, 0L); }
  public static void addIsSnapshot(FlatBufferBuilder builder, boolean isSnapshot) { builder.addBoolean(2, isSnapshot, false); }
  public static void addEffectiveViewport(FlatBufferBuilder builder, int effectiveViewportOffset) { builder.addOffset(3, effectiveViewportOffset, 0); }
  public static int createEffectiveViewportVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createEffectiveViewportVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startEffectiveViewportVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addEffectiveReverseViewport(FlatBufferBuilder builder, boolean effectiveReverseViewport) { builder.addBoolean(4, effectiveReverseViewport, false); }
  public static void addEffectiveColumnSet(FlatBufferBuilder builder, int effectiveColumnSetOffset) { builder.addOffset(5, effectiveColumnSetOffset, 0); }
  public static int createEffectiveColumnSetVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createEffectiveColumnSetVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startEffectiveColumnSetVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addAddedRows(FlatBufferBuilder builder, int addedRowsOffset) { builder.addOffset(6, addedRowsOffset, 0); }
  public static int createAddedRowsVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createAddedRowsVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startAddedRowsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addRemovedRows(FlatBufferBuilder builder, int removedRowsOffset) { builder.addOffset(7, removedRowsOffset, 0); }
  public static int createRemovedRowsVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createRemovedRowsVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startRemovedRowsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addShiftData(FlatBufferBuilder builder, int shiftDataOffset) { builder.addOffset(8, shiftDataOffset, 0); }
  public static int createShiftDataVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createShiftDataVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startShiftDataVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addAddedRowsIncluded(FlatBufferBuilder builder, int addedRowsIncludedOffset) { builder.addOffset(9, addedRowsIncludedOffset, 0); }
  public static int createAddedRowsIncludedVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createAddedRowsIncludedVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startAddedRowsIncludedVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addModColumnNodes(FlatBufferBuilder builder, int modColumnNodesOffset) { builder.addOffset(10, modColumnNodesOffset, 0); }
  public static int createModColumnNodesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startModColumnNodesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTableSize(FlatBufferBuilder builder, long tableSize) { builder.addLong(11, tableSize, 0L); }
  public static int endBarrageUpdateMetadata(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarrageUpdateMetadata get(int j) { return get(new BarrageUpdateMetadata(), j); }
    public BarrageUpdateMetadata get(BarrageUpdateMetadata obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

