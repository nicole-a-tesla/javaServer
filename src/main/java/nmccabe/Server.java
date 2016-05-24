package nmccabe;

import nmccabe.Handlers.Handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    ServerSocket serverSocket;
    Logger logger;

    public void setUp(HashMap args) throws IOException {
        String baseDir = (String) args.getOrDefault("-d", "/Users/bears8yourface/IdeaProjects/cob_spec/public");
        System.setProperty("baseDir", baseDir);
        logger = new Logger((String) args.getOrDefault("--logs", (baseDir + "/logs.txt")));
    }

    public void start(HashMap args) throws IOException {
        setUp(args);
        serverSocket = new ServerSocket((Integer) args.getOrDefault("-p", 5000));

        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream rawInputStream = clientSocket.getInputStream();

            Request request = new RequestBuilder().build(rawInputStream);
            logger.log(request);
            Handler handler = new Router().getHandlerFor(request);
            Response response = handler.getResponseFor(request);

            OutputStream outStream = clientSocket.getOutputStream();
            ResponsePrinter printer = new ResponsePrinter(response, outStream);
            printer.printToOutStream();
            outStream.close();
        }
    }

}
