import java.util.Arrays;
import java.util.HashMap;

public class RequestBuilder {
    Request request;
    String requestString;
    String[] slicedRequest;

    public RequestBuilder(String requestString) {
        this.requestString = requestString;
        this.slicedRequest = requestString.split("\r\n");
        this.request = new Request();
    }

    public Request build() {
        setFirstLineAttrs();
        setHeaders();
        return request;
    }

    private void setFirstLineAttrs() {
        String[] firstLineParts = slicedRequest[0].split(" ");

        request.method = firstLineParts[0];
        request.resource = firstLineParts[1];
        request.httpVersion = firstLineParts[2];
    }

    private void setHeaders() {
        request.headers =  buildHeaderHash();
    }

    private HashMap<String, String> buildHeaderHash() {
        String[] elements = Arrays.copyOfRange(slicedRequest, 1, slicedRequest.length);
        HashMap<String, String> headerMap = new HashMap<>();

        for (String element : elements) {
            setHeader(element, headerMap);
        }
        return headerMap;
    }

    private void setHeader(String element, HashMap headerMap) {
        String[] parts = element.split("\\s+");
        headerMap.put(parts[0], parts[1]);
    }

}
