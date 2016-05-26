package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

public class MethodNotAllowedHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return buildResponseForStatus(HttpCodes.METHOD_NOT_ALLOWED);
    }
}
