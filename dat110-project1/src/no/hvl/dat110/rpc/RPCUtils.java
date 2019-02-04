package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RPCUtils {

	public static byte[] marshallString(byte rpcid, String str) {

		// TODO: marshall RPC identifier and string into byte array
		
		byte[] encoded = new byte [str.getBytes().length+1];
		encoded [0] = rpcid;
		
		byte [] s = str.getBytes(); 
		
		for (int i = 1; i < encoded.length; i++) { 
			encoded [i] = s[i-1];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {


		// TODO: unmarshall String contained in data into decoded
		
		String decoded = new String (Arrays.copyOfRange(data, 1, data.length));
	

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		// TODO: marshall RPC identifier in case of void type

		byte[] encoded = new byte [1]; 
		
		encoded[0] = rpcid; 

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {
		// TODO: unmarshall void type
		return;
		

	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {


		// TODO: marshall RPC identifier and string into byte array
		
		byte[] encoded = new byte [5];
		
		encoded [0] = rpcid; 
		byte[] buffer = ByteBuffer.allocate(4).putInt(x).array();
		
		for (int i = 1; i < encoded.length; i++) { 
			encoded [i] = buffer[i-1];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		// TODO: unmarshall integer contained in data
		int decoded = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, data.length)).getInt();
		

		return decoded;

	}
}
