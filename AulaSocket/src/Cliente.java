import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static final int PORTA = 1025;
    public static Socket socket = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", PORTA);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            Scanner scanner = new Scanner(System.in);
            String mensagem = " mensagem";
            String nomeCliente = null;

            //criar thread para cliente
            ClientThread thread = new ClientThread(socket);

            //iniciar a thread do cliente
            thread.start();

            //Ler entradas sp teclado enquanto o usu´rio está conectado
            while (!mensagem.equals("Fim")){
                if (nomeCliente == null){
                    System.out.println("Qual o seu nome?");
                    nomeCliente =scanner.nextLine();
                }else{
                    mensagem =scanner.nextLine();
                    saida.println(nomeCliente+"> "+mensagem);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (socket != null){
                    socket.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    }
}
