import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;

public class ResponsePrinterTest {
    String outString;
    Response response;

    @Before
    public void setUp() throws Exception {
        String testResourceDir = System.getProperty("user.dir") + "/src/test/testResources";

        ResponseBuilder builder = new ResponseBuilder("200 OK", new Resource(testResourceDir + "/file1"));
        response = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResponsePrinter printer = new ResponsePrinter(response, out);
        printer.printToOutStream();
        outString = out.toString();
    }

    @Test
    public void canWriteATextBody() {
        assertTrue(outString.contains("file1 contents"));
    }

    @Test
    public void canWriteHttpVersion() {
        assertTrue(outString.contains("HTTP/1.0"));
    }

    @Test
    public void canWriteHeaders() {
        assertTrue(outString.contains("Content-Type:"));
        assertTrue(outString.contains("Content-Length:"));
    }
}