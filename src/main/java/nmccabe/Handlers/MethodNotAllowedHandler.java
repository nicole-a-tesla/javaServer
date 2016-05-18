package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

public class MethodNotAllowedHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return buildResponseForStatus(NOT_ALLOWED_STATUS);
    }
}
