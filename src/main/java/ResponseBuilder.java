import java.util.HashMap;

public class ResponseBuilder {
    public Response response;
    private String[] args;

    public ResponseBuilder(String[] args) {
        this.response = new Response();
        this.args = args;
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
        headersMap.put("Content-Length:", getContentLength());

        response.headers = headersMap;
    }

    private String getContentLength() {
        if (args.length > 1) {
            return Integer.toString(args[1].length());
        } else {
            return "0";
        }
    }

    private void setStatusCode() {
        response.statusCode = args[0];
    }

    private void setBody() {
        try {
            response.body = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            response.body = "";
        }
    }
}
