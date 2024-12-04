import java.io.Console;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class implements a UDP client that sends messages to a server.
 */
public class UDPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: 'java UDPClient <host> <port>'");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            DatagramSocket socket = new DatagramSocket();
            Console console = System.console();

            if (console == null) {
                System.out.println("No console available");
                return;
            }

            System.out.println("Enter text (type 'exit' to quit):");

            while (true) {
                String input = console.readLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                byte[] buffer = input.getBytes("UTF-8");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
                socket.send(packet);
                System.out.println("Sent: " + input);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
