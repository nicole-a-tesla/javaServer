public class PostHandler extends Handler {
    @Override
    public Response getResponseFor(Request request) {
        return new ResponseBuilder(OK_STATUS).build();
    }
}
