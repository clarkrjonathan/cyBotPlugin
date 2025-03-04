package org.jonathanclark.cybot_plugin.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Client to access server endpoint hosted on CyBot
 * @author Jonathan Clark
 */
public class Client {
	
	/**
	 * Socket wrapper
	 */
	private Socket socket;
	
	/**
	 * Raw output stream for writing data to CyBot
	 */
	private OutputStream out;
	
	/**
	 * Raw input stream for reading data from CyBot
	 */
	private InputStream in;
	
	/**
	 * Setup for client to given server ip on given port
	 * @param ip - ip of server
	 * @param port
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Client(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		
		out = socket.getOutputStream();
		in = socket.getInputStream();
	}
	
	/**
	 * Send a byte to Server
	 * @param b - byte to be sent
	 * @throws IOException 
	 */
	public void sendByte(byte b) throws IOException {
		out.write(b);
	}
	
	/**
	 * Send byte and await echo back
	 * @param b - byte to be sent
	 * @param echo - if true wait for echo
	 * @throws IOException 
	 */
	public void sendByte(byte b, boolean echo) throws IOException {
		if(echo) {
			sendByte(b);
			while(in.read() == -1) {};
		} else {
			sendByte(b);
		}
	}
	

	/**
	 * Closes out all streams and steam socket
	 * @throws IOException
	 */
	public void closeClient() throws IOException {
		socket.close();
		out.close();
		in.close();
	}
	

}