import nmccabe.Handlers.DirectoryHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import nmccabe.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryHandlerTest {

    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
    }

    @Test
    public void responds200OK() throws Exception {
        Request request = Helper.buildRequestFromString("GET / HTTP/1.0\r\n\r\n");
        Response response = new DirectoryHandler().getResponseFor(request);
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void responseBodyHasAllTheRightLinks() throws Exception {
        Request request = Helper.buildRequestFromString("GET / HTTP/1.0\r\n\r\n");
        Response response = new DirectoryHandler().getResponseFor(request);
        String bodyAsString = new String(response.body);

        File baseDir = new File(System.getProperty("baseDir"));
        String[] files = baseDir.list();

        for (String file:files) {
            assertTrue(bodyAsString.contains(file));
        }

    }
}
