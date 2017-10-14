import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer extends Thread 
{
	// Initialize a server socket
	private DatagramSocket serverSocket;
	byte[] receivedData = new byte[1024];
    byte[] sendData = new byte[1024];
	
	// Server socket initialization
	// Server socket will timeout in 60 seconds
	public UDPServer(int portNumber) throws IOException 
	{
		serverSocket = new DatagramSocket(portNumber);
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
	
	public void run()
	{
        
		while(true)
		{
			DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
			try 
			{
				serverSocket.receive(receivedPacket);
			} 
			catch (IOException e) 
			{
				System.out.println(e);
			}
			
			String data = new String(receivedPacket.getData());
			String[] loanData = data.split(" ");
			
			int loanAmount = Integer.parseInt(loanData[2]);
			int loanPeriod = Integer.parseInt(loanData[3]);
			float interestRate = Float.parseFloat(loanData[4]);
			interestRate = interestRate / 100;
			
			double monthlyPayment = monthlyPayment(loanAmount, loanPeriod, interestRate);
					
			data = "Loan Amount of $" + loanAmount + " requested. \n"
					+ "Your monthly payment is: $" + monthlyPayment +". \n"
					+ "Your yearly or total payment is: $" + monthlyPayment * 12 + ". \n";
			
			sendData = data.getBytes();
			InetAddress IPAddress = receivedPacket.getAddress();
			int port = receivedPacket.getPort();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			try 
			{
				serverSocket.send(sendPacket);
			} 
			catch (IOException e) 
			{
				System.out.println(e);
			}
			
		}
	}

	public static void main(String[] args) {
		// Initialize port number variable
		int portNumber;
		
		// Asks the user for desired port number
		System.out.println("Welcome to the UDP server for CSC 565's first programming assignment!\n"
					+ "Please enter a port number for the server to use: ");
		Scanner sc = new Scanner(System.in);
		portNumber = sc.nextInt();
		System.out.println("Listening on port " + portNumber + " until the socket times out...");
		sc.close();
	
		try 
		{
			// Initializes the server in a new thread and starts it
			Thread t = new UDPServer(portNumber);
			t.start();
		}
		// Catches any other exception and prints it to the user
		catch(IOException e) {
			System.out.println(e);
		}
	}

}
