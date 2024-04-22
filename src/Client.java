import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client extends Thread{
    public Socket socket;
    public String username;

    InputStream inputStream;
    public byte[] buffer = new byte[1024];
    public String message;

    public Client(Socket socket, String username){
        this.socket = socket;
        this.username = username;

        System.out.println(UI.ANSI_GREEN + "Client " +
                UI.ANSI_CYAN + this.username +
                UI.ANSI_GREEN + " connected! " +
                UI.ANSI_RESET);
    }

    @Override
    public void run() {
        try {
            while (true){
                inputStream = socket.getInputStream();
                int bytesRead = inputStream.read(buffer);
                message = new String(buffer, 0, bytesRead);
                if (!message.isEmpty()){
                    System.out.println(UI.ANSI_GREEN + "(" + this.username + "): " + UI.ANSI_RESET + message);
                }
            }
        }
        catch (IOException e){
            System.out.println(UI.ANSI_RED + "Error communicating with client! Error: " + UI.ANSI_YELLOW + e + UI.ANSI_RESET);
        }
    }
}
