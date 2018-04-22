package com.royaletitans.life.networking.messages.server;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.lib.OutBuffer;
import com.royaletitans.life.logic.LogicClientAvatar;
import com.royaletitans.life.logic.LogicClientHome;
import com.royaletitans.life.networking.messages.ServerMessage;

public class OwnHomeData extends ServerMessage {
	public LogicClientAvatar mAvatar;
	public LogicClientHome mHome;
	
    public OwnHomeData() {
    		mAvatar = new LogicClientAvatar();
    		mHome = new LogicClientHome();
    }

    @Override
    public int getId() {
        return 24101;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer stream = OutBuffer.newBuffer();
        stream.writeInt(0);
        stream.writeInt(0);
        stream.writeInt(0);
        stream.write(mAvatar.toBytes());
        stream.write(mHome.toBytes());
        return stream.obtain();
    }
}
