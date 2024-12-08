package com.example.network.tcp;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class implements a TCP client that connects to a server,
 * sends messages, and receives responses.
 */
public class TCPClient {
    private String serverAddress;
    private int serverPort;

    /**
     * Constructor to initialize the client with a server address and port.
     *
     * @param serverAddress The IP address or hostname of the server.
     * @param serverPort    The port number of the server.
     */
    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Starts the client, allowing the user to send messages to the server.
     * Messages are read from the console.
     */
    public void start() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Establishes connection to the server
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);

            // Streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Reader for user input from the console
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            // Prompt the user to start entering messages
            System.out.println("Enter messages to send to the server. Type 'exit' to quit.");

            while (true) {
                System.out.print("Client: ");
                // Reads the user's input
                message = userInput.readLine();

                // If the user types 'exit', terminate the client
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Sends the user's message to the server
                out.println(message);

                // Reads the server's response and displays it
                String response = in.readLine();
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            // Prints the stack trace in case of errors
            e.printStackTrace();
        }
    }

    /**
     * Main method to start the TCP client.
     *
     * @param args Command-line arguments: server address and port.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            // Displays usage instructions if arguments are incorrect
            System.out.println("Usage: 'java TCPClient <SERVER_ADDRESS> <PORT>'");
            return;
        }

        // Retrieves the server address and port from the command-line arguments
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        // Initializes and starts the TCP client
        TCPClient client = new TCPClient(serverAddress, serverPort);
        client.start();
    }
}

