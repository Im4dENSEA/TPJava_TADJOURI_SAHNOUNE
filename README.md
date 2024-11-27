UDP Server/Client  
# UDP/TCP Chat App with Java sockets  
This lab project is a chat application developed in Java that uses sockets to enable communication between a client and a server. The lab covers setting up network communication using UDP and TCP protocols and managing multiple client connections through a multithreaded TCP server.
## Prerequisites ##
* A Java development Kit (JDK)
* A Java development environment (here we used Visual Studio Code)
* A Machine running Windows, Linux ect (We worked on Windows)
## **1 – Creating a UDP Client-Server**  

### Structure ###
* UDPServer.java: UDP server that listens for messages from clients and displays them along with the client's address.
* UDPClient.java: UDP client that reads user input and sends messages to the UDP server.

How to run:
1. Compile the code : Place all .java files in the same directory.Open a terminal and navigate to the directory with cd command.
Use command javac to compile the .java file:
javac UDPClient.java UDPServer.java
2. Run the application: Start the UDP server on a specific port (java UDPServer 9090) and in an other terminal, run the UDP client (java UDPClient localhost 9090) and send messages.

Here are the terminal screenshots:

![My image](images/UDP_Client.png)

![My image](images/UDP_Server.png)

## **2 – Creating a TCP Client-Server** ##

### Structure ###
* TCPServer.java: A basic TCP server that handles one client at a time.
* TCPClient.java: A client that establishes a TCP connection and exchanges messages with the server.

How to run:
1. Compile the code : Place all .java files in the same directory.Open a terminal and navigate to the directory with cd command.
Use command javac to compile the .java file:
javac UDPClient.java UDPServer.java
2. Run the application: Start the TCP server on a specific port (java TCPServer 9090) and in an other terminal, run the TCP client (java TCPClient localhost 9090) and send messages.

Here are the terminal screenshots:

![My image](images/TCP_Client.png)

![My image](images/TCP_Server.png)
