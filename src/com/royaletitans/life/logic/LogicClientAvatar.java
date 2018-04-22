package com.royaletitans.life.logic;

import com.royaletitans.life.lib.OutBuffer;

public class LogicClientAvatar {

	public byte[] toBytes() {
		OutBuffer stream = OutBuffer.newBuffer();

		return stream.obtain().array();
	}
}
