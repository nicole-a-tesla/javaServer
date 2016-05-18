package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

public class MethodOptions2Handler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = new ResponseBuilder(Handler.OK_STATUS).build();
        response.addHeader("Allow:", "GET,OPTIONS");
        return response;
    }
}
