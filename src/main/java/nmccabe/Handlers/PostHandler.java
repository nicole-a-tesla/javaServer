package nmccabe.Handlers;

import nmccabe.FileWriter;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;

public class PostHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws IOException {
        Response response = buildResponseForStatus(OK_STATUS);

        String postTo = System.getProperty("baseDir") + request.route;
        new FileWriter().write(postTo, request.body);

        return response;
    }
}
