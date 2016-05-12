import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    public Response response;

    @Before
    public void setUp() {
        String[] args = {"200 OK", "body"};
        response = new ResponseBuilder(args).build();
    }

    @Test
    public void testHasResponseCode() {
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void testHasContentTypeHeader() {
        assertEquals("text/html", response.getHeader("Content-Type:"));
    }

    @Test
    public void testHasBody() {
        assertEquals("\r\nbody\r\n\r\n", response.body);
    }

    @Test
    public void testHasContentLengthEqualToBodyLength(){
        assertEquals("4", response.getHeader("Content-Length:"));
    }

    @Test
    public void testResponseToString() {
        String expectedString = "HTTP/1.0 200 OK\r\nContent-Length: 4\r\nContent-Type: text/html\r\n\r\nbody\r\n\r\n";
        String actualString = response.toString();
        assertEquals(expectedString, actualString);
    }
}
