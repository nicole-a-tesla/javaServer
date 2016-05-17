import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeapotHandlerTest {
    @Test
    public void responds200ForTeaRequest() throws Exception {
        Request request = Helper.buildRequestFromString("GET /tea HTTP/1.1\r\n\r\n");
        Handler handler = new TeapotHandler();
        Response response = handler.getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void responds418ForCoffeeRequest() throws Exception {
        Request request = Helper.buildRequestFromString("GET /coffee HTTP/1.1\r\n\r\n");
        Handler handler = new TeapotHandler();
        Response response = handler.getResponseFor(request);

        assertEquals("418 I'm a teapot", response.statusCode);
    }
}
