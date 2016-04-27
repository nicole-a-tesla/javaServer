package test;
import org.junit.Test;
import org.junit.Before;

import main.Request;
import main.RequestBuilder;
import static org.junit.Assert.assertEquals;

public class RequestTest {
    private Request request;
    @Before
    public void setUp() {
        RequestBuilder builder = new RequestBuilder("GET /nice/file.html HTTP/1.0\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value");
        request = builder.build();

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
