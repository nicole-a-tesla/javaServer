package nmccabe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executioner = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket((Integer) args.getOrDefault("-p", 5000));

        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            executioner.execute(new ServerWorker(clientSocket, logger));
        }
    }

}

