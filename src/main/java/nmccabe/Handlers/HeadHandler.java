package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

import java.util.Objects;

public class HeadHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        String status = NOT_FOUND_STATUS;

        if (Objects.equals(request.route(), "/")) {
            status = OK_STATUS;
        }

        return buildResponseForStatus(status);
    }
}
