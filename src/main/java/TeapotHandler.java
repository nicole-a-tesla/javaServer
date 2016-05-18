import java.util.Objects;

public class TeapotHandler extends Handler {
    public static final String IM_A_TEAPOT = "418 I'm a teapot";

    @Override
    public Response getResponseFor(Request request) {
        if (Objects.equals(request.route, "/tea")) {
            return new ResponseBuilder(OK_STATUS).build();
        }
        return new ResponseBuilder(IM_A_TEAPOT).build();
    }
}
