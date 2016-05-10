import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            String response = "HTTP/1.1 200 OK\r\n";
            out.print(response);
            out.close();
        }

    }

}
