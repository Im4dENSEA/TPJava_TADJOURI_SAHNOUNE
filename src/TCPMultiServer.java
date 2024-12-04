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
            System.out.println("Multi-client TCP Server running on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                ConnectionThread connection = new ConnectionThread(clientSocket);
                connection.start();
            }
        } catch (Exception e) {
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
            System.out.println("Usage: 'java TCPMultiServer <PORT>'");
            return;
        }

        int port = Integer.parseInt(args[0]);
        TCPMultiServer server = new TCPMultiServer(port);
        server.launch();
    }
}
