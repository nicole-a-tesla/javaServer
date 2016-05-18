package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

public class MethodOptionsHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = buildResponseForStatus(OK_STATUS);
        response.addHeader("Allow:", "GET,HEAD,POST,OPTIONS,PUT");
        return response;
    }
}
