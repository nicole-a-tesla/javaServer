public class NullResource implements IResource {
    @Override
    public String getContentLength() {
        return "0";
    }

    @Override
    public String mimeType() {
        return "";
    }

    @Override
    public byte[] byteData() {
        return new byte[0];
    }
}
