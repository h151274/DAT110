package no.hvl.dat110.system.controller;

import jdk.nashorn.internal.runtime.ECMAException;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		// TODO
		// create display and sensor object
		
		Display display = new Display(); 
		Sensor sensor = new Sensor(); 
		
		// create RPC clients for display device and sensor device
		
		RPCClient displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT); 
		RPCClient sensorclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		// register RPC methods in the RPC layer
		displayclient.register(display);
		sensorclient.register(sensor);
	
		// register stop methods in the RPC middleware
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		// TODO:
		// loop while reading from sensor and write to display via RPC
		
		for (int i = 0; i < N; i++) { 
			int value = sensor.read(); 
			display.write(value + " °C");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
