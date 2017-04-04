
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            String user = "";
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader bur = new BufferedReader(isr);
            System.out.println(bur.readLine());
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader brin = new BufferedReader(in);
            OutputStream out = socket.getOutputStream();
            PrintStream output = new PrintStream(out, true, "UTF-8");

            System.out.print("Client> ");            
            while(!"exit".equals(user)){
                user = brin.readLine();
                output.println(user);
                System.out.println(bur.readLine());
                System.out.print("Client> "); 
            }
            
            
        }
    }
}















