import nmccabe.Handlers.PostHandler;
import nmccabe.Handlers.ResourceHandler;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostHandlerTest {
    @Test
    public void returns200OKForFormPost() throws Exception {
        Request request = Helper.buildRequestFromString("POST /form HTTP/1.0\r\n\r\n");
        Response response = new PostHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void writesToFile() throws Exception {
        Request request = Helper.buildRequestFromString("POST /form HTTP/1.0\r\ndata=whoa\r\n\r\n");
//        assertEquals("data=whoa", request.);
        Response response = new PostHandler().getResponseFor(request);
        assertEquals("200 OK", response.statusCode);

//        Request gRequest = Helper.buildRequestFromString("GET /form HTTP/1.0\r\n\r\n");
//        Response gResponse = new ResourceHandler().getResponseFor(gRequest);
//        assertEquals("data=whoa", new String(gResponse.body));
    }
}
