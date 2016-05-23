package nmccabe.Handlers;

import nmccabe.IResource;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

import java.io.IOException;
import java.util.HashMap;

public class Handler {
    public static final String OK_STATUS = "200 OK";
    public static final String NOT_FOUND_STATUS = "404 Not Found";
    public static final String NOT_ALLOWED_STATUS = "405 Method Not Allowed";
    public static final String PARTIAL_STATUS = "206 Partial";
    public static final String NO_CONTENT_STATUS = "204 No Content";

    public Response getResponseFor(Request request) throws IOException {
        return buildResponseForStatus(NOT_ALLOWED_STATUS);
    }

    public Response buildResponseForStatus(String status) {
        return new ResponseBuilder(status).build();
    }

    public Response buildResponseForStatus(String status, IResource resource) {
        return new ResponseBuilder(status, resource).build();
    }

    public Response buildResponseForStatus(String status, IResource resource, HashMap requestHeaders) {
        return new ResponseBuilder(status, resource, requestHeaders).build();
    }
}
