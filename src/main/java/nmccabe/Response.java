package nmccabe;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public String statusCode;
    public HashMap<String, String> headers;
    public byte[] body;
    public String mimeType;
    private String crlf = "\r\n";

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }

    public byte[] formattedHeader() {
        return ("HTTP/1.1 " + statusCode + headersToString() + crlf).getBytes();
    }

    public byte[] responseEnd() {
        return (crlf + crlf).getBytes();
    }

    public String headersToString() {
        String headerString = crlf;

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            headerString += entry.getKey() + " " + entry.getValue() + crlf;
        }

        return headerString;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addBody(byte[] bodyBytes) {
        body = bodyBytes;
        updateContentLength();
    }

    private void updateContentLength() {
        addHeader("Content-Length:", String.valueOf(body.length));
    }

}
