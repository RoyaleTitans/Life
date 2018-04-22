package com.royaletitans.life.networking.messages;

import com.royaletitans.life.lib.Buffer;

public class ClientMessage {
    private final Buffer mBuffer;

    public ClientMessage(Buffer buffer) {
        mBuffer = buffer;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }
}
