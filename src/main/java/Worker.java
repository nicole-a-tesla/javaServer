import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Worker {
    public Response getResponse(Request request) throws IOException {
        BsResponse bsResponse;

        if (Objects.equals(request.method, "GET")) {
            bsResponse = handleGET(request);
        } else {
            bsResponse = handleOtherRequests();
        }

        return new ResponseBuilder(bsResponse).build();
    }

    private BsResponse handleGET(Request request) throws IOException {
        if (Objects.equals(request.resource, "/")) {
            return new BsResponse("200 OK", null);
        }

        File file = new File(System.getProperty("baseDir") + request.resource);

        if (file.exists()) {
            Resource resource = new Resource(file.getPath());
            return new BsResponse("200 OK", resource);
        } else {
            return new BsResponse("404 Not Found", null);
        }
    }

    private BsResponse handleOtherRequests() {
        return new BsResponse("200 OK", null);
    }
}
