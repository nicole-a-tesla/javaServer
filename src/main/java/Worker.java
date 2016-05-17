import java.io.IOException;

public class Worker {

    public Response getResponse(Request request) throws IOException {
        Handler handler = new Router().getHandlerFor(request);
        return handler.getResponseFor(request);
    }
}
