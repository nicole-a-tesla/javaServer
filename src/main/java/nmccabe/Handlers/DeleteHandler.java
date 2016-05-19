package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

public class DeleteHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return buildResponseForStatus(OK_STATUS);
    }
}
