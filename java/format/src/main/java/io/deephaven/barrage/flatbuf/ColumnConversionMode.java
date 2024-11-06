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

/**
 * There will always be types that cannot be easily supported over IPC. While column conversion mode is no longer
 * supported, users can more explicitly configure the encoding/decoding behavior of the server.
 */
@SuppressWarnings("unused")
public final class ColumnConversionMode {
  private ColumnConversionMode() { }
  public static final byte Stringify = 1;
  public static final byte JavaSerialization = 2;
  public static final byte ThrowError = 3;

  public static final String[] names = { "Stringify", "JavaSerialization", "ThrowError", };

  public static String name(int e) { return names[e - Stringify]; }
}

