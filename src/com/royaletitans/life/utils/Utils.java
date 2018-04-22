package com.royaletitans.life.utils;

import java.security.SecureRandom;

public class Utils {
    private static final String sRandomChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom sRandom = new SecureRandom();
    private static final char[] sHexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHexa(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = sHexArray[v >>> 4];
            hexChars[j * 2 + 1] = sHexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexaToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(sRandomChars.charAt(sRandom.nextInt(sRandomChars.length())));
        }
        return sb.toString();
    }

	public static String pad(String message) {
		int limit = 27;
		String replaceWith = "...";
		
	    if (message.length() > limit) {
	        message = message.substring(0, limit - replaceWith.length());
	        message = message + replaceWith;
	    }
	    else if (message.length() < limit) {
	        int length = limit - message.length();

	        int leftPad = (int)Math.round((double)length / 2);
	        int rightPad = (int)Math.round((double)length / 2);

	        if (length % 2 != 0) {
	            rightPad = rightPad - 1;
	        }
	        
	        for (int i = 0; i < rightPad; i++) {
	            message += " ";
	        }
	        for (int i = 0; i < leftPad; i++) {
	            message = " " + message;
	        }
	    }

	    return message;
	}
}
