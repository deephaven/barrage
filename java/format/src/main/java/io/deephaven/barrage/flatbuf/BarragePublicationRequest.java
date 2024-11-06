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
 * Describes the table update stream the client would like to push to. This is similar to a DoPut but the client
 * will send BarrageUpdateMetadata to explicitly describe the row key space. The updates sent adhere to the table
 * update model semantics; thus BarragePublication enables the client to upload a ticking table.
 */
@SuppressWarnings("unused")
public final class BarragePublicationRequest extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_24_3_25(); }
  public static BarragePublicationRequest getRootAsBarragePublicationRequest(ByteBuffer _bb) { return getRootAsBarragePublicationRequest(_bb, new BarragePublicationRequest()); }
  public static BarragePublicationRequest getRootAsBarragePublicationRequest(ByteBuffer _bb, BarragePublicationRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public BarragePublicationRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * The destination Ticket.
   */
  public byte ticket(int j) { int o = __offset(4); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int ticketLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector ticketVector() { return ticketVector(new ByteVector()); }
  public ByteVector ticketVector(ByteVector obj) { int o = __offset(4); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer ticketAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer ticketInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  /**
   * Options to configure your request.
   */
  public io.deephaven.barrage.flatbuf.BarragePublicationOptions publishOptions() { return publishOptions(new io.deephaven.barrage.flatbuf.BarragePublicationOptions()); }
  public io.deephaven.barrage.flatbuf.BarragePublicationOptions publishOptions(io.deephaven.barrage.flatbuf.BarragePublicationOptions obj) { int o = __offset(6); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }

  public static int createBarragePublicationRequest(FlatBufferBuilder builder,
      int ticketOffset,
      int publishOptionsOffset) {
    builder.startTable(2);
    BarragePublicationRequest.addPublishOptions(builder, publishOptionsOffset);
    BarragePublicationRequest.addTicket(builder, ticketOffset);
    return BarragePublicationRequest.endBarragePublicationRequest(builder);
  }

  public static void startBarragePublicationRequest(FlatBufferBuilder builder) { builder.startTable(2); }
  public static void addTicket(FlatBufferBuilder builder, int ticketOffset) { builder.addOffset(0, ticketOffset, 0); }
  public static int createTicketVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createTicketVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startTicketVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addPublishOptions(FlatBufferBuilder builder, int publishOptionsOffset) { builder.addOffset(1, publishOptionsOffset, 0); }
  public static int endBarragePublicationRequest(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public BarragePublicationRequest get(int j) { return get(new BarragePublicationRequest(), j); }
    public BarragePublicationRequest get(BarragePublicationRequest obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

