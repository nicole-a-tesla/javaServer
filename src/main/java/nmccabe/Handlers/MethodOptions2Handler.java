package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

public class MethodOptions2Handler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = buildResponseForStatus(HttpCodes.OK);
        response.addHeader("Allow:", "GET,OPTIONS");
        return response;
    }
}
