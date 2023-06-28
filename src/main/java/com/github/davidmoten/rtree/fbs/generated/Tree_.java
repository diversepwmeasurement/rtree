// automatically generated by the FlatBuffers compiler, do not modify

package com.github.davidmoten.rtree.fbs.generated;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Tree_ extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_8(); }
  public static Tree_ getRootAsTree_(ByteBuffer _bb) { return getRootAsTree_(_bb, new Tree_()); }
  public static Tree_ getRootAsTree_(ByteBuffer _bb, Tree_ obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Tree_ __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public com.github.davidmoten.rtree.fbs.generated.Context_ context() { return context(new com.github.davidmoten.rtree.fbs.generated.Context_()); }
  public com.github.davidmoten.rtree.fbs.generated.Context_ context(com.github.davidmoten.rtree.fbs.generated.Context_ obj) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  public com.github.davidmoten.rtree.fbs.generated.Node_ root() { return root(new com.github.davidmoten.rtree.fbs.generated.Node_()); }
  public com.github.davidmoten.rtree.fbs.generated.Node_ root(com.github.davidmoten.rtree.fbs.generated.Node_ obj) { int o = __offset(6); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  public long size() { int o = __offset(8); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }

  public static int createTree_(FlatBufferBuilder builder,
      int contextOffset,
      int rootOffset,
      long size) {
    builder.startTable(3);
    Tree_.addSize(builder, size);
    Tree_.addRoot(builder, rootOffset);
    Tree_.addContext(builder, contextOffset);
    return Tree_.endTree_(builder);
  }

  public static void startTree_(FlatBufferBuilder builder) { builder.startTable(3); }
  public static void addContext(FlatBufferBuilder builder, int contextOffset) { builder.addOffset(0, contextOffset, 0); }
  public static void addRoot(FlatBufferBuilder builder, int rootOffset) { builder.addOffset(1, rootOffset, 0); }
  public static void addSize(FlatBufferBuilder builder, long size) { builder.addInt(2, (int)size, (int)0L); }
  public static int endTree_(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishTree_Buffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedTree_Buffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Tree_ get(int j) { return get(new Tree_(), j); }
    public Tree_ get(Tree_ obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

