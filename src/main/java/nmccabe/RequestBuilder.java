package nmccabe;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RequestBuilder {
    Request request;
    ArrayList<String> requestLines;
    private int headersBodyBreakIndex;

    public RequestBuilder() throws IOException {
        this.request = new Request();
    }

    public Request build(BufferedReader requestStream) throws IOException {
        try {
            this.requestLines = getRequestLines(requestStream);
            this.headersBodyBreakIndex = this.requestLines.indexOf("");
            setFirstLineAttrs();
            setHeaders();
            setBodyIfPresent();

        } catch (Exception e) {
            return request;
        }

        return request;
    }

    private ArrayList<String> getRequestLines(BufferedReader requestStream) throws IOException {
        String line;
        ArrayList<String> linesSoFar = new ArrayList();

        while (!Objects.equals(line = requestStream.readLine(), null)) {
            linesSoFar.add(line);
        }
        return linesSoFar;
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
        List<String> bodyBits = requestLines.subList(headersBodyBreakIndex + 1, requestLines.lastIndexOf(""));
        String allTheBits = "";

        for (String bit : bodyBits) {
            allTheBits += bit;
            allTheBits += "\r\n";
        }

        request.body = allTheBits;
    }

}
