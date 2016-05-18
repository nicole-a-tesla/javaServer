package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

public class MethodNotAllowedHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return new ResponseBuilder(NOT_ALLOWED_STATUS).build();
    }
}
