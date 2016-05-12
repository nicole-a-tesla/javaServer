import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public String BASE_DIR;
    public int PORT;
    ServerSocket serverSocket;

    public void setUp(HashMap args) throws IOException {
        BASE_DIR = (String) args.getOrDefault("-d", "/Users/bears8yourface/IdeaProjects/javaServer/cob_spec/public/");
        PORT = (Integer) args.getOrDefault("-p", 5000);

    }

    public void start(HashMap args) throws IOException {
        setUp(args);
        serverSocket = new ServerSocket(PORT);

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

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
