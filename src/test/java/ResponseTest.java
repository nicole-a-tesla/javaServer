import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    public Response response;

    @Before
    public void setUp() throws IOException {
        String resourcePath = System.getProperty("user.dir") + "/src/test/testResources/file1";
        Resource resource = new Resource(resourcePath);
        BsResponse bsResponse = new BsResponse("200 OK", resource);
        response = new ResponseBuilder(bsResponse).build();
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
        assertEquals("\r\nfile1 contents\r\n\r\n", response.body);
    }

    @Test
    public void testHasContentLengthEqualToBodyLength(){
        assertEquals("14", response.getHeader("Content-Length:"));
    }

    @Test
    public void testResponseToString() {
        String expectedString = "HTTP/1.0 200 OK\r\nContent-Length: 14\r\nContent-Type: text/html\r\n\r\nfile1 contents\r\n\r\n";
        String actualString = response.toString();
        assertEquals(expectedString, actualString);
    }
}
