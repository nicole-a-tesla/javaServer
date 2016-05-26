package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

import java.util.Objects;

public class HeadHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        String status = HttpCodes.NOT_FOUND;

        if (Objects.equals(request.route(), "/")) {
            status = HttpCodes.OK;
        }

        return buildResponseForStatus(status);
    }
}
