package nmccabe;

import java.io.UnsupportedEncodingException;

public class URLParametersDecoder {
    public String decode(String params) throws UnsupportedEncodingException {
        String paramsWithSpaces = insertSpacesAroundEquals(params);
        return java.net.URLDecoder.decode(paramsWithSpaces, "UTF-8");
    }

    private String insertSpacesAroundEquals(String baseDecoding) {
        return baseDecoding.replaceAll("([=])", " $1 ");
    }
}
