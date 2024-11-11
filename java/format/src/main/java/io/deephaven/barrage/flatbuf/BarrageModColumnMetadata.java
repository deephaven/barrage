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
 * Holds all of the rowset data structures for the column being modified.
 */
@SuppressWarnings("unused")
public final class BarrageModColumnMetadata extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarrageModColumnMetadata getRootAsBarrageModColumnMetadata(ByteBuffer _bb) { return getRootAsBarrageModColumnMetadata(_bb, new BarrageModColumnMetadata()); }
  public static BarrageModColumnMetadata getRootAsBarrageModColumnMetadata(ByteBuffer _bb, BarrageModColumnMetadata obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarrageModColumnMetadata __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * This is an encoded and compressed RowSet for this column (within the viewport) that were modified.
   * There is no notification for modifications outside of the viewport.
   */
  public byte modifiedRows(int j) { int o = __offset(4); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int modifiedRowsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector modifiedRowsVector() { return modifiedRowsVector(new ByteVector()); }
  public ByteVector modifiedRowsVector(ByteVector obj) { int o = __offset(4); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer modifiedRowsAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer modifiedRowsInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int createBarrageModColumnMetadata(FlatBufferBuilder builder,
      int modifiedRowsOffset) {
    builder.startTable(1);
    BarrageModColumnMetadata.addModifiedRows(builder, modifiedRowsOffset);
    return BarrageModColumnMetadata.endBarrageModColumnMetadata(builder);
  }

  public static void startBarrageModColumnMetadata(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addModifiedRows(FlatBufferBuilder builder, int modifiedRowsOffset) { builder.addOffset(0, modifiedRowsOffset, 0); }
  public static int createModifiedRowsVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createModifiedRowsVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startModifiedRowsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endBarrageModColumnMetadata(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarrageModColumnMetadata get(int j) { return get(new BarrageModColumnMetadata(), j); }
    public BarrageModColumnMetadata get(BarrageModColumnMetadata obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

