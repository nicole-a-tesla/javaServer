public class MethodOptions2Handler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        Response response = new ResponseBuilder(OK_STATUS).build();
        response.addHeader("Allow:", "GET,OPTIONS");
        return response;
    }
}
