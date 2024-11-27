import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionThread extends Thread {
    private Socket clientSocket;

    public ConnectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        ) {
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Thread started for client: " + clientAddress);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from " + clientAddress + ": " + message);
                out.println("Echo [" + clientAddress + "]: " + message); 
            }

            System.out.println("Client disconnected: " + clientAddress);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
