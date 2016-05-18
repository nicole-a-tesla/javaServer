package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

import java.util.Objects;

public class HeadHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        if (Objects.equals(request.route, "/")) {
            return new ResponseBuilder(OK_STATUS).build();
        } else {
            return new ResponseBuilder(NOT_FOUND_STATUS).build();
        }
    }
}
