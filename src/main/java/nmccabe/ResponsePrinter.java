package nmccabe;

import java.io.IOException;

public class ResponsePrinter {
    private final Response response;
    private final HttpOutStream out;


    public ResponsePrinter(Response response, HttpOutStream out) {
        this.response = response;
        this.out = out;
    }

    public void printToOutStream() throws IOException {
        out.write(response.formattedHeader());
        out.write(response.body());
        out.write(response.responseEnd());
    }
}
