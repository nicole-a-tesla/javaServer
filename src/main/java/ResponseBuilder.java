import java.util.HashMap;

public class ResponseBuilder {
    private final String status;
    private final IResource resource;
    public Response response;

    public ResponseBuilder(String status, IResource resource) {
        this.response = new Response();
        this.status = status;
        this.resource = resource;
    }

    public ResponseBuilder(String status) {
        this(status, new NullResource());
    }

    public Response build() {
        setStatusCode();
        setHeaders();
        setBody();
        setMimeType();
        return response;
    }

    private void setStatusCode() {
        response.statusCode = status;
    }

    private void setHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type:", "text/html");
        headersMap.put("Content-Length:", getContentLength());

        response.headers = headersMap;
    }

    private void setBody() {
        if (resource.getBody().length() == 0) {
            response.body = "".getBytes();
        } else {
            response.body = resource.byteData();
        }
    }

    private String getContentLength() {
        return resource.getContentLength();
    }

    private void setMimeType() {
        response.mimeType = resource.mimeType();
    }

}
