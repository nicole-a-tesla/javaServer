package nmccabe;

public interface IResource {
    String getContentLength();

    String mimeType();

    byte[] byteData();
}
