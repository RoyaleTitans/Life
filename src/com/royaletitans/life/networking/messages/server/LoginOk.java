package com.royaletitans.life.networking.messages.server;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.lib.OutBuffer;
import com.royaletitans.life.networking.messages.ServerMessage;

public class LoginOk extends ServerMessage {
	public LoginOk() {
	}

	@Override
	public int getId() {
		return 20104;
	}

	@Override
	public int getVersion() {
		return 1;
	}

	@Override
	public Buffer getBuffer() {
		OutBuffer stream = OutBuffer.newBuffer();
		
		// These values are temporary - they will be changed later.
		stream.writeLong(0);
		stream.writeLong(0);
		
		stream.writeString("");
		stream.writeString("");
		stream.writeString("");
		
		stream.writeInt(0);
		stream.writeInt(1620);
		stream.writeInt(1);
		stream.writeString("prod");
		
		stream.writeInt(0);
		stream.writeInt(0);
		stream.writeInt(0);
		
		stream.writeString("");
		stream.writeString("");
		stream.writeString("");
		
		stream.writeInt(0);
		
		return stream.obtain();
	}
}
