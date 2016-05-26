package nmccabe.Handlers;

import nmccabe.FileWriter;
import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;
import java.util.Objects;

public class AddingStuffHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws IOException {
        String status = (Objects.equals(request.method(), "PATCH")) ? HttpCodes.NO_CONTENT : HttpCodes.OK;
        Response response = buildResponseForStatus(status);
        executePost(request.route(), request.body());

        return response;
    }

    private void executePost(String route, String postThis) throws IOException {
        String postTo = System.getProperty("baseDir") + route;
        new FileWriter().write(postTo, postThis);
    }
}
