package main;

import java.util.HashMap;

public class Response {
    public String statusCode;
    public HashMap<String, String> headers;
    public String body;

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }
}
