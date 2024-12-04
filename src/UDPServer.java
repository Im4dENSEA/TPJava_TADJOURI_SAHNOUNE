import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class implements a UDP server that listens for messages from clients
 * and displays them.
 */
public class UDPServer {
    private int port;

    /**
     * Constructor to initialize the server with a port number.
     *
     * @param port The port number on which the server listens.
     */
    public UDPServer(int port) {
        this.port = port;
    }

    /**
     * Starts the UDP server, listening for and displaying incoming messages.
     */
    public void launch() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is running on port: " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

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

    /**
     * Main method to start the UDP server.
     *
     * @param args Command-line arguments: port number.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java UDPServer <PORT>'");
            return;
        }

        int port = Integer.parseInt(args[0]);
        UDPServer server = new UDPServer(port);
        System.out.println(server);
        server.launch();
    }
}
