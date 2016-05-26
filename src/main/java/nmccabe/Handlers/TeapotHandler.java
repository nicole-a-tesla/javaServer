package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;

import java.util.Objects;

public class TeapotHandler extends Handler {
    public static final String IM_A_TEAPOT = "418 I'm a teapot";

    @Override
    public Response getResponseFor(Request request) {

        if (Objects.equals(request.route(), "/tea")) {
            return buildResponseForStatus(OK_STATUS);
        } else if (Objects.equals(request.route(), "/coffee")){
            Response response = buildResponseForStatus(IM_A_TEAPOT);
            response.addBody("I'm a teapot".getBytes());
            return response;
        }
        return buildResponseForStatus(NOT_ALLOWED_STATUS);
    }
}
