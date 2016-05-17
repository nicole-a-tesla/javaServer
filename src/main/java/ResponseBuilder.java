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
        response.statusCode = getStatusCode();
        response.headers = getHeaders();
        response.body = getBody();
        response.mimeType = getMimeType();
        
        return response;
    }

    private String getStatusCode() {
        return status;
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();

        headersMap.put("Content-Type:", "text/html");
        headersMap.put("Content-Length:", getContentLength());

        return headersMap;
    }

    private byte[] getBody() {
        return resource.byteData();
    }

    private String getContentLength() {
        return resource.getContentLength();
    }

    private String getMimeType() {
        return resource.mimeType();
    }

}
