package nmccabe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ResponseBuilder {
    private final String status;
    private final IResource resource;
    public Response response;
    private HashMap requestHeaders;


    public ResponseBuilder(String status, IResource resource, HashMap requestHeaders) {
        this.response = new Response();
        this.status = status;
        this.resource = resource;
        this.requestHeaders = requestHeaders;
    }

    public ResponseBuilder(String status, IResource resource) {
        this(status, resource, new HashMap());
    }

    public ResponseBuilder(String status) {
        this(status, new NullResource(), new HashMap());
    }

    public Response build() {
        response.body = getBody();
        response.mimeType = getMimeType();
        response.headers = getHeaders();
        response.statusCode = getStatusCode();

        return response;
    }

    private String getStatusCode() {
        return status;
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();

        headersMap.put("Content-Length:", getContentLength());
        headersMap.put("Content-Type:", getMimeType());

        return headersMap;
    }

    private byte[] getBody() {
        byte[] allBytes = resource.byteData();

        if (Objects.equals(status, "206 Partial")) {
            RangeDecoder decoder = new RangeDecoder(allBytes, (String) requestHeaders.get("Range:"));
            return Arrays.copyOfRange(allBytes, decoder.lowerBound(), decoder.upperBound());
        } else {
            return allBytes;
        }
    }

    private String getContentLength() {
        return String.valueOf(response.body.length);
    }

    private String getMimeType() {
        return resource.mimeType();
    }
}
