import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientListener extends Thread{
    Socket tmpSocket;
    Client tmpClient;
    public void run(){
        InputStream inputStream;
        byte[] buffer = new byte[1024];

        UI.print("Waiting for new clients...");
        while (true){
            try {
                tmpSocket = Program.server.accept();

                UI.print(UI.ANSI_GREEN + "New client found! IP: " + UI.ANSI_CYAN + tmpSocket.getInetAddress() + UI.ANSI_RESET);

                inputStream = tmpSocket.getInputStream();
                int bytesRead = inputStream.read(buffer);
                String username = new String(buffer, 0, bytesRead);

                tmpClient = new Client(tmpSocket, username);
                tmpClient.start();

                Program.connectedClients.add(tmpClient);
            }
            catch (IOException e) {
                UI.print("Client listener closed, unexpected error occurred!: " + e);
            }
        }
    }
}