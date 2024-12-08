package com.example.network.main;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar MonProjet.jar [tcpServer|tcpMultiServer|udpServer|tcpClient|udpClient] <options>");
            System.out.println("Options for servers: <port>");
            System.out.println("Options for clients: <host> <port>");
            return;
        }

        String mode = args[0].toLowerCase();

        switch (mode) {
            case "tcpserver":
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpServer <port>");
                    return;
                }
                int tcpServerPort = Integer.parseInt(args[1]);
                com.example.network.tcp.TCPServer tcpServer = new com.example.network.tcp.TCPServer(tcpServerPort);
                tcpServer.launch();
                break;

            case "tcpmultiserver":
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpMultiServer <port>");
                    return;
                }
                int tcpMultiServerPort = Integer.parseInt(args[1]);
                com.example.network.tcp.TCPMultiServer tcpMultiServer = new com.example.network.tcp.TCPMultiServer(tcpMultiServerPort);
                tcpMultiServer.launch();
                break;

            case "udpserver":
                if (args.length != 2) {
                    System.out.println("Usage: java -jar MonProjet.jar udpServer <port>");
                    return;
                }
                int udpServerPort = Integer.parseInt(args[1]);
                com.example.network.udp.UDPServer udpServer = new com.example.network.udp.UDPServer(udpServerPort);
                udpServer.launch();
                break;

            case "tcpclient":
                if (args.length != 3) {
                    System.out.println("Usage: java -jar MonProjet.jar tcpClient <host> <port>");
                    return;
                }
                String tcpClientHost = args[1];
                int tcpClientPort = Integer.parseInt(args[2]);
                com.example.network.tcp.TCPClient tcpClient = new com.example.network.tcp.TCPClient(tcpClientHost, tcpClientPort);
                tcpClient.start();
                break;

            case "udpclient":
                if (args.length != 3) {
                    System.out.println("Usage: java -jar MonProjet.jar udpClient <host> <port>");
                    return;
                }
                String udpClientHost = args[1];
                int udpClientPort = Integer.parseInt(args[2]);
                com.example.network.udp.UDPClient udpClient = new com.example.network.udp.UDPClient();
                udpClient.main(new String[]{udpClientHost, String.valueOf(udpClientPort)});
                break;

            default:
                System.out.println("Unknown mode: " + mode);
                System.out.println("Usage: java -jar MonProjet.jar [tcpServer|tcpMultiServer|udpServer|tcpClient|udpClient] <options>");
        }
    }
}
