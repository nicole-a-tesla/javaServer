import nmccabe.*;
import nmccabe.Handlers.TeapotHandler;
import nmccabe.Helper;
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
        assertTrue(outString.contains("HTTP/1.1"));
    }

    @Test
    public void canWriteHeaders() {
        assertTrue(outString.contains("Content-Type:"));
        assertTrue(outString.contains("Content-Length:"));
    }

    @Test
    public void outputIsProperlyFormatted() {
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Length: 14\r\nContent-Type: noType/\r\n\r\nfile1 contents\r\n\r\n";
        String actualString = outString;
        assertEquals(expectedString, actualString);
    }

    @Test
    public void writesBodyAddedByHandler() throws Exception {
        Request request = Helper.buildRequestFromString("GET /coffee HTTP/1.1\r\n\r\n");
        Response coffeeResponse = new TeapotHandler().getResponseFor(request);

        ByteArrayOutputStream coffeeOut = new ByteArrayOutputStream();
        ResponsePrinter printer = new ResponsePrinter(coffeeResponse, coffeeOut);
        printer.printToOutStream();
        String coffeeOutString = coffeeOut.toString();

        assertTrue(coffeeOutString.contains("I'm a teapot"));
    }
}