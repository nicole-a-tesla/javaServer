package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

public class MethodOptionsHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = buildResponseForStatus(HttpCodes.OK);
        response.addHeader("Allow:", "GET,HEAD,POST,OPTIONS,PUT");
        return response;
    }
}
