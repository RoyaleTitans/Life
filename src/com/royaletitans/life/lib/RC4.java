package com.royaletitans.life.lib;

public class RC4 {
	private final byte[] S = new byte[256];
	private final byte[] T = new byte[256];
	private final int keylen;

	public RC4(String key)
	{
		this(stringToByteArray(key));
	}
	
	public RC4(final byte[] key) {
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException("Key must be between 1 and 256 bytes!");
		} else {
			keylen = key.length;
			for (int i = 0; i < 256; i++) {
				S[i] = (byte) i;
				T[i] = key[i % keylen];
			}
			int j = 0;
			byte tmp;
			for (int i = 0; i < 256; i++) {
				j = (j + S[i] + T[i]) & 0xFF;
				tmp = S[j];
				S[j] = S[i];
				S[i] = tmp;
			}
		}
	}

	public Buffer encrypt(final byte[] plainText) {
		final byte[] cipherText = new byte[plainText.length];
		int i = 0, j = 0, k, t;
		byte tmp;
		for (int counter = 0; counter < plainText.length; counter++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			tmp = S[j];
			S[j] = S[i];
			S[i] = tmp;
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			cipherText[counter] = (byte) (plainText[counter] ^ k);
		}
		return Buffer.wrap(cipherText);
	}

	public Buffer decrypt(final byte[] ciphertext) {
		return Buffer.wrap(encrypt(ciphertext).array());
	}
	
	 public static byte[] stringToByteArray(String value) {
         byte[] bytes = new byte[value.length()];
         for (int i = 0; i < value.length(); i++)
             bytes[i] = (byte)value.toCharArray()[i];
         return bytes;
     }
}
