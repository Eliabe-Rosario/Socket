import java.io.*;
import java.net.Socket;

public class Cliente1 {
    public final static int PORTA = 1025;


    public static void main(String[] args) {
        Socket s = null;
        BufferedReader entrada;
        PrintWriter saida;

        try {
            //ESTABELECER A COMUNICAÇÃO
            s = new Socket("localhost", PORTA);

            //CRIAR STREAMS DE ENTRADA (VEM DO SERVIDOR) E SAÍDA(VAI PARA O SERVIDOR)
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            System.out.println("Conectado ao servidor:" + s.getInetAddress() +
                    ":" + s.getPort());

            //ESCREVER PARA O SERVIDOR
            saida.println("E aí, Cianotista!");
            saida.flush();

            //LER A RESPOSTA DO SERVIDOR
            String respostaDoServidor = entrada.readLine();
            System.out.println("Servidor > " + respostaDoServidor);
            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {

            }
        }


    }
}