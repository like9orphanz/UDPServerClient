/*
 * Sam Stein
 * Joshua Wright
 *
 * UDPclient.java
 * Systems and Networks II
 * Project 2
 *
 * This file describes the functions to be implemented by the UDPclient class
 * You may also implement any helper functions you deem necessary to complete the project.
 */

import java.io.*;
import java.util.Scanner;
import java.net.*;

public class UDPclient
{
	private DatagramSocket _socket; // the socket for communication with a server
	
	/**
	 * Constructs a TCPclient object.
	 */
	public UDPclient()
	{
	}
	
	/**
	 * Creates a datagram socket and binds it to a free port.
	 *
	 * @return - 0 or a negative number describing an error code if the connection could not be established
	 */
	public int createSocket()
	{	
	
		try {
			_socket = new DatagramSocket();
		} catch(SocketException e) {
			System.out.println("Socket could not be opened");
			return 0;
		} catch (SecurityException e) {
			System.out.println("Security Exception creating socket");
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	/**
	 * Sends a request for service to the server. Do not wait for a reply in this function. This will be
	 * an asynchronous call to the server.
	 * 
	 * @param request - the request to be sent
	 * @param hostAddr - the ip or hostname of the server
	 * @param port - the port number of the server
	 *
	 * @return - 0, if no error; otherwise, a negative number indicating the error
	 */
	public int sendRequest(String request, String hostAddr, int port)
	{

		byte[] message = request.getBytes();
		
		try {
			InetAddress hostAddress = InetAddress.getByName(hostAddr);
		} catch (UnknownHostException e) {
			System.out.println("Unkown host");
			return -1;
		}

		try {
			DatagramPacket packet = new DatagramPacket(message, message.length);
			_socket.send(packet);
		} catch (IOException e) {
			System.out.println("IO exception, unable to send request");
			return -1;
		}
		return 0;
	}

	/**
	 * Receives the server's response following a previously sent request.
	 *
	 * @return - the server's response or NULL if an error occurred
	 */
	public String receiveResponse()
	{
		byte[] readData = new byte[256];
		String response;

		try {
			DatagramPacket packet = new DatagramPacket(readData, readData.length);
			_socket.receive(packet);
		} catch (IOException e) {
			System.out.println("IO exception, unable to receive response");
			return "\0";
		}

		response = new String(packet.getData());

		return response;
	}
	
	/*
    * Prints the response to the screen in a formatted way.
    *
    * response - the server's response as an XML formatted string
    */
	public static void printResponse(String response)
	{
	
	}
 

	/*
	 * Closes an open socket.
	 *
    * @return - 0, if no error; otherwise, a negative number indicating the error
	 */
	public int closeSocket() 
	{
		return 1;	
	}
}
