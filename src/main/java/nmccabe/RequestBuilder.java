package nmccabe;

import java.io.IOException;
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

    public Request build(HttpInStream httpInStream) throws IOException {
        try {
            this.requestLines = getRequestAsArray(httpInStream);
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

    private ArrayList<String> getRequestAsArray(HttpInStream httpInStream) throws IOException {
        String requestString = getRequestString(httpInStream);
        String realCharsOnly = requestString.split("\u0000")[0];
        String[] requestArray = realCharsOnly.split(carriageReturn);
        return new ArrayList<String>(Arrays.asList(requestArray));
    }

    private String getRequestString(HttpInStream httpInStream) throws IOException {
        return httpInStream.readString();
    }

    private void setFirstLineAttrs() {
        String[] firstLineParts = requestLines.get(0).split(" ");

        request.setMethod(firstLineParts[0]);
        setRouteAndParams(firstLineParts[1]);
        request.setHttpVersion(firstLineParts[2]);
    }

    private void setRouteAndParams(String routeAndPossiblyParams) {
        String[] separateRouteAndParams = routeAndPossiblyParams.split("\\?");
        request.setRoute(separateRouteAndParams[0]);

        if (requestHasParams(separateRouteAndParams)) {
            request.setParams(separateRouteAndParams[1]);
        } else {
            request.setParams("");
        }
    }

    private boolean requestHasParams(String[] routeAndParams) {
        return routeAndParams.length == 2;
    }

    private void setHeaders() {
        request.setHeaders(buildHeaderHash());
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
        request.setBody(buildBodyString(bodyParts));
    }

    private String buildBodyString(List<String> bodyParts) {
        StringBuilder wholeBody = new StringBuilder();

        for (String part : bodyParts)
            wholeBody.append(part).append(carriageReturn);

        return wholeBody.toString();
    }
}
