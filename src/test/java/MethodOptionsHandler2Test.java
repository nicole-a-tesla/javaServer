import nmccabe.Handlers.Handler;
import nmccabe.Handlers.MethodOptions2Handler;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MethodOptionsHandler2Test {
    @Test
    public void includesOptionsHeader() throws Exception {
        Request request = Helper.buildRequestFromString("GET /method_options2 HTTP/1.1\r\n\r\n");
        Handler handler = new MethodOptions2Handler();
        Response response = handler.getResponseFor(request);

        assertTrue(response.headersToString().contains("GET,OPTIONS"));
        assertFalse(response.headersToString().contains("HEAD"));
        assertFalse(response.headersToString().contains("POST"));
        assertFalse(response.headersToString().contains("PUT"));

    }


}
