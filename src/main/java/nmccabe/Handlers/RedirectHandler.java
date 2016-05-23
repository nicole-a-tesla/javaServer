package nmccabe.Handlers;

import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

public class RedirectHandler extends Handler {
    public static final String FOUND_STATUS = "302 Found";

    @Override
    public Response getResponseFor(Request request) {
        Response response =  new ResponseBuilder(FOUND_STATUS).build();
        response.addHeader("Location:", "http://localhost:5000/");
        return response;
    }
}
