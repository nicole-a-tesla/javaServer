import java.util.HashMap;

public class ResponseBuilder {
    private final BsResponse bsResponse;
    public Response response;

    public ResponseBuilder(BsResponse bsResponse) {
        this.response = new Response();
        this.bsResponse = bsResponse;
    }

    public Response build() {
        setStatusCode();
        setHeaders();
        setBody();
        return response;
    }

    private void setHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type:", "text/html");
        headersMap.put("Content-Length:", bsResponse.getContentLength());

        response.headers = headersMap;
    }

    private void setStatusCode() {
        response.statusCode = bsResponse.getStatus();
    }

    private String getBody() {
        String body;
        try {
            body = "\r\n" + bsResponse.getResource().stringData() + "\r\n\r\n";
        } catch (NullPointerException e) {
            body = "";
        }
        return body;
    }

    private void setBody() {
        response.body = getBody();
    }
}
