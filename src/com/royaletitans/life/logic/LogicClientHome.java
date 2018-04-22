package com.royaletitans.life.logic;

import com.royaletitans.life.lib.OutBuffer;

public class LogicClientHome {

	public byte[] toBytes() {
		OutBuffer stream = OutBuffer.newBuffer();
		
        return stream.obtain().array();
	}
}
