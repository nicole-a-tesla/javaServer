package nmccabe;

import java.io.UnsupportedEncodingException;

public class URLParametersDecoder {
    public String decode(String params) throws UnsupportedEncodingException {
        String paramsWithSpaces = insertSpacesAroundEquals(params);
        return java.net.URLDecoder.decode(paramsWithSpaces, "UTF-8");
    }

    private String insertSpacesAroundEquals(String params) {
        return params.replaceAll("([=])", " $1 ");
    }
}
