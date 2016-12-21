package org.usfirst.frc.team238.core;


import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.Timer;

public class udpClient{
	
	public final static int PORT = 2238;
	public final static int BYTE_SIZE = 8;
	public static final double VISION_DATA_FRESHNESS = .1;
	
	private static DatagramSocket myClient;
	private static DatagramPacket recievePacket;
	
	private static byte[] recieveData;
	private static ByteBuffer dataBuffer;
	
	private boolean isRunning;
	
	private static double angle;
	private static double distance;
	private static double lastDataTimestamp;
	
	
	public static void init()
	{
		try
		{
			myClient = new DatagramSocket(PORT);
			
			
		}
		catch(SocketException e)
		{
			e.printStackTrace();
		}
		recieveData = new byte[BYTE_SIZE];
		
		
		
		dataBuffer = ByteBuffer.wrap(recieveData);
		
		recievePacket = new DatagramPacket(recieveData, recieveData.length);
		
	}
	
	public void run()
	{
		while(isRunning)
		{
			try
			{
				myClient.receive(recievePacket);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			processData(recieveData);
		}
	}
	
	public void enable()
	{
		isRunning = true;
	}
		
	public void disable()
	{
		isRunning = false;
	}
	
	private synchronized static void processData (byte[] receivedData)
	{
		lastDataTimestamp = Timer.getFPGATimestamp();
		
		angle = dataBuffer.get();
		
		distance = dataBuffer.get();
		
		/*angle = dataBuffer.wrap(receivedData).getDouble();
		distance = dataBuffer.wrap(receivedData).getDouble();*/
	}
	
	public synchronized double getAngle()
	{
		return angle;
	}
	
	public synchronized double getDistance()
	{
		return distance;
	}
	
	public synchronized boolean isFresh()
	{
		return (Timer.getFPGATimestamp() - lastDataTimestamp) < VISION_DATA_FRESHNESS;
	}
	
	
	
	
	/*public static void main(String args[])
	{
		
		init();
		try
		{
			myClient.receive(recievePacket);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		processData(recieveData);
		System.out.println(angle);
		
	}*/
	
	
}
