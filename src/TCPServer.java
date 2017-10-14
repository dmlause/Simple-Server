/* Derek Lause
 * CSC 565 Programming Assignment 1
 * Fall 2017
 * Instructor: Dr. Hui Liu 
 */

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class TCPServer extends Thread {
	// Initialize a server socket
	private ServerSocket serverSocket;
	
	// Server socket initialization
	// Server socket will timeout in 60 seconds
	public TCPServer(int portNumber) throws IOException {
		serverSocket = new ServerSocket(portNumber);
		serverSocket.setSoTimeout(60000);
	}
	
	
	// Calculates monthly payment
	public static double monthlyPayment(int loanAmount, int loanPeriod, float interestRate)
	{
		int numPayments = loanPeriod * 12;
		double monthlyInterest = interestRate / 12;
		double monthlyPayment;
		
		monthlyPayment = ((loanAmount * monthlyInterest) / (1 - (Math.pow(1 / (1 + monthlyInterest), numPayments))));
		
		return monthlyPayment;
	}
	
	// The server will run as long as the socket does not timeout
	// Once the client receives its request, the socket is closed and must be reopened again.
	public void run()
	{
		while(true)
		{
			try
			{
				//Accepts an incoming client attempt to connect
				System.out.println("The server is waiting for the client to connect on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				
				// Establishes connection with the client and begins reading input
				System.out.println("The server has connected to client " + server.getRemoteSocketAddress());
				DataInputStream input = new DataInputStream(server.getInputStream());
				
				// Expected input
				int loanAmount = input.readInt();
				int loanPeriod = input.readInt();
				float interestRate = input.readFloat();
				interestRate = interestRate / 100;
				
				// System.out.println(loanAmount + loanPeriod + interestRate);  For testing purposes
				
				// Calculate monthly payment
				double monthlyPayment;
				monthlyPayment = monthlyPayment(loanAmount, loanPeriod, interestRate);
				
				// Send the response back to the client
				DataOutputStream output = new DataOutputStream(server.getOutputStream());
				output.writeUTF("Loan Amount of $" + loanAmount + " requested. \n"
						+ "Your monthly payment is $" + monthlyPayment + ". \n"
						+ "Your yearly or total payment is $" + monthlyPayment * 12);
				server.close();
				
			}
			// Executed if the client does not connect within the 60 second timeout
			catch (SocketTimeoutException se)
			{
				System.out.println("Socket connection has timed out! Please restart the server to open the socket again.");
				break;
			}
			// Catches any other possible exception and prints it to the user
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] args) {
		
		// Initialize port number variable
		int portNumber;
		
		// Asks the user for desired port number
		System.out.println("Welcome to the TCP server for CSC 565's first programming assignment!\n"
					+ "Please enter a port number for the server to use: ");
		Scanner sc = new Scanner(System.in);
		portNumber = sc.nextInt();
		sc.close();
	
		try {
			// Initializes the server in a new thread and starts it
			Thread t = new TCPServer(portNumber);
			t.start();
		}
		// Catches any other exception and prints it to the user
		catch(IOException e) {
			System.out.println(e);
		}

	}

}
