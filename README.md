UDP Server/Client  
# UDP/TCP Chat App with Java sockets  
This lab project is a chat application developed in Java that uses sockets to enable communication between a client and a server. The lab covers setting up network communication using UDP and TCP protocols and managing multiple client connections through a multithreaded TCP server.


## Prerequisites ##
* A Java development Kit (JDK)
* A Java development environment (here we used Visual Studio Code)
* A Machine running Windows, Linux ect (We worked on Windows)

## Main Features 
### 1. **TCP Communication**
- **Standard TCP Server**: Handles a single client connection at a time.
- **TCP Client**: Connects to a TCP server and sends/receives messages.

### 2. **Multi-Client TCP Server**
- A server capable of handling multiple client connections simultaneously using dedicated threads.

### 3. **UDP Communication**
- **UDP Server**: Listens for datagrams sent by clients.
- **UDP Client**: Sends datagrams to a server.


## Project Organization
### **Packages**
- The project is organized into packages for better readability and modularity:
  - **`main`**: Contains the `Main` class to choose the execution mode.
  - **`tcp`**: Contains TCP-related classes(TCPServer, TCPMultiServer, TCPClient).
  - **`udp`**: Contains UDP-related classes(UDPServer, UDPClient).
  - **`threads`**: Contains utility classes for thread management(ConnectionThread).

# How to Compile and Run
## **Compilation without .jar file**
To compile all classes with their package structure, follow these steps:
* Navigate to the src directory with cd
* Compile the classes into the bin directory: Use the following command to compile all classes within the package structure: javac -d bin com/example/network/**/*.java
* If the above command does not work, you can compile each package separately. For example: javac -d ../bin com/example/network/tcp/*.java
javac -d ../bin com/example/network/udp/*.java
javac -d ../bin com/example/network/main/*.java
* You can directly execute the compiled classes using the java command. Ensure you are in the bin directory or specify its path.
* Run a specific class: java com.example.network.tcp.TCPServer [numéro de port]



### **1 – Creating a UDP Client-Server**  

* UDPServer.java: UDP server that listens for messages from clients and displays them along with the client's address.
* UDPClient.java: UDP client that reads user input and sends messages to the UDP server.

How to run:

1. Run the application: Start the UDP server on a specific port (java UDPServer 9090) and in an other terminal, run the UDP client (java UDPClient localhost 9090) and send messages.

Here are the terminal screenshots:

![My image](images/UDP_Client.png)

![My image](images/UDP_Server.png)



## **2 – Creating a TCP Client-Server** ##

* TCPServer.java: A basic TCP server that handles one client at a time.
* TCPClient.java: A client that establishes a TCP connection and exchanges messages with the server.

How to run:
2. Run the application: Start the TCP server on a specific port (java TCPServer 9090) and in an other terminal, run the TCP client (java TCPClient localhost 9090) and send messages.

Here are the terminal screenshots:

![My image](images/TCP_Client.png)

![My image](images/TCP_Server.png)



## **3 – Server accepting multiple TCP connections** ##

* TCPMultiServer.java: A TCP server capable of handling multiple clients simultaneously.
* ConnectionThread.java: A thread class that manages communication with an individual client.


2. Run the application: Start the TCP server on a specific port (java TCPServer 9090) and in many other terminal, run the TCP client (java TCPClient localhost 9090) and send messages.

Here are the terminal screenshots:

![My image](images/TCPMultiClient_Server.png)









