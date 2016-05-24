import nmccabe.*;
import nmccabe.Handlers.Handler;
import nmccabe.Handlers.LogsHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LogsHandlerTest {
    Request unauthorizedRequest;
    Request authorizedRequest;


    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
        unauthorizedRequest = Helper.buildRequestFromString("GET /logs HTTP/1.0\r\n\r\n");

        authorizedRequest = Helper.buildRequestFromString("GET /logs HTTP/1.0\r\n\r\n");
        authorizedRequest.headers.put("Authorization:", "admin:hunter2");
    }

    @Test
    public void returns401ForPlainGet() throws Exception {
        Response response = new LogsHandler().getResponseFor(unauthorizedRequest);
        assertEquals("401 Unauthorized", response.statusCode);
    }

    @Test
    public void appendsAuthenticateHeaderToResponse() throws Exception {
        Response response = new LogsHandler().getResponseFor(unauthorizedRequest);
        assertEquals("Basic realm=ohHai", response.getHeader("WWW-Authenticate:"));
    }

    @Test
    public void returns200ForLoggedInGet() throws Exception {
        Response response = new LogsHandler().getResponseFor(authorizedRequest);
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void returnsLogsForAuthorizedGET() throws Exception {
        Router router = new Router();

        Request getRootRequest = Helper.buildRequestFromString("GET / HTTP/1.0\r\n\r\n");
        Request headRequest = Helper.buildRequestFromString("HEAD / HTTP/1.0\r\n\r\n");

        router.getHandlerFor(getRootRequest).getResponseFor(getRootRequest);
        router.getHandlerFor(headRequest).getResponseFor(headRequest);

        Response response = new LogsHandler().getResponseFor(authorizedRequest);
        assertEquals("BUTTS", new String(response.body));

    }

}
