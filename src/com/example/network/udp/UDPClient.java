package com.example.network.udp;

import java.io.Console;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class implements a UDP client that sends messages to a server.
 */
public class UDPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            // Display usage instructions if the arguments are incorrect
            System.out.println("Usage: 'java UDPClient <host> <port>'");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            DatagramSocket socket = new DatagramSocket(); // Create a UDP socket for communication
            Console console = System.console(); // Get the system console for user input

            if (console == null) {
                // Notify if no console is available
                System.out.println("No console available");
                return;
            }

            // Prompt the user to start typing messages
            System.out.println("Enter text (type 'exit' to quit):");

            while (true) {
                // Read the user's input from the console
                System.out.print("Client: ");
                String input = console.readLine();

                // Exit if the user types 'exit'
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                // Convert the message to bytes (UTF-8 encoding)
                byte[] buffer = input.getBytes("UTF-8");

                // Create and send a datagram packet to the server
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
                socket.send(packet);
                System.out.println("Message sent to server.");

                // Receive the server's response
                byte[] responseBuffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);

                // Convert the response to a string and display it
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength(), "UTF-8");
                System.out.println(response);
            }

            socket.close(); // Close the socket after the loop ends
        } catch (Exception e) {
            // Print the stack trace for any errors
            e.printStackTrace();
        }
    }
}
