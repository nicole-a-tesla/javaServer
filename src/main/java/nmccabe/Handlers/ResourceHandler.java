package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Resource;
import nmccabe.Response;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ResourceHandler extends Handler {

    @Override
    public Response getResponseFor(Request request) throws IOException {
        return handleGET(request);
    }

    public Response handleGET(Request request) throws IOException {
        if (Objects.equals(request.route, "/")) {
            return bodylessResponse(OK_STATUS);
        }

        File file = new File(System.getProperty("baseDir") + request.route);

        if (file.exists()) {
            Resource resource = new Resource(file.getPath());
            return bodyfulResponse(OK_STATUS, resource);
        } else {
            return bodylessResponse(NOT_FOUND_STATUS);
        }
    }


    private Response bodyfulResponse(String status, Resource resource) {
        return buildResponseForStatus(status, resource);
    }

    private Response bodylessResponse(String status) {
        return buildResponseForStatus(status);
    }
}
