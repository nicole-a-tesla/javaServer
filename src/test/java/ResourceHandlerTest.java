import nmccabe.Handlers.ResourceHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceHandlerTest {

    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
    }

    @Test
    public void buildsResourceProperly() throws Exception {
        Request request = Helper.buildRequestFromString("GET /text-file.txt HTTP/1.1\r\n\r\n");
        Response response = new ResourceHandler().getResponseFor(request);
        assertEquals("file1 contents", new String(response.body));
    }

    @Test
    public void responds404NotFoundForNonexistentResource() throws IOException {
        Request request = Helper.buildRequestFromString("GET /i-dont-exist.txt HTTP/1.1\r\n\r\n");
        Response response = new ResourceHandler().getResponseFor(request);

        assertEquals("404 Not Found", response.statusCode);

    }
}
