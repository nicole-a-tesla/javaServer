package nmccabe;

import nmccabe.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RequestBuilder {
    Request request;
    ArrayList<String> requestLines;

    public RequestBuilder() throws IOException {
        this.request = new Request();
    }

    public Request build(BufferedReader requestStream) throws IOException {
        try {
            this.requestLines = getRequestLines(requestStream);
            setFirstLineAttrs();
            setHeaders();

        } catch (Exception e) {
            return request;
        }

        return request;
    }

    private ArrayList<String> getRequestLines(BufferedReader requestStream) throws IOException {

        String line;
        ArrayList<String> linesSoFar = new ArrayList();

        while (!Objects.equals(line = requestStream.readLine(), "")) {
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
        ArrayList<String> headerElements = (ArrayList) requestLines.clone();
        headerElements.remove(0);

        HashMap<String, String> headerMap = new HashMap<>();

        for (String element : headerElements) {
            setHeader(element, headerMap);
        }

        return headerMap;
    }

    private void setHeader(String element, HashMap headerMap) {
        String[] parts = element.split("\\s+");
        headerMap.put(parts[0], parts[1]);
    }

}
