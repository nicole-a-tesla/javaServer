package test;
import main.Response;
import main.ResponseBuilder;
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
        assertEquals("body", response.body);
    }

    @Test
    public void testHasContentLengthEqualToBodyLength(){
        assertEquals(Integer.toString(response.body.length()), response.getHeader("Content-Length:"));
    }

    @Test
    public void testResponseToString() {
        String expectedString = "HTTP/1.0 200 OK\r\nContent-Length: 4\nContent-Type: text/html\r\nbody";
        String actualString = response.toString();
        assertEquals(expectedString, actualString);
    }
}
