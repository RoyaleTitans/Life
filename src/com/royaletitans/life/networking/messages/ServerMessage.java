package com.royaletitans.life.networking.messages;

import com.royaletitans.life.lib.Buffer;

public abstract class ServerMessage {
    public abstract int getId();
    public abstract int getVersion();
    public abstract Buffer getBuffer();
}
