import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Worker {

    public static final String OK_STATUS = "200 OK";
    public static final String NOT_FOUND_STATUS = "404 Not Found";

    public Response getResponse(Request request) throws IOException {
        if (Objects.equals(request.method, "GET")) {
            return handleGET(request);
        } else {
            return handleOtherRequests();
        }
    }

    private Response handleGET(Request request) throws IOException {
        if (Objects.equals(request.resource, "/")) {
            return bodylessResponse(OK_STATUS);
        }

        File file = new File(System.getProperty("baseDir") + request.resource);

        if (file.exists()) {
            Resource resource = new Resource(file.getPath());
            return bodyfulResponse(OK_STATUS, resource);
        } else {
            return bodylessResponse(NOT_FOUND_STATUS);
        }
    }

    private Response handleOtherRequests() {
        return bodylessResponse(OK_STATUS);
    }

    private Response bodyfulResponse(String status, Resource resource) {
        return new ResponseBuilder(status, resource).build();
    }

    private Response bodylessResponse(String status) {
        return new ResponseBuilder(status).build();
    }
}
