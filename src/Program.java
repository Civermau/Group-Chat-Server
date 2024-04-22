import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static ServerSocket server;
    public static List<Client> connectedClients;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(7777);
            connectedClients = new ArrayList<Client>();

            System.out.println("Starting server at: " + server.getInetAddress().getHostAddress() + " with port: " + server.getLocalPort());
            System.out.println();

            ClientListener clientListener =  new ClientListener();
            clientListener.start();
        }
        catch (IOException e){
            System.out.println("Server couldn't start, please try again");
        }
    }
}