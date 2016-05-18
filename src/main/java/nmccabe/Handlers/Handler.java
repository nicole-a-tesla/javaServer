package nmccabe.Handlers;

import nmccabe.IResource;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

import java.io.IOException;

public class Handler {
    public static final String OK_STATUS = "200 OK";
    public static final String NOT_FOUND_STATUS = "404 Not Found";
    public static final String NOT_ALLOWED_STATUS = "405 Method Not Allowed";

    public Response getResponseFor(Request request) throws IOException {
        return buildResponseForStatus(NOT_ALLOWED_STATUS);
    }

    public Response buildResponseForStatus(String status) {
        return new ResponseBuilder(status).build();
    }

    public Response buildResponseForStatus(String status, IResource resource) {
        return new ResponseBuilder(status, resource).build();
    }
}
