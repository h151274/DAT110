package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void register(RPCStub remote) {
		remote.register(this);
	}

	public void connect() {

		// TODO: connect using the underlying messaging layer connection
		if (connection == null) {
			try {
				connection = msgclient.connect();
			} catch (Exception e) {
				System.out.println("An error occurred: " + e);
			}
		}

	}

	public void disconnect() {

		// TODO: disconnect/close the underlying messaging connection

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e);
		}
	}

	public byte[] call(byte[] rpcrequest) {

		/*
		 * TODO:
		 * 
		 * Make a remote call on the RPC server by sending a request message and receive
		 * a reply message
		 * 
		 * rpcrequest is the marshalled rpcrequest from the client-stub rpctreply is the
		 * rpcreply to be unmarshalled by the client-stub
		 * 
		 */
		Message requestMessage = new Message (rpcrequest); 
		
		connection.send(requestMessage);
		
		Message recieveMessage = connection.receive();

		
		byte[] rpcreply = recieveMessage.getData();
		

		return rpcreply;

	}

}
