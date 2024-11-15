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

@SuppressWarnings("unused")
public final class BarrageMessageType {
  private BarrageMessageType() { }
  /**
   * A barrage message wrapper might send a None message type
   * if the msg_payload is empty.
   */
  public static final byte None = 0;
  /**
   * enum values 1 - 3 are reserved for future use
   */
  public static final byte UNUSED_1 = 1;
  public static final byte UNUSED_2 = 2;
  public static final byte UNUSED_3 = 3;
  /**
   * for subscription parsing/management (aka DoPut, DoExchange)
   */
  public static final byte BarrageSerializationOptions = 4;
  public static final byte BarrageSubscriptionRequest = 5;
  public static final byte BarrageUpdateMetadata = 6;
  public static final byte BarrageSnapshotRequest = 7;
  public static final byte BarragePublicationRequest = 8;

  public static final String[] names = { "None", "UNUSED_1", "UNUSED_2", "UNUSED_3", "BarrageSerializationOptions", "BarrageSubscriptionRequest", "BarrageUpdateMetadata", "BarrageSnapshotRequest", "BarragePublicationRequest", };

  public static String name(int e) { return names[e]; }
}

