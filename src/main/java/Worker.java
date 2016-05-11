import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Worker {
    public Response getResponse(Request request) throws IOException {
        String[] args;

        if (Objects.equals(request.method, "GET")) {
            args = handleGET(request);
        } else {
            args = handleOtherRequests();
        }

        return new ResponseBuilder(args).build();
    }

    private String[] handleGET(Request request) throws IOException {
        if (Objects.equals(request.resource, "/")) {
            return new String[]{"200 OK"};
        }

        String dir = "/Users/bears8yourface/IdeaProjects/javaServer/cob_spec/public/";
        File file = new File(dir + request.resource);

        if (file.exists()) {
            return new String[]{"200 OK", getFileContents(file)};
        } else {
            return new String[]{"404 Not Found"};
        }
    }

    private String[] handleOtherRequests() {
        return new String[]{"200 OK"};
    }

    private String getFileContents(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded);
    }
}
