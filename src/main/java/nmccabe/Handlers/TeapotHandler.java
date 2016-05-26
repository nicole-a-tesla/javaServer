package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;

import java.util.Objects;

public class TeapotHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {

        if (Objects.equals(request.route(), "/tea")) {
            return buildResponseForStatus(HttpCodes.OK);
        } else if (Objects.equals(request.route(), "/coffee")){
            Response response = buildResponseForStatus(HttpCodes.IM_A_TEAPOT);
            response.updateBody("I'm a teapot".getBytes());
            return response;
        }
        return buildResponseForStatus(HttpCodes.METHOD_NOT_ALLOWED);
    }
}
