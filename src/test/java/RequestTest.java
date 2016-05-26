import nmccabe.HttpInStream;
import nmccabe.Request;
import nmccabe.RequestBuilder;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() throws IOException {
        String str = "GET /parameters?oh=hi HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\nbody\r\n\r\n";
        InputStream rawInStream = new ByteArrayInputStream(str.getBytes());
        HttpInStream httpInStream = new HttpInStream(rawInStream);
        RequestBuilder builder = new RequestBuilder();
        request = builder.build(httpInStream);
    }

    @Test
    public void testGetRequestMethod() {
        assertEquals("GET", request.method());
    }

    @Test
    public void testGetRequestRoute() {
        assertEquals("/parameters", request.route());
    }

    @Test
    public void testGetParameters() {
        assertEquals("oh=hi", request.params());
    }

    @Test
    public void testGetRequestHttpVersion() {
        assertEquals("HTTP/1.1", request.httpVersion());
    }

    @Test
    public void testGetRequestHeader() {
        assertEquals("I'm-A-Value", request.getHeader("I'm-A-Key:"));
    }

    @Test
    public void testGetsMultipleRequestHeaders() {
        assertEquals("Another-Value", request.getHeader("Another-Key:"));
    }

    @Test
    public void getBody() {
        assertEquals("body\r\n", request.body());
    }

    @Test
    public void getsMultiLineBody() throws IOException {
        String str = "GET / HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\nbody\r\n\r\nMore body!\r\n\r\nOmg so body\r\n\r\n";
        InputStream rawInStream = new ByteArrayInputStream(str.getBytes());
        HttpInStream httpInStream = new HttpInStream(rawInStream);
        RequestBuilder builder = new RequestBuilder();

        Request allTheBodyRequest = builder.build(httpInStream);

        assertEquals("I'm-A-Value", allTheBodyRequest.getHeader("I'm-A-Key:"));

        assertTrue(allTheBodyRequest.body().contains("body"));
        assertTrue(allTheBodyRequest.body().contains("More body!"));
        assertTrue(allTheBodyRequest.body().contains("Omg so body"));
    }

    @Test
    public void getsAllOfTheAboveIfNoBody() throws IOException {
        String strNoBody = "GET / HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n";
        InputStream rawStreamNoBody = new ByteArrayInputStream(strNoBody.getBytes());
        HttpInStream streamNoBody = new HttpInStream(rawStreamNoBody);
        RequestBuilder builderNoBody = new RequestBuilder();

        Request noBodyRequest = builderNoBody.build(streamNoBody);

        assertEquals("GET", noBodyRequest.method());
        assertEquals("/", noBodyRequest.route());
        assertEquals("HTTP/1.1", noBodyRequest.httpVersion());
        assertEquals("I'm-A-Value", noBodyRequest.getHeader("I'm-A-Key:"));
        assertEquals("Another-Value", noBodyRequest.getHeader("Another-Key:"));
        assertEquals("", noBodyRequest.body());
    }
}
