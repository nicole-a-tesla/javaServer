import nmccabe.Handlers.RedirectHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RedirectHandlerTest {
    Request request;
    Response response;

    @Before
    public void setUp() throws IOException {
        request = Helper.buildRequestFromString("GET /redirect HTTP/1.0\r\n\r\n");
        response = new RedirectHandler().getResponseFor(request);
    }

    @Test
    public void returns200OKForRequestToRoot() throws Exception {
        assertEquals("302 Found", response.statusCode);
    }

    @Test
    public void setsLocationHeader() throws Exception {
        assertEquals("http://localhost:5000/", response.getHeader("Location:"));
    }
}
