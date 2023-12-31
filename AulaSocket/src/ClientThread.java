import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    BufferedReader entrada;

    public ClientThread(Socket socket) throws IOException {
        this.socket =socket;
        entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        //obter a resposta do servidor
        try {


            while (true){
                String resposta = entrada.readLine();
                System.out.println(resposta);

        }
    }catch (IOException e){
        throw new RuntimeException(e);

        }finally {
            try {
                entrada.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
