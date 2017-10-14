/* Derek Lause
 * CSC 565 Programming Assignment 1
 * Fall 2017
 * Instructor: Dr. Hui Liu 
 */

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
	
	private static Scanner sc;

	public static void main(String [] args)
	{
		// Asks user for the desired port number to be used
		System.out.println("Welcome to the TCP client for CSC 565's first programming assignment! \n" +
						   "Please enter the port you wish to connect with:	");
		sc = new Scanner(System.in);
		int portNumber = sc.nextInt();

		// Initialize variables
		String serverName = "";
		String init = "";
		int loanAmount;
		int loanPeriod;
		float interestRate;
		char isRunning;
		
		try
		{
			do { // Runs this loop while isRunning is true. The user can quit by pressing anything other than a y.
	
			// User enters the needed information
			System.out.println("Please enter the command 'Cal <hostname> <loan_amount> <loan_period> <yearly_interest_rate>': ");
			init = sc.next();
			serverName = sc.next();
			loanAmount = sc.nextInt();
			loanPeriod = sc.nextInt();
			interestRate = sc.nextFloat();
			
			// The client attempts to connect to the server
			System.out.println("Connecting to " + serverName + "using port " + portNumber + ".");
			Socket client = new Socket(serverName, portNumber);
			
			// Client is connected and the output stream is initialized
			System.out.println("The client has connected to " + client.getRemoteSocketAddress());
			OutputStream outputToServer = client.getOutputStream();
			DataOutputStream output = new DataOutputStream(outputToServer);			
			
			// Streams the data to the server
			output.writeInt(loanAmount);
			output.writeInt(loanPeriod);
			output.writeFloat(interestRate);
			
			// Initialize response from server request
			InputStream inputFromServer = client.getInputStream();
			DataInputStream input = new DataInputStream(inputFromServer);
			
			// Outputs the information to the user
			System.out.println(input.readUTF());
			System.out.println("Do you wish to run another calculation? \n" 
					+ "Enter 'y' for yes or 'n' for no.");
			isRunning = sc.next().charAt(0);
			
			client.close();
			} while (isRunning == 'y' || isRunning == 'Y'); // Press anything but 'y' to close the client
		}
		// Catches any other possible exceptions and prints it to the user
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
