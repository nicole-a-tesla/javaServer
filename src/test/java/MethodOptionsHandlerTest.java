import nmccabe.Handlers.Handler;
import nmccabe.Handlers.MethodOptionsHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {
    @Test
    public void includesOptionsHeader() throws Exception {
        Request request = Helper.buildRequestFromString("GET /method_options HTTP/1.1\r\n\r\n");
        Handler handler = new MethodOptionsHandler();
        Response response = handler.getResponseFor(request);

        assertTrue(response.headersToString().contains("GET,HEAD,POST,OPTIONS,PUT"));
    }


}
