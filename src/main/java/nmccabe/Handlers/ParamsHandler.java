package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.URLParametersDecoder;

import java.io.UnsupportedEncodingException;

public class ParamsHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) throws UnsupportedEncodingException {
        Response response = buildResponseForStatus(OK_STATUS);
        String decodedParams = new URLParametersDecoder().decode(request.params());

        response.addBody(decodedParams.getBytes());
        return response;
    }
}
