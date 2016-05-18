import java.io.IOException;

public class Handler {
    public static final String OK_STATUS = "200 OK";
    public static final String NOT_FOUND_STATUS = "404 Not Found";

    public Response getResponseFor(Request request) throws IOException {
        return new ResponseBuilder("404 Not Found").build();
    }
}
