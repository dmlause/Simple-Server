import java.io.*;
import java.net.*;
import java.util.*;


public class UDPClient {
	
	
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		char isRunning;
		// Asks user for the desired port number to be used
		System.out.println("Welcome to the UDP client for CSC 565's first programming assignment! \n" +
						   "Please enter the port you wish to connect with:	");
		Scanner sc = new Scanner(System.in);
		int portNumber = sc.nextInt();
		
		do {
		// User enters the needed information
		System.out.println("Please enter the command 'Cal <hostname> <loan_amount> <loan_period> <yearly_interest_rate>': ");
		String data = userInput.readLine();
		
		// Creates a new UDP socket
		DatagramSocket clientSocket = new DatagramSocket();
     
		// Grabs the localhost IP address
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		// Initialize the UDP packet data variables
		byte[] receivedData = new byte[1024];
	    byte[] sendData = new byte[1024];
	    
	    // Send the data off to the server for use
	    sendData = data.getBytes();
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, portNumber);
	    clientSocket.send(sendPacket);
	    
	    // Receive the data from the server.
	    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
	    clientSocket.receive(receivedPacket);
	    
	    // Outputs the received data from the server to the user
	    String serverOutput = new String(receivedPacket.getData());
	    System.out.println(serverOutput);
	    
		System.out.println("Do you wish to run another calculation? \n" 
				+ "Enter 'y' for yes or 'n' for no.");
		isRunning = sc.next().charAt(0);
		
	    clientSocket.close();
		} while (isRunning == 'Y' || isRunning == 'y');
		sc.close();
	}

}