import java.io.Console;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: write 'java UDPClient <host> <port>'");  //If we don't write 2 arguments for host and port, we display a usage to inform how to do it.
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            DatagramSocket socket = new DatagramSocket();
            Console console = System.console(); //We use a console instance, if it's not available we display an error message

            if (console == null) {
                System.out.println("No console available");
                return;
            }

            System.out.println("Enter text (type 'exit' to quit):");

            while (true) {
                String input = console.readLine(); // We read the text written by the user, if the user type 'exit' or nothing, the loop end.

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                byte[] buffer = input.getBytes("UTF-8"); // To send the packet, we create an array of bytes encoded in UTF-8, we use the .send method of DatagramSocket after this.
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
                socket.send(packet);
                System.out.println("Sent: " + input);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace(); // For each exception happening during the bloc 'try' we display an error message.
        }
    }
}
