package com.royaletitans.life.lib;

import java.nio.ByteBuffer;

public class Buffer {
    final ByteBuffer mBuffer;

    public static Buffer allocate(int size) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        return new Buffer(byteBuffer);
    }

    public static Buffer wrap(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new Buffer(byteBuffer);
    }

    public Buffer(ByteBuffer byteBuffer) {
        mBuffer = byteBuffer;
    }

    public byte[] array() {
        return mBuffer.array();
    }

    public int capacity() {
        return mBuffer.capacity();
    }

    public void clear() {
        mBuffer.clear();
    }

    public void flip() {
        mBuffer.flip();
    }

    public ByteBuffer getByteBuffer() {
        return mBuffer;
    }

    public int position() {
        return mBuffer.position();
    }

    public void position(int newPosition) {
        mBuffer.position(newPosition);
    }

    public byte read() {
        return mBuffer.get();
    }

    public byte[] read(int len) {
        byte[] b = new byte[len];
        read(b, 0, len);
        return b;
    }

    public void read(byte[] dest, int offset, int len) {
        mBuffer.get(dest, offset, len);
    }

    public int readInt() {
        return mBuffer.getInt();
    }

    public int readShort() {
        return mBuffer.getShort();
    }

    public long readLong() {
        return mBuffer.getLong();
    }

    public String readString() {
        int len = readInt();
        if (len < 0) {
            return "";
        }
        byte[] b = new byte[len];
        read(b, 0, len);
        return new String(b);
    }

    public void rewind() {
        mBuffer.rewind();
    }
}
