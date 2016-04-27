package main;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public String statusCode;
    public HashMap<String, String> headers;
    public String body;

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }

    public String toString() {
        return "HTTP/1.0 " + statusCode + headersToString() + "body";
    }

    private String headersToString() {
        String lineBreak = "\r\n";
        String headerString = lineBreak;

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            headerString += entry.getKey() + " " + entry.getValue() + lineBreak;
        }

        return headerString;
    }
}
