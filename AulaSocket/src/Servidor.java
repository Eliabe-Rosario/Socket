import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static final int PORTA = 1025;
    public static Socket cliente;

    public static void main(String[] args) {
        //CRIAR LISTA DE THREADS (clientes) ATIVAS
        try {
            ArrayList<ServerThread> listaThreads = new ArrayList<>();
            ServerSocket servidor = new ServerSocket(PORTA);
            //Deixar o servidor em loop para aceitar novas conexões
            while (true) {
                // Estabelecer o Socket
                System.out.println("Ouvindo a porta" + PORTA + ". . .");
                cliente = servidor.accept();
                //Criar a thread para o socket estabelecido
                ServerThread thread = new ServerThread(cliente, listaThreads);
                //Adicionar a thread á lista de clientes
                listaThreads.add(thread);
                //Inicializar a thread
                thread.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            System.out.println("Encerrando colnexão . . .");
            try {
                if (cliente != null) {
                    cliente.close();
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }

            System.out.println("Conexão encerrada!");
        }

    }
}