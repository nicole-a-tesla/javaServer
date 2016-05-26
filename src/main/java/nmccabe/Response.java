package nmccabe;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private String statusCode;
    private HashMap<String, String> headers;
    private byte[] body;
    private String mimeType;
    private String crlf = "\r\n";

    public String statusCode() {
        return this.statusCode;
    }

    public HashMap<String, String> headers() {
        return this.headers;
    }

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }

    public byte[] formattedHeader() {
        return ("HTTP/1.1 " + statusCode + headersToString() + crlf).getBytes();
    }

    public String headersToString() {
        String headerString = crlf;

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            headerString += formatIndividualHeader(entry);
        }

        return headerString;
    }

    private String formatIndividualHeader(Map.Entry entry) {
        return entry.getKey() + " " + entry.getValue() + crlf;
    }

    public byte[] body() {
        return this.body;
    }

    public String mimeType() {
        return this.mimeType;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    private void updateContentLength() {
        addHeader("Content-Length:", String.valueOf(body.length));
    }

    public void setBody(byte[] bodyBytes) {
        body = bodyBytes;
    }

    public void updateBody(byte[] bodyBytes) {
        setBody(bodyBytes);
        updateContentLength();
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] responseEnd() {
        return (crlf + crlf).getBytes();
    }

}
