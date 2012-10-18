/**
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package redelm.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import redelm.schema.PrimitiveType.Primitive;



<<<<<<< HEAD
public class MessageType extends GroupType {
=======

  public static MessageType parse(String schemaString) {

    StringTokenizer st = new StringTokenizer(schemaString, " ;{}\n\t", true);
    String t = nextToken(st);
    check(t, "message", "start with 'message'");
    String name = nextToken(st);
    Type[] fields = readGroupTypeFields(st);
    return new MessageType(name, fields);
  }

  private static Type[] readGroupTypeFields(StringTokenizer st) {
    List<Type> types = new ArrayList<Type>();
    String t = nextToken(st);
    check(t, "{", "start of message");
    while (!(t = nextToken(st)).equals("}")) {
      types.add(readType(t, st));
    }
    return types.toArray(new Type[types.size()]);
  }

  private static Type readType(String t, StringTokenizer st) {
    Repetition r = Repetition.valueOf(t.toUpperCase());
    t = nextToken(st);
    String name = nextToken(st);
    if (t.equalsIgnoreCase("group")) {
      Type[] fields = readGroupTypeFields(st);
      check(nextToken(st), ";", "field ended by ;");
      return new GroupType(r, name, fields);
    } else {
      Primitive p = Primitive.valueOf(t.toUpperCase());
      check(nextToken(st), ";", "field ended by ;");
      return new PrimitiveType(r, p, name);
    }
  }

  private static void check(String t, String expected, String message) {
    if (!t.equalsIgnoreCase(expected)) {
      throw new IllegalArgumentException("expected: "+message+ ": '" + expected + "' got '" + t + "'");
    }
  }

  private static String nextToken(StringTokenizer st) {
    while (st.hasMoreTokens()) {
      String t = st.nextToken();
      if (!isWhitespace(t)) {
        return t;
      }
    }
    throw new IllegalArgumentException("unexpected end of schema");
  }

  private static boolean isWhitespace(String t) {
    return t.equals(" ") || t.equals("\t") || t.equals("\n");
  }

>>>>>>> 11b8190df05c1ef61a6dc677db378bca62338062
  public MessageType(String name, Type... fields) {
    super(Repetition.REPEATED, name, fields);
  }

  @Override
  public void accept(TypeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public String toString() {
    return membersDisplayString(
                new StringBuilder("message ")
                    .append(getName())
                    .append(" {\n"), "  ")
                .append("}\n").toString();
  }
<<<<<<< HEAD
}
=======

}
>>>>>>> 11b8190df05c1ef61a6dc677db378bca62338062
