package nmccabe;

import java.io.IOException;
import java.io.OutputStream;

public class HttpOutStream {
    private OutputStream internalOutStream;

    public HttpOutStream(OutputStream internalOutStream) {
        this.internalOutStream = internalOutStream;
    }

    public void write(byte[] data) throws IOException {
        this.internalOutStream.write(data);
    }

    @Override
    public String toString() {
        return this.internalOutStream.toString();
    }

    public void tearDownStream() throws IOException {
        this.internalOutStream.flush();
        this.internalOutStream.close();
    }
}
