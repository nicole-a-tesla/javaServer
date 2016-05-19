import nmccabe.Request;
import nmccabe.RequestBuilder;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.ArrayList;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() throws IOException {
        String str = "GET / HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\nbody!\r\n\r\n";
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
        assertEquals("/", request.route);
    }

    @Test
    public void testGetRequestHttpVersion() {
        assertEquals("HTTP/1.1", request.httpVersion);
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
    public void getBody() throws Exception {
        assertEquals("body!\r\n", request.body);
    }

    @Test
    public void getsMultiLineBody() throws Exception {
        String str = "GET / HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\nbody!\r\n\r\nMore body!\r\n\r\nOmg so body\r\n\r\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        RequestBuilder builder = new RequestBuilder();

        Request allTheBodyRequest = builder.build(reader);

        assertTrue(allTheBodyRequest.body.contains("body!"));
        assertTrue(allTheBodyRequest.body.contains("More body!"));
        assertTrue(allTheBodyRequest.body.contains("Omg so body"));
    }

    @Test
    public void getsAllOfTheAboveIfNoBody() throws Exception {
        String str = "GET / HTTP/1.1\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        RequestBuilder builder = new RequestBuilder();
        Request noBodyRequest = builder.build(reader);

        assertEquals("GET", noBodyRequest.method);
        assertEquals("/", noBodyRequest.route);
        assertEquals("HTTP/1.1", noBodyRequest.httpVersion);
        assertEquals("I'm-A-Value", noBodyRequest.getHeader("I'm-A-Key:"));
        assertEquals("Another-Value", noBodyRequest.getHeader("Another-Key:"));
        assertEquals("", noBodyRequest.body);


    }
}
