package nmccabe;

public interface IResource {
    public String getContentLength();

    String mimeType();

    byte[] byteData();
}
