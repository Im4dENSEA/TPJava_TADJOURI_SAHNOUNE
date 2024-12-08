package com.example.network.main;

/**
 * The Main class serves as the entry point for the application.
 * It allows the user to select between different modes of operation:
 * TCP server, multi-client TCP server, UDP server, and TCP/UDP clients.
 */
public class Main {

    /**
     * The main method that launches the application.
     * 
     * @param args Command-line arguments to specify the mode of operation and its parameters.
     *             - Mode: tcpServer, tcpMultiServer, udpServer, tcpClient, udpClient
     *             - For servers: <port>
     *             - For clients: <host> <port>
     */
    public static void main(String[] args) {
        // If no arguments are provided, display usage instructions.
        if (args.length == 0) {
            System.out.println("Usage: java -jar MonProjet.jar [tcpServer|tcpMultiServer|udpServer|tcpClient|udpClient] <options>");
            System.out.println("Options for servers: <port>");
            System.out.println("Options for clients: <host> <port>");
            return;
        }

        // Retrieve the mode of operation from the first argument.
        String mode = args[0].toLowerCase();

        // Switch between different modes based on the user's input.
        switch (mode) {
            case "tcpserver":
                // Ensure the correct number of arguments for TCP server.
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpServer <port>");
                    return;
                }
                try {
                    int tcpServerPort = Integer.parseInt(args[1]); // Parse the port number.
                    // Initialize and launch the TCP server.
                    com.example.network.tcp.TCPServer tcpServer = new com.example.network.tcp.TCPServer(tcpServerPort);
                    tcpServer.launch();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Port must be a valid number.");
                }
                break;

            case "tcpmultiserver":
                // Ensure the correct number of arguments for multi-client TCP server.
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpMultiServer <port>");
                    return;
                }
                try {
                    int tcpMultiServerPort = Integer.parseInt(args[1]); // Parse the port number.
                    // Initialize and launch the multi-client TCP server.
                    com.example.network.tcp.TCPMultiServer tcpMultiServer = new com.example.network.tcp.TCPMultiServer(tcpMultiServerPort);
                    tcpMultiServer.launch();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Port must be a valid number.");
                }
                break;

            case "udpserver":
                // Ensure the correct number of arguments for UDP server.
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar udpServer <port>");
                    return;
                }
                try {
                    int udpServerPort = Integer.parseInt(args[1]); // Parse the port number.
                    // Initialize and launch the UDP server.
                    com.example.network.udp.UDPServer udpServer = new com.example.network.udp.UDPServer(udpServerPort);
                    udpServer.launch();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Port must be a valid number.");
                }
                break;

            case "tcpclient":
                // Ensure the correct number of arguments for TCP client.
                if (args.length != 3) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpClient <host> <port>");
                    return;
                }
                try {
                    String tcpClientHost = args[1]; // Get the host address.
                    int tcpClientPort = Integer.parseInt(args[2]); // Parse the port number.
                    // Initialize and start the TCP client.
                    com.example.network.tcp.TCPClient tcpClient = new com.example.network.tcp.TCPClient(tcpClientHost, tcpClientPort);
                    tcpClient.start();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Port must be a valid number.");
                }
                break;

            case "udpclient":
                // Ensure the correct number of arguments for UDP client.
                if (args.length != 3) {
                    System.out.println("Usage: java -jar MonProjet.jar udpClient <host> <port>");
                    return;
                }
                try {
                    String udpClientHost = args[1]; // Get the host address.
                    int udpClientPort = Integer.parseInt(args[2]); // Parse the port number.
                    // Initialize and start the UDP client.
                    com.example.network.udp.UDPClient udpClient = new com.example.network.udp.UDPClient();
                    udpClient.main(new String[]{udpClientHost, String.valueOf(udpClientPort)});
                } catch (NumberFormatException e) {
                    System.out.println("Error: Port must be a valid number.");
                }
                break;

            default:
                // Handle unknown modes by displaying usage instructions.
                System.out.println("Unknown mode: " + mode);
                System.out.println("Usage: java -jar MonProjet.jar [tcpServer|tcpMultiServer|udpServer|tcpClient|udpClient] <options>");
        }
    }
}

