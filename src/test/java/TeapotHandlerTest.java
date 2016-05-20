import nmccabe.Handlers.Handler;
import nmccabe.Handlers.TeapotHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeapotHandlerTest {
    Handler handler = new TeapotHandler();

    @Test
    public void responds200ForTeaRequest() throws Exception {
        Request request = Helper.buildRequestFromString("GET /tea HTTP/1.1\r\n\r\n");
        Response response = handler.getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void responds418ForCoffeeRequest() throws Exception {
        Request request = Helper.buildRequestFromString("GET /coffee HTTP/1.1\r\n\r\n");
        Response response = handler.getResponseFor(request);

        assertEquals("418 I'm a teapot", response.statusCode);
    }

    @Test
    public void error418hasBodyImATeapot() throws Exception {
        Request request = Helper.buildRequestFromString("GET /coffee HTTP/1.1\r\n\r\n");
        Response response = handler.getResponseFor(request);

        assertTrue(new String(response.body).contains("I'm a teapot"));
    }
}
