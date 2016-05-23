package nmccabe.Handlers;

import nmccabe.FileWriter;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;

public class AddingStuffHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws IOException {
        Response response = buildResponseForStatus(OK_STATUS);
        executePost(request.route, request.body);

        return response;
    }

    private void executePost(String route, String postThis) throws IOException {
        String postTo = System.getProperty("baseDir") + route;
        new FileWriter().write(postTo, postThis);
    }
}
