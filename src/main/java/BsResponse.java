public class BsResponse {
    private final Resource resource;
    private final String status;

    public BsResponse(String status, Resource resource) {
        this.status = status;
        this.resource = resource;
    }

    public String getStatus() {
        return status;
    }

    public Resource getResource() {
        return resource;
    }

    public String getContentLength() {
        if (getResource() != null) {
            return Integer.toString(getResource().byteData().length);
        } else {
            return "0";
        }
    }
}
