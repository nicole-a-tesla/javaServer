package main;

import java.util.HashMap;

public class Request {
    public String method;
    public String resource;
    public String httpVersion;
    public HashMap<String, String> headers;

    public String getHeader(String headerKey) {
        return this.headers.get(headerKey);
    }

}
