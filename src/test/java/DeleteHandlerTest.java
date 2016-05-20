import nmccabe.Handlers.DeleteHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteHandlerTest {
    @Test
    public void returns200() throws Exception {
        Request request = Helper.buildRequestFromString("DELETE / HTTP/1.0\r\n\r\n");
        Response response = new DeleteHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }
}
