package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

public class HeadHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return new ResponseBuilder(OK_STATUS).build();
    }
}
