import nmccabe.Handlers.PutHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PutHandlerTest {
    @Test
    public void returns200OKForFormPost() throws Exception {
        Request request = Helper.buildRequestFromString("PUT /form HTTP/1.0\r\n\r\n");
        Response response = new PutHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }
}
