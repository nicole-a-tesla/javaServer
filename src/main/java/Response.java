import java.util.HashMap;
import java.util.Map;

public class Response {
    public String statusCode;
    public HashMap<String, String> headers;
    public String body;
    public String mimeType;
    private String crlf = "\r\n";;

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }

    public String toFormattedString() {
        return "HTTP/1.0 " + statusCode + headersToString() + crlf + body + crlf + crlf;
    }

    public String headersToString() {
        String headerString = crlf;

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            headerString += entry.getKey() + " " + entry.getValue() + crlf;
        }

        return headerString;
    }
}
