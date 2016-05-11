import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.io.*;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() throws IOException {
        String str = "GET /nice/file.html HTTP/1.0\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        RequestBuilder builder = new RequestBuilder();
        request = builder.build(reader);

    }

    @Test
    public void testGetRequestMethod() {
        assertEquals("GET", request.method);
    }

    @Test
    public void testGetRequestResource() {
        assertEquals("/nice/file.html", request.resource);
    }

    @Test
    public void testGetRequestHttpVersion() {
        assertEquals("HTTP/1.0", request.httpVersion);
    }

    @Test
    public void testGetRequestHeader() {
        assertEquals("I'm-A-Value", request.getHeader("I'm-A-Key:"));
    }

    @Test
    public void testGetsMultipleRequestHeaders() {
        assertEquals("Another-Value", request.getHeader("Another-Key:"));
    }
}
