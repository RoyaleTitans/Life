package com.royaletitans.life.networking.messages.server;

import com.royaletitans.life.lib.Buffer;
import com.royaletitans.life.networking.messages.ServerMessage;

public class ServerKeepAlive extends ServerMessage {
	public ServerKeepAlive() { }
	
	@Override
	public int getId() {
		return 20108;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public Buffer getBuffer() {
		return Buffer.allocate(0);
	}
}
