import java.net.ServerSocket;
import java.net.Socket;

public class TCPMultiServer {
    private int port;

    public TCPMultiServer(int port) {
        this.port = port;
    }

    public void launch() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Multi-client TCP Server running on port: " + port);

            // Boucle infinie pour accepter les connexions clients
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accepter une connexion client
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Démarrer un nouveau thread pour gérer le client
                ConnectionThread connection = new ConnectionThread(clientSocket);
                connection.start(); // Lancer le thread
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
