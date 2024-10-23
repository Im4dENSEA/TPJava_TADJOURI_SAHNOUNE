import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private int port;


    public UDPServer() {
        this.port = 8080; //Default port
    }


    public UDPServer(int port) {
        this.port = port;
    }


    public void launch() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is running on port: " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                if (message.length() > 1024) {
                    message = message.substring(0, 1024);
                }

                System.out.printf("Received from %s:%d - %s%n", clientAddress.getHostAddress(), clientPort, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "UDPServer listening on port " + port;
    }

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        UDPServer server = new UDPServer(port);
        System.out.println(server);
        server.launch();
    }
}
