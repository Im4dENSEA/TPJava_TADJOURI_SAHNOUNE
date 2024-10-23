import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private int port;



    public UDPServer(int port) {
        this.port = port;
    }


    public void launch() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is running on port: " + port);

            byte[] buffer = new byte[1024]; //Buffer to store the incoming data, we assume that the server can store a maximum of 1024 bytes.

            // Usage of an infinite loop to keep the server running.
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // We create a DatagramPacket with the size of the buffer.
                socket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort(); //We get the client IP address and port with InetAddress method.

                String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                //We do the truncation for message with a length higher than 1024 bytes, so we divide the message at 1024 bytes each time until the message length is under 1024 bytes.

                if (message.length() > 1024) {
                    message = message.substring(0, 1024);
                }

                System.out.printf("Received from %s:%d - %s%n", clientAddress.getHostAddress(), clientPort, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "UDPServer listening on port " + port;
    }

    // On the main method, we print a Usage in case the user don't specify the port number when he try to launch java UDPServer.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java UDPServer <PORT>'"+"\n -Port                                  port à utiliser pour le serveur");
        }

        else if(args.length == 1) {
            int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
            UDPServer server = new UDPServer(port);
            System.out.println(server);
            server.launch();
        }
    }
}
