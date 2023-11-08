import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;

        try {
            System.out.println("Ouvindo a porta" + PORTA + "...");
            ServerSocket servidor = new ServerSocket(PORTA);
            cliente = servidor.accept();//Aceita aconexão do cliente
            System.out.println("Conexão estabelecida com" + cliente.getInetAddress());

            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            String mensagem = entrada.readLine();
            System.out.println("Cliente >" + mensagem);

            saida.println("Ola bro Cliente! Você está conectado ao servidor eco");
            saida.flush();
            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Encerrando conexão...");
            try {
                if (cliente != null) {
                    cliente.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
