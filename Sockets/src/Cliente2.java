import java.io.*;
import java.net.Socket;

public class Cliente2 {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        Socket s =null;
        PrintWriter saida;
        BufferedReader entrada;
        BufferedReader teclado; //Ler entradas do teclado
        String mensagem;
        boolean fim = false;

        try {
                s = new Socket("localhost", PORTA);

                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado ao servidor:"+s.getInetAddress()+
                    ":"+ s.getPort());

            String resposta = entrada.readLine();
            System.out.println("Servidor >"+resposta);

            while (true){
                System.out.println("Cliente >");
                System.out.flush();
                mensagem =  teclado.readLine();
                if (mensagem.equals("Fim")){
                    fim = true;
                }
                saida.println(mensagem);
                saida.flush();

                mensagem =entrada.readLine();
                if(mensagem == null){
                    System.out.println("Conexão encerrada pelo servidor");
                    break;
                }
                if(fim){
                    break;
                }
                System.out.println("Servidor >"+ mensagem);
            }

            entrada.close();
            saida.close();
            teclado.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (s !=null){
                    s.close();
                }

            }catch (IOException e){
                throw new RuntimeException(e);

            }

        }
    }
}