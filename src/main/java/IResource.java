public interface IResource {
    public String getContentLength();
    public String getBody();

    String mimeType();

    byte[] byteData();
}
