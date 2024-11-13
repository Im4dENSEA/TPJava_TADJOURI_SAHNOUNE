
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
        private int port;

        public TCPServer(int port) {
            this.port = port;
        }

        public void launch() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is running on port: " + port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received: " + message);
                        out.println("Echo from server [" + clientSocket.getInetAddress().getHostAddress() + "]: " + message);
                    }

                    clientSocket.close();
                    System.out.println("Client disconnected.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "TCPServer listening on port " + port;
        }

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



