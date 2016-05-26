package nmccabe.Handlers;

import nmccabe.*;

import java.io.IOException;
import java.util.HashMap;

public class Handler {

    public Response getResponseFor(Request request) throws IOException {
        return buildResponseForStatus(HttpCodes.METHOD_NOT_ALLOWED);
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
