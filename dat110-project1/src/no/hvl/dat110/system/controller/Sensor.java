package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		// TODO
		// implement marshalling, call and unmarshalling for read RPC method
		
		rmiclient.connect();
		byte [] bytesToSend = RPCUtils.marshallInteger(RPCID, 0); 
		byte [] recieved = rmiclient.call(bytesToSend);
		
		int temp = RPCUtils.unmarshallInteger(recieved);
		
		return temp;
	}
	
}
