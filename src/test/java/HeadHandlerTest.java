import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadHandlerTest {
    @Test
    public void returns200OKForRequestToRoot() throws Exception {
        Request request = Helper.buildRequestFromString("HEAD / HTTP/1.0\r\n\r\n");
        Response response = new HeadHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }
}
