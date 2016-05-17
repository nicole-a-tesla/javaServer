import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {
    @Test
    public void includesOptionsHeader() throws Exception {
        Request request = Helper.buildRequestFromString("GET /method_options HTTP/1.1\r\n\r\n");
        Handler handler = new Router().getHandlerFor(request);
        Response response = handler.getResponseFor(request);

        assertTrue(response.headersToString().contains("GET,HEAD,POST,OPTIONS,PUT"));
    }


}
