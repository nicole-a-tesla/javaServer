package nmccabe;

import java.util.HashMap;

public class Request {
    private String method;
    private String route;
    private String httpVersion;
    private HashMap<String, String> headers;
    private String body = "";
    private String params;

    public String route() {
        return this.route;
    }

    public String params() {
        return this.params;
    }

    public String method() {
        return this.method;
    }

    public String httpVersion() {
        return this.httpVersion;
    }

    public HashMap<String, String> headers() {
        return this.headers;
    }

    public String getHeader(String headerKey) {
        return this.headers.getOrDefault(headerKey, "Header Not Found");
    }

    public String body() {
        return this.body;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setHeader(String headerKey, String headerValue) {
        this.headers.put(headerKey, headerValue);
    }

    public void setBody(String body) {
        this.body = body;
    }
}
