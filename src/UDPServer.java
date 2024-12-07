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
            // Start the server and notify that it is running
            System.out.println("UDP Server is running on port: " + port);

            byte[] buffer = new byte[1024]; // Buffer to store incoming data (max 1024 bytes)

            while (true) {
                // Prepare a packet to receive data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Wait for incoming data

                // Extract client details
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                // Convert the received data to a string (UTF-8 encoding)
                String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                // Log the received message with client information
                System.out.printf("Client [%s:%d]: %s%n", clientAddress.getHostAddress(), clientPort, message);

                // Send acknowledgment or echo the message back
                String response = "Server: Received your message -> " + message;
                byte[] responseBytes = response.getBytes("UTF-8");
                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
                socket.send(responsePacket);
                System.out.println("Server: Sent acknowledgment to client.");
            }
        } catch (Exception e) {
            // Print the stack trace for any errors
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
            // Display usage instructions if the arguments are incorrect
            System.out.println("Usage: 'java UDPServer <PORT>'");
            return;
        }

        int port = Integer.parseInt(args[0]);
        UDPServer server = new UDPServer(port);
        System.out.println(server);
        server.launch();
    }
}
