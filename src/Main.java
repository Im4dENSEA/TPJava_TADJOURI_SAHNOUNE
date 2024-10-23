public class Main {
    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number, using default port 8080");
            }
        }
        UDPServer server = new UDPServer(port);
        System.out.println(server);
    }}