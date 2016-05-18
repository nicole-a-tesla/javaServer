import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RouterTest {
    public Router router;

    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
        router = new Router();
    }

    @Test
    public void fileCheckTrueForRealFile() throws Exception {
        assertTrue(router.isFile("/file1"));
    }

    @Test
    public void fileCheckFalseForUnrealFile() throws Exception {
        assertFalse(router.isFile("/fakeFile"));
    }

    @Test
    public void returnsGenericHandlerAnd404AsDefault() throws Exception {
        Request request = Helper.buildRequestFromString("GET /what-is-this-fiiiiiiiile HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);
        Response response = handler.getResponseFor(request);

        assertEquals(Handler.class, handler.getClass());
        assertEquals("404 Not Found", response.statusCode);
    }

    @Test
    public void routesToMethodOptionsHandler() throws Exception {
        Request request = Helper.buildRequestFromString("GET /method_options HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodOptionsHandler.class, handler.getClass());
    }

    @Test
    public void routesToMethodOptions2HandlerViaGet() throws Exception {
        Request request = Helper.buildRequestFromString("GET /method_options2 HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodOptions2Handler.class, handler.getClass());
    }


    @Test
    public void routesToMethodOptions2HandlerViaOption() throws Exception {
        Request request = Helper.buildRequestFromString("OPTIONS /method_options2 HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodOptions2Handler.class, handler.getClass());
    }

    @Test
    public void routesToResourceHandler() throws Exception {
        Request request = Helper.buildRequestFromString("GET /text-file.txt HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(ResourceHandler.class, handler.getClass());
    }

    @Test
    public void routesToTeapotHandlerForTea() throws Exception {
        Request request = Helper.buildRequestFromString("GET /tea HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(TeapotHandler.class, handler.getClass());
    }

    @Test
    public void routesToTeapotHandlerForCoffee() throws Exception {
        Request request = Helper.buildRequestFromString("GET /coffee HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(TeapotHandler.class, handler.getClass());
    }

    @Test
    public void routesToPostForPostToForm() throws Exception {
        Request request = Helper.buildRequestFromString("POST /form HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(PostHandler.class, handler.getClass());
    }

    @Test
    public void routesToDefaultHandlerForPostToGarbage() throws Exception {
        Request request = Helper.buildRequestFromString("POST /blerfgorp HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(Handler.class, handler.getClass());
    }
}

