import java.io.IOException;
import java.net.*;


public class UDPServer {
    private DatagramSocket socket;
    private int port;

    public UDPServer(int port) {
        this.port = port;
    }
    public UDPServer(){
        this(8080);

    }

    public void launch() throws IOException {
        socket = new DatagramSocket(port);
        System.out.println("Server is on port" + port);

        while (true){
            byte[] buffer =new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            if (message.length() >1024) {
                message = message.substring(0, 1024);
            }
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("You received a message from"+clientAddress+":"+clientPort+":"+"-"+message);
        }



    }
    @Override
    public String toString() {
        return "UDPServer listening on port " + port;
    }
}
