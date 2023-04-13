package sha;

import java.security.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;

public class sha {
	static final String HEXES = "0123456789abcdef";
	static final int BUFFERSIZE = 4096;
	
	public static String getHex(byte[] raw) {
		if (raw == null)
			return null;
		final StringBuilder hex = new StringBuilder (2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F) >> 4));
		}
		
		return hex.toString();
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		MessageDigest oMD = MessageDigest.getInstance("SHA1");
		FileInputStream oIn = new FileInputStream("C:\\Users\\guite\\OneDrive\\Área de Trabalho\\DistributedSystems-UBI\\PracticalClasses\\Aulas\\src\\sha\\teste.txt");
		byte [] buffer = new byte[4096];
		int byteRead = 0;
		while((byteRead = oIn.read(buffer)) != -1) { oMD.update(buffer, 0, byteRead);}
		System.out.println("SHA1(file): " + getHex(oMD.digest()));
	}
}
