import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread{
    public Socket socket;
    public String username;

    InputStream inputStream;
    OutputStream outputStream;
    public byte[] buffer = new byte[1024];
    public String message;

    public Client(Socket socket, String username){
        this.socket = socket;
        this.username = username;

        UI.print(UI.ANSI_GREEN + "Client " +
                UI.ANSI_CYAN + this.username +
                UI.ANSI_GREEN + " connected! " +
                UI.ANSI_RESET);
    }

    @Override
    public void run() {
        try {
            while (true){
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                int bytesRead = inputStream.read(buffer);
                message = new String(buffer, 0, bytesRead);

                message = UI.ANSI_GREEN + "(" + this.username + "): " + UI.ANSI_RESET + message;
                UI.print(message);

                for (Client client : Program.connectedClients){
                    if(!client.equals(this)) {
                        outputStream = client.socket.getOutputStream();
                        outputStream.write(message.getBytes());
                    }
                }
            }
        }
        catch (IOException e){
            if (e.getMessage().equals("Connection reset")) {
                UI.print(UI.ANSI_GREEN + "Client " + UI.ANSI_CYAN + this.username + UI.ANSI_GREEN + " disconnected!");
            } else {
                UI.print(UI.ANSI_RED + "Error communicating with client! Error: " + UI.ANSI_YELLOW + e + UI.ANSI_RESET);
            }
        }
    }
}
