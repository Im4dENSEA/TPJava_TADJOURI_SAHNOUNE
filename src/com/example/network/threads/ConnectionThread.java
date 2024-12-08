package com.example.network.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class represents a thread that handles a single client connection.
 * It reads messages from the client, processes them, and sends a response.
 */
public class ConnectionThread extends Thread {
    private Socket clientSocket;

    /**
     * Constructor to initialize the connection thread with the client socket.
     *
     * @param clientSocket The socket representing the client's connection.
     */
    public ConnectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * The main logic for handling client communication. Reads messages, echoes them,
     * and manages the connection lifecycle.
     */
    @Override
    public void run() {
        try (
            // Initializes input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        ) {
            // Retrieve and log the client's IP address
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Thread started for client: " + clientAddress);

            String message;
            // Continuously read and process messages from the client
            while ((message = in.readLine()) != null) {
                // Log the received message
                System.out.println("Received from " + clientAddress + ": " + message);
                // Echo the message back to the client
                out.println("Echo [" + clientAddress + "]: " + message);
            }

            // Log when the client disconnects
            System.out.println("Client disconnected: " + clientAddress);
        } catch (Exception e) {
            // Handle exceptions during communication
            e.printStackTrace();
        } finally {
            // Ensure the client socket is closed to free resources
            try {
                clientSocket.close();
            } catch (Exception e) {
                // Log any issues during socket closure
                e.printStackTrace();
            }
        }
    }
}

