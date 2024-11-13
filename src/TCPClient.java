import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    private String serverAddress;
    private int serverPort;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            System.out.println("Enter messages to send to the server. Type 'exit' to quit.");

            while (true) {
                System.out.print("Client: ");
                message = userInput.readLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                out.println(message);
                String response = in.readLine();
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: 'java TCPClient <SERVER_ADDRESS> <PORT>'");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        TCPClient client = new TCPClient(serverAddress, serverPort);
        client.start();
    }
}
