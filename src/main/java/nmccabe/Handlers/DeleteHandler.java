package nmccabe.Handlers;

import nmccabe.FileWriter;
import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;

public class DeleteHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws IOException {
        Response response = buildResponseForStatus(HttpCodes.OK);
        executeDelete(request.route());

        return response;
    }

    private void executeDelete(String route) throws IOException {

        String deleteFrom = System.getProperty("baseDir") + route;
        new FileWriter().deleteFrom(deleteFrom);
    }
}
