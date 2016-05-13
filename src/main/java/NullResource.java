public class NullResource implements IResource {
    @Override
    public String getContentLength() {
        return "0";
    }

    @Override
    public String getBody() {
        return "";
    }
}
