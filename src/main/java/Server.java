import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    ServerSocket serverSocket;

    public void setUp(HashMap args) throws IOException {
        String baseDir = (String) args.getOrDefault("-d", "/Users/bears8yourface/IdeaProjects/javaServer/cob_spec/public/");
        System.setProperty("baseDir", baseDir);

    }

    public void start(HashMap args) throws IOException {
        setUp(args);
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

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
