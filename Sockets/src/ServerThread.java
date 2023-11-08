import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> listaThreads;
    private PrintWriter saida;
    private String mensagem = "";
    public boolean fim = false;

    public ServerThread(Socket socket, ArrayList<ServerThread> listaThreads){
        this.socket = socket;
        this.listaThreads = listaThreads;
    }

    @Override
    public void run(){
        try{
            BufferedReader entra = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida =new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        while (true){
            mensagem = entra.readLine();

            if (mensagem == null|| mensagem.equals("Fim")){
                break;
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
