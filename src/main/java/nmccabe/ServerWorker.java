package nmccabe;

import nmccabe.Handlers.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServerWorker implements Runnable {
    HttpInStream clientIn;
    HttpOutStream clientOut;
    Logger logger;

    public ServerWorker(HttpInStream clientIn, HttpOutStream clientOut, Logger logger) {
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            Request request = new RequestBuilder().build(clientIn);
            logger.log(request);
            Handler handler = new Router().getHandlerFor(request);
            Response response = handler.getResponseFor(request);

            new ResponsePrinter(response, clientOut).printToOutStream();
            clientOut.tearDownStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
