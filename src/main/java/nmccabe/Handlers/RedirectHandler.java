package nmccabe.Handlers;

import nmccabe.HttpCodes;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.ResponseBuilder;

public class RedirectHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response =  new ResponseBuilder(HttpCodes.FOUND).build();
        response.addHeader("Location:", "http://localhost:5000/");
        return response;
    }
}
