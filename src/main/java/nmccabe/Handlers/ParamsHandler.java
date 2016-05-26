package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.URLParametersDecoder;

import java.io.UnsupportedEncodingException;

public class ParamsHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws UnsupportedEncodingException {
        Response response = buildResponseForStatus(HttpCodes.OK);
        String decodedParams = new URLParametersDecoder().decode(request.params());

        response.updateBody(decodedParams.getBytes());
        return response;
    }
}
