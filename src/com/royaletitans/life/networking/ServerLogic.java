package com.royaletitans.life.networking;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.lib.RC4;
import com.royaletitans.life.networking.messages.ClientMessage;
import com.royaletitans.life.networking.messages.Headers;
import com.royaletitans.life.networking.messages.ServerMessage;
import com.royaletitans.life.networking.messages.client.ClientKeepAlive;
import com.royaletitans.life.networking.messages.client.Login;
import com.royaletitans.life.networking.messages.server.LoginOk;
import com.royaletitans.life.networking.messages.server.OwnHomeData;
import com.royaletitans.life.networking.messages.server.ServerKeepAlive;
import com.royaletitans.life.utils.Debugger;

public class ServerLogic {
    private static ServerLogic sInstance;

    public RC4 mRC4;
    
    static synchronized ServerLogic getInstance() {
        if (sInstance == null) {
            sInstance = new ServerLogic();
        }

        return sInstance;
    }

    private ServerLogic() {
    		mRC4 = new RC4("asfddsfsd874397f7d98wq090qd80qw");
        Debugger.info("Server is listening on 0.0.0.0:9339!");
    }

    void initialize() {}

    ClientMessage route(Headers headers, Buffer buffer) {
    		buffer = mRC4.decrypt(buffer.array());

        switch (headers.getId()) {
            case 10101:
                return new Login(buffer);
            case 10108:
                return new ClientKeepAlive(buffer);
        }
        return null;
    }

    ServerMessage[] handle(Headers headers, ClientMessage clientMessage) {
        switch (headers.getId()) {
            case 10101:
                return new ServerMessage[] {
                        new LoginOk(),
                        new OwnHomeData()
                };
            case 10108:
                return new ServerMessage[] {
                        new ServerKeepAlive()
                };
        }

        return null;
    }
}
