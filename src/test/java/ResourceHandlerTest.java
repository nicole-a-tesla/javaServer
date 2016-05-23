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
    public void responds200OKForRealRequest() throws IOException {
        Request request = Helper.buildRequestFromString("GET / HTTP/1.0\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n");
        Response response = new ResourceHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void responds404NotFoundForNonexistentResource() throws IOException {
        Request request = Helper.buildRequestFromString("GET /i-dont-exist.txt HTTP/1.1\r\n\r\n");
        Response response = new ResourceHandler().getResponseFor(request);

        assertEquals("404 Not Found", response.statusCode);

    }

    @Test
    public void respondsWithVariablesIfParamsPassed() throws Exception {
        String paramsReq = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Request request = Helper.buildRequestFromString("GET " + paramsReq + " HTTP/1.1\r\n\r\n");
        Response response = new ResourceHandler().getResponseFor(request);
        String bodyString = new String(response.body);

        assertTrue(bodyString.contains("variable_2 = stuff"));
        assertTrue(bodyString.contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
    }
}
