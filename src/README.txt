Derek Lause derek456@live.missouristate.edu
CSC 565 10/5/2017 TCP and UDP socket programming
FILES SUBMITTED:
  1. TCPClient.java
  2. TCPServer.java
  3. UDPClient.java
  4. UDPServer.java
  5. The project folder will be submitted in full so the project can be easily imported into Eclipse.


RUNNING THE TCP SERVER AND CLIENT:
  1. Open a Java IDE, preferably Eclipse, and open up the project folder
  2. Start the TCPServer.java file by running it through the Eclipse console.
    a. The TCP Server must have the user enter the port desired for the socket at the console dialog.
    b. The server will wait for the client to connect with a timeout timer of 60 seconds.
  3. Start the TCPClient.java file by running it through the Eclipse console.
    a. The TCP Client will ask for the desired port number to connect.
    b. The client will then ask for the command which MUST follow the following format: "Cal localhost <loan_amount> <loan_period> <yearly_interest_rate>"
      i. loan_amount must be an integer, loan_period must be an integer, yearly_interest_rate must be a decimal number.
      ii. the host MUST be localhost for the client to work
    c. The client will then perform the calculation, if the information was entered correctly, the response will be sent to the client.
  4. If one wishes to run another calculation, enter the letter 'y' to do another calculation.

  RUNNING THE UDP SERVER AND CLIENT:
  1. Open a Java IDE, preferably Eclipse, and open up the project folder
  2. Start the UDPServer.java file by running it through the Eclipse console.
    a. The UDP Server must have the user enter the port desired for the socket at the console dialog.
    b. The server will wait for the client to connect with a timeout timer of 60 seconds.
  3. Start the UDPClient.java file by running it through the Eclipse console.
    a. The TCP Client will ask for the desired port number to connect.
    b. The client will then ask for the command which MUST follow the following format: "Cal localhost <loan_amount> <loan_period> <yearly_interest_rate>"
      i. loan_amount must be an integer, loan_period must be an integer, yearly_interest_rate must be a decimal number.
      ii. the host MUST be localhost for the client to work
      iii. if the following format is not followed, the program will not run. Error handling has not been fully implemented yet.
    c. The client will then send the calculation to the server, if the information was entered correctly, the response will be sent to the client.
  4. If one wishes to run another calculation, enter the letter 'y' to do another calculation.

  Notes: The console windows are touchy in Eclipse, but you can run both the server and the client within the Eclipse IDE.
