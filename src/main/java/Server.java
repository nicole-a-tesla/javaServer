import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    ServerSocket serverSocket;

    public void start(HashMap args) throws IOException {

        serverSocket = new ServerSocket((Integer) args.getOrDefault("-p", 5000));

        while (true) {
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Request request = new RequestBuilder().build(in);
            Response response = new Worker().getResponse(request);

            out.print(response.toString());
            out.close();
        }
    }
}
