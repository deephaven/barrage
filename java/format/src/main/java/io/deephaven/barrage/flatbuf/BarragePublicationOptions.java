/*
 * Copyright 2020 Deephaven Data Labs
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
public final class BarragePublicationOptions extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarragePublicationOptions getRootAsBarragePublicationOptions(ByteBuffer _bb) { return getRootAsBarragePublicationOptions(_bb, new BarragePublicationOptions()); }
  public static BarragePublicationOptions getRootAsBarragePublicationOptions(ByteBuffer _bb, BarragePublicationOptions obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarragePublicationOptions __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * Deephaven reserves a value in the range of primitives as a custom NULL value. This enables more efficient transmission
   * by eliminating the additional complexity of the validity buffer.
   */
  public boolean useDeephavenNulls() { int o = __offset(4); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createBarragePublicationOptions(FlatBufferBuilder builder,
      boolean useDeephavenNulls) {
    builder.startTable(1);
    BarragePublicationOptions.addUseDeephavenNulls(builder, useDeephavenNulls);
    return BarragePublicationOptions.endBarragePublicationOptions(builder);
  }

  public static void startBarragePublicationOptions(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addUseDeephavenNulls(FlatBufferBuilder builder, boolean useDeephavenNulls) { builder.addBoolean(0, useDeephavenNulls, false); }
  public static int endBarragePublicationOptions(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarragePublicationOptions get(int j) { return get(new BarragePublicationOptions(), j); }
    public BarragePublicationOptions get(BarragePublicationOptions obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

