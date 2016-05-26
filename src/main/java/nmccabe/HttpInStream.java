package nmccabe;

import java.io.IOException;
import java.io.InputStream;

public class HttpInStream {
    private byte[] byteData;

    public HttpInStream(InputStream rawIn) throws IOException {
        this.byteData = readByteData(rawIn);
    }

    public byte[] byteData() {
        return this.byteData;
    }

    private byte[] readByteData(InputStream rawIn) throws IOException {
        byte[] data = new byte[18000];
        rawIn.read(data);
        return data;
    }

    public String readString() {
        return new String(byteData);
    }
}
