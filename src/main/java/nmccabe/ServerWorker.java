package nmccabe;

import nmccabe.Handlers.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker implements Runnable {
    Socket clientSocket;
    Logger logger;

    public ServerWorker(Socket clientSocket, Logger logger) {
        this.clientSocket = clientSocket;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            InputStream rawInputStream = clientSocket.getInputStream();
            Request request = new RequestBuilder().build(rawInputStream);
            logger.log(request);
            Handler handler = new Router().getHandlerFor(request);
            Response response = handler.getResponseFor(request);

            OutputStream outStream = clientSocket.getOutputStream();
            ResponsePrinter printer = new ResponsePrinter(response, outStream);
            printer.printToOutStream();
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
