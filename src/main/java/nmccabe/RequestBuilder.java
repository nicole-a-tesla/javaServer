package nmccabe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestBuilder {
    Request request;
    ArrayList<String> requestLines;
    private int headersBodyBreakIndex;
    private String carriageReturn = "\r\n";

    public RequestBuilder() throws IOException {
        this.request = new Request();
    }

    public Request build(InputStream rawInputStream) throws IOException {
        try {
            this.requestLines = getRequestAsArray(rawInputStream);
            this.headersBodyBreakIndex = getHeaderBodyBreak();
            setFirstLineAttrs();
            setHeaders();
            setBodyIfPresent();

        } catch (Exception e) {
            return request;
        }

        return request;
    }

    private int getHeaderBodyBreak() {
        int possibleBreakPoint = this.requestLines.indexOf("");

        if (possibleBreakPoint == -1) {
            return this.requestLines.size();
        } else {
            return possibleBreakPoint;
        }
    }

    private ArrayList<String> getRequestAsArray(InputStream rawInputStream) throws IOException {
        String requestString = getRequestString(rawInputStream);
        String realCharsOnly = requestString.split("\u0000")[0];
        String[] requestArray = realCharsOnly.split(carriageReturn);
        return new ArrayList<String>(Arrays.asList(requestArray));
    }

    private String getRequestString(InputStream rawInputStream) throws IOException {
        byte[] data = new byte[18000];
        rawInputStream.read(data);
        return new String(data);
    }

    private void setFirstLineAttrs() {
        String[] firstLineParts = requestLines.get(0).split(" ");

        request.method = firstLineParts[0];
        request.route = firstLineParts[1];
        request.httpVersion = firstLineParts[2];
    }

    private void setHeaders() {
        request.headers =  buildHeaderHash();
    }

    private HashMap<String, String> buildHeaderHash() {
        List<String> headerElements = getJustTheHeaders();
        HashMap<String, String> headerMap = new HashMap<>();

        for (String element : headerElements) {
            setHeader(element, headerMap);
        }

        return headerMap;
    }

    private List getJustTheHeaders() {
        return requestLines.subList(1, headersBodyBreakIndex);
    }

    private void setHeader(String element, HashMap headerMap) {
        String[] parts = element.split("\\s+");
        headerMap.put(parts[0], parts[1]);
    }

    private void setBodyIfPresent() {
        List<String> bodyParts = requestLines.subList(headersBodyBreakIndex + 1, requestLines.size());
        request.body = buildBodyString(bodyParts);
    }

    private String buildBodyString(List<String> bodyParts) {
        StringBuilder wholeBody = new StringBuilder();

        for (String part : bodyParts)
            wholeBody.append(part).append(carriageReturn);

        return wholeBody.toString();
    }
}
