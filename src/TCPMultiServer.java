import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements a multi-threaded TCP server capable of handling
 * multiple client connections simultaneously.
 */
public class TCPMultiServer {
    private int port;

    /**
     * Constructor to initialize the server with a port number.
     *
     * @param port The port number on which the server listens.
     */
    public TCPMultiServer(int port) {
        this.port = port;
    }

    /**
     * Launches the server, accepting multiple client connections and spawning
     * a new thread for each connection.
     */
    public void launch() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Log that the server is running and ready to accept connections
            System.out.println("Multi-client TCP Server running on port: " + port);

            // Infinite loop to accept client connections
            while (true) {
                // Waits for a client connection and accepts it
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create and start a new thread for handling the client connection
                ConnectionThread connection = new ConnectionThread(clientSocket);
                connection.start();
            }
        } catch (Exception e) {
            // Handle any errors that occur during server execution
            e.printStackTrace();
        }
    }

    /**
     * Main method to start the multi-client TCP server.
     *
     * @param args Command-line arguments: port number.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            // Display usage instructions if no or incorrect arguments are provided
            System.out.println("Usage: 'java TCPMultiServer <PORT>'");
            return;
        }

        // Parse the port number from the command-line arguments
        int port = Integer.parseInt(args[0]);

        // Initialize and launch the server
        TCPMultiServer server = new TCPMultiServer(port);
        server.launch();
    }
}
