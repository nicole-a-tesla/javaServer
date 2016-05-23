package nmccabe.Handlers;

import nmccabe.FileWriter;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;
import java.util.Objects;

public class AddingStuffHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws IOException {
        String status = (Objects.equals(request.method, "PATCH")) ? NO_CONTENT_STATUS : OK_STATUS;
        Response response = buildResponseForStatus(status);
        executePost(request.route, request.body);

        return response;
    }

    private void executePost(String route, String postThis) throws IOException {
        String postTo = System.getProperty("baseDir") + route;
        new FileWriter().write(postTo, postThis);
    }
}
