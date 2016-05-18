package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

import java.util.Objects;

public class TeapotHandler extends Handler {
    public static final String IM_A_TEAPOT = "418 I'm a teapot";

    @Override
    public Response getResponseFor(Request request) {
        String status = IM_A_TEAPOT;

        if (Objects.equals(request.route, "/tea")) {
            status = OK_STATUS;
        }
        return buildResponseForStatus(status);
    }
}
