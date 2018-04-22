package com.royaletitans.life.networking.messages.client;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.networking.messages.ClientMessage;

public class ClientKeepAlive extends ClientMessage {
    public ClientKeepAlive(Buffer buffer) {
        super(buffer);
    }
}
