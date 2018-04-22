package com.royaletitans.life.networking.messages.client;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.networking.messages.ClientMessage;

public class Login extends ClientMessage {
    public Login(Buffer buffer) {
        super(buffer);
    }
}
