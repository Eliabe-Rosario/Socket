import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> listaThreads;
    private PrintWriter saida;
    private String mensagem = "";

    public ServerThread(Socket socket, ArrayList<ServerThread> listaThreads){
       this.socket = socket;
       this.listaThreads =listaThreads;
    }

 //ESTABELECER O COMPORTAMENTO DA THREAD
 @Override
 public void run(){
     try {


        //LER O QUE VEM DO CLIENTE
        BufferedReader entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
     //escrever para os clientes
        saida = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()), true);
     while (true){
         mensagem = entrada.readLine();
         if(mensagem == null || mensagem.equals("Fim")){
             break;
         }
         //exibir a mensagem para todos os clientes conectados
         for(ServerThread st: listaThreads){
             st.saida.println(mensagem);
         }
         System.out.println("Servidor recebeu a mensagem"+mensagem);
     }

     entrada.close();
     saida.close();

        //EXIBIR A MENSAGEM PARA TODOS OS CLIENTES CONECTADOS
 }catch (IOException e){
        throw new RuntimeException(e);
        }
     }
 }

