import nmccabe.*;
import nmccabe.Handlers.*;
import nmccabe.Helper;
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
    public void returnsGenericHandlerAnd404ForGETToNonexistentResource() throws Exception {
        Request request = Helper.buildRequestFromString("GET /what-is-this-fiiiiiiiile HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);
        Response response = handler.getResponseFor(request);

        assertEquals(ResourceHandler.class, handler.getClass());
        assertEquals("404 Not Found", response.statusCode);
    }

    @Test
    public void returns405ForNonsenseMethod() throws Exception {
        Request request = Helper.buildRequestFromString("GOO /file1 HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);
        Response response = handler.getResponseFor(request);

        assertEquals(MethodNotAllowedHandler.class, handler.getClass());
        assertEquals("405 Method Not Allowed", response.statusCode);
    }

    @Test
    public void routesToMethodOptionsHandler() throws Exception {
        Request request = Helper.buildRequestFromString("OPTIONS /method_options HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodOptionsHandler.class, handler.getClass());
    }

    @Test
    public void routesToMethodOptions2HandlerViaGet() throws Exception {
        Request request = Helper.buildRequestFromString("OPTIONS /method_options2 HTTP/1.1\r\n\r\n");
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

        assertEquals(AddingStuffHandler.class, handler.getClass());
    }

    @Test
    public void routesTo405NotALlowedForPostToNotPostable() throws Exception {
        Request request = Helper.buildRequestFromString("POST /file1 HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodNotAllowedHandler.class, handler.getClass());
    }

    @Test
    public void routesPutHandlerForPutRequestToForm() throws Exception {
        Request request = Helper.buildRequestFromString("PUT /form HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(AddingStuffHandler.class, handler.getClass());
    }

    @Test
    public void routesDefaultHandlerForPutRequestToGarbage() throws Exception {
        Request request = Helper.buildRequestFromString("PUT /flogbarber HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(MethodNotAllowedHandler.class, handler.getClass());
    }

    @Test
    public void routesToHeadHandler() throws Exception {
        Request request = Helper.buildRequestFromString("HEAD / HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(HeadHandler.class, handler.getClass());
    }

    @Test
    public void routesToHeadHandlerIfRequestsToNonRoot() throws Exception {
        Request request = Helper.buildRequestFromString("HEAD /helloThere HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(HeadHandler.class, handler.getClass());
    }

    @Test
    public void routesToDeleteHandlerOnDELETE() throws Exception {
        Request request = Helper.buildRequestFromString("DELETE /form HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(DeleteHandler.class, handler.getClass());
    }

    @Test
    public void routesToRedirectHandlerOnGETToRedirect() throws Exception {
        Request request = Helper.buildRequestFromString("GET /redirect HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(RedirectHandler.class, handler.getClass());
    }

    @Test
    public void routesToResourceHandlerOnGETWithParams() throws Exception {
        String paramsRoute = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Request request = Helper.buildRequestFromString("GET " + paramsRoute +  " HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(ResourceHandler.class, handler.getClass());
    }

    @Test
    public void routesToAddingStuffHandlerOnPATCH() throws Exception {
        Request request = Helper.buildRequestFromString("PATCH /patch-content.txt HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(AddingStuffHandler.class, handler.getClass());
    }

    @Test
    public void routesToDirectoryHandler() throws Exception {
        Request request = Helper.buildRequestFromString("GET / HTTP/1.1\r\n\r\n");
        Handler handler = router.getHandlerFor(request);

        assertEquals(DirectoryHandler.class, handler.getClass());
    }
}

