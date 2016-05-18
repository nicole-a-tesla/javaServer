package nmccabe;

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
        out.write(response.formattedHeader());
//
//        System.out.print("\r\n");
//        System.out.print("ABOUT TO WRITE THE BODY");
//        System.out.print("\r\n    ");
//        System.out.print("the body = ");
//        System.out.print("\r\n");
//
//        System.out.print(new String(response.body));

//        System.out.print("\r\n");
//        System.out.print("...");

//        out.write("!!!!!!!".getBytes());
        out.write(response.body);
//        out.write("!!!!!!!".getBytes());
//
//
//        System.out.print("\r\n");
//        System.out.print("WROTE THAT SHIT");

        out.write(response.responseEnd());
    }

    public void close() throws IOException {
        out.close();
    }

}
