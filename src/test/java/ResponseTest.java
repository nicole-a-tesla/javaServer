import nmccabe.NullResource;
import nmccabe.Resource;
import nmccabe.Response;
import nmccabe.ResponseBuilder;
import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    public Response response;

    @Before
    public void setUp() throws IOException {
        String resourcePath = System.getProperty("user.dir") + "/src/test/testResources/text-file.txt";
        Resource resource = new Resource(resourcePath);
        response = new ResponseBuilder("200 OK", resource).build();
    }

    @Test
    public void testHasResponseCode() {
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void testHasContentTypeHeader() {
        assertEquals("text/plain", response.getHeader("Content-Type:"));
    }

    @Test
    public void testHasBody() {
        assertEquals("file1 contents", new String(response.body));
    }

    @Test
    public void testHasContentLengthEqualToBodyLength(){
        assertEquals("14", response.getHeader("Content-Length:"));
    }

    @Test
    public void headerCanBeAdded() throws Exception {
        response.addHeader("New:", "Header!");
        assertEquals("Header!", response.getHeader("New:"));
    }

    @Test
    public void bodyCanBeAdded() throws Exception {
        Response bodylessResponse = new ResponseBuilder("200 OK", new NullResource()).build();
        bodylessResponse.addBody("Bod".getBytes());

        assertEquals("Bod", new String(bodylessResponse.body));
        assertEquals("3", bodylessResponse.getHeader("Content-Length:"));
    }

}
