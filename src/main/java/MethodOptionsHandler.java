public class MethodOptionsHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = new ResponseBuilder(OK_STATUS).build();
        response.addHeader("Allow:", "GET,HEAD,POST,OPTIONS,PUT");
        return response;
    }
}
