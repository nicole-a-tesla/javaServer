package nmccabe.Handlers;

import nmccabe.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Objects;

public class ResourceHandler extends Handler {

    @Override
    public Response getResponseFor(Request request) throws IOException {
        File file = new File(System.getProperty("baseDir") + request.route());
        String status = determineOKOrPARTIALStatus(request);

        if (file.exists()) {
            Resource resource = new Resource(file.getPath());
            return bodyfulResponse(status, resource, request.headers());
        } else {
            return bodylessResponse(HttpCodes.NOT_FOUND);
        }
    }

    private String determineOKOrPARTIALStatus(Request request) {
        if (Objects.equals(request.getHeader("Range:"), "Header Not Found")) {
            return HttpCodes.OK;
        } else {
            return HttpCodes.PARTIAL;
        }
    }

    private Response bodyfulResponse(String status, Resource resource, HashMap requestHeaders) {
        return buildResponseForStatus(status, resource, requestHeaders);
    }

    private Response bodylessResponse(String status) {
        return buildResponseForStatus(status);
    }
}
