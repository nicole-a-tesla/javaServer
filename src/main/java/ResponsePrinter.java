import java.io.IOException;
import java.io.OutputStream;

public class ResponsePrinter {
    private final Response response;
    private final OutputStream out;


    public ResponsePrinter(Response response, OutputStream out) {
        this.response = response;
        this.out = out;
    }

    public void printToOutStream() throws IOException {
        out.write(response.toFormattedString().getBytes());
    }

    public void close() throws IOException {
        out.close();
    }

}
