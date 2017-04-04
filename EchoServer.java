
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class EchoServer {

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    SocketHandler buffer = new SocketHandler(socket);
                    buffer.start();
//                    boolean exitNotHit = true;
//                    String message = null;
//                    String address = socket.getInetAddress().getHostAddress();
//                    System.out.printf("Client connected: %s%n", address);
//                    OutputStream os = socket.getOutputStream();
//                    PrintStream out = new PrintStream(os, true, "UTF-8");
//                    out.printf("Hi %s, thanks for connecting!%n", address);
//
//                    InputStream is = socket.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//                    BufferedReader br = new BufferedReader(isr);
//
//                    while (exitNotHit) {
//                        message = br.readLine();
//                        out.println("Server> " + message);
//
//                        if ("exit".equals(message)) {
//                            exitNotHit = false;
//                            System.out.printf("Client disconnected: %s%n", address);
//                        }
//                    }

                }
            }
        }

    }
}

class SocketHandler extends Thread {

    Socket socket;

    SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            boolean exitNotHit = true;
            String message = null;
            String address = socket.getInetAddress().getHostAddress();
            System.out.printf("Client connected: %s%n", address);
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            out.printf("Hi %s, thanks for connecting!%n", address);
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            while (exitNotHit) {
                message = br.readLine();
                out.println("Server> " + message);

                if ("exit".equals(message)) {
                    exitNotHit = false;
                    System.out.printf("Client disconnected: %s%n", address);
                }
            }
        } catch (Exception e) {
        }
    }
}
