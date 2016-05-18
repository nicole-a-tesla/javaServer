import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostHandlerTest {
    @Test
    public void returns200OKForFormPost() throws Exception {
        Request request = Helper.buildRequestFromString("POST /form HTTP/1.0\r\n\r\n");
        Response response = new PostHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }
}
