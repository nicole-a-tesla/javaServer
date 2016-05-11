public class Worker {
    public Response getResponse(Request request) {
        String[] args = {"200 OK"};
        return new ResponseBuilder(args).build();
    }
}
