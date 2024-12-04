import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements a simple TCP server that handles one client at a time.
 * It receives messages from the client, echoes them back, and displays them on the console.
 */
public class TCPServer {
    private int port;

    /**
     * Constructor to initialize the TCP server with a port number.
     *
     * @param port The port number on which the server listens.
     */
    public TCPServer(int port) {
        this.port = port;
    }

    /**
     * Starts the TCP server, listens for client connections, and handles communication.
     */
    public void launch() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Waits for a client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Communication streams for input and output
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                String message;
                // Reads and processes client messages
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    out.println("Echo from server [" + clientSocket.getInetAddress().getHostAddress() + "]: " + message);
                }

                clientSocket.close(); // Closes the connection
                System.out.println("Client disconnected.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handles errors
        }
    }

    /**
     * Provides a string representation of the TCP server.
     *
     * @return A string describing the server's state.
     */
    @Override
    public String toString() {
        return "TCPServer listening on port " + port;
    }

    /**
     * Main method to start the TCP server.
     *
     * @param args Command-line arguments: port number.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java TCPServer <PORT>'");
            return;
        }

        int port = Integer.parseInt(args[0]);
        TCPServer server = new TCPServer(port);
        System.out.println(server);
        server.launch();
    }
}


