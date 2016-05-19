package nmccabe;

import java.util.HashMap;

public class Request {
    public String method;
    public String route;
    public String httpVersion;
    public HashMap<String, String> headers;
    public String body = "";

    public String getHeader(String headerKey) {
        return this.headers.getOrDefault(headerKey, "Header Not Found");
    }

}
