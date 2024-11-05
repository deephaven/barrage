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

/**
 * The message wrapper used for all barrage app_metadata fields.
 */
@SuppressWarnings("unused")
public final class BarrageMessageWrapper extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarrageMessageWrapper getRootAsBarrageMessageWrapper(ByteBuffer _bb) { return getRootAsBarrageMessageWrapper(_bb, new BarrageMessageWrapper()); }
  public static BarrageMessageWrapper getRootAsBarrageMessageWrapper(ByteBuffer _bb, BarrageMessageWrapper obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarrageMessageWrapper __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * Used to identify this type of app_metadata vs other applications.
   * The magic value is '0x6E687064'. It is the numerical representation of the ASCII "dphn".
   */
  public long magic() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  /**
   * The msg type being sent.
   */
  public byte msgType() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }
  /**
   * The msg payload.
   */
  public byte msgPayload(int j) { int o = __offset(8); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int msgPayloadLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector msgPayloadVector() { return msgPayloadVector(new ByteVector()); }
  public ByteVector msgPayloadVector(ByteVector obj) { int o = __offset(8); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer msgPayloadAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer msgPayloadInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }

  public static int createBarrageMessageWrapper(FlatBufferBuilder builder,
      long magic,
      byte msgType,
      int msgPayloadOffset) {
    builder.startTable(3);
    BarrageMessageWrapper.addMsgPayload(builder, msgPayloadOffset);
    BarrageMessageWrapper.addMagic(builder, magic);
    BarrageMessageWrapper.addMsgType(builder, msgType);
    return BarrageMessageWrapper.endBarrageMessageWrapper(builder);
  }

  public static void startBarrageMessageWrapper(FlatBufferBuilder builder) { builder.startTable(3); }
  public static void addMagic(FlatBufferBuilder builder, long magic) { builder.addInt(0, (int) magic, (int) 0L); }
  public static void addMsgType(FlatBufferBuilder builder, byte msgType) { builder.addByte(1, msgType, 0); }
  public static void addMsgPayload(FlatBufferBuilder builder, int msgPayloadOffset) { builder.addOffset(2, msgPayloadOffset, 0); }
  public static int createMsgPayloadVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createMsgPayloadVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startMsgPayloadVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endBarrageMessageWrapper(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarrageMessageWrapper get(int j) { return get(new BarrageMessageWrapper(), j); }
    public BarrageMessageWrapper get(BarrageMessageWrapper obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

