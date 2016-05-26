import nmccabe.Handlers.LogsHandler;
import nmccabe.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class LogsHandlerTest {
    Request unauthorizedRequest;
    Request authorizedRequest;


    @Before
    public void setUp() throws IOException {
        Helper.configTestServer();

        unauthorizedRequest = Helper.buildRequestFromString("GET /logs HTTP/1.0\r\n\r\n");
        authorizedRequest = Helper.buildRequestFromString("GET /logs HTTP/1.0\r\n\r\n");
        authorizedRequest.setHeader("Authorization:", "Basic");
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
    public void returnsLogsFileContentsForAuthorizedGET() throws Exception {
        String fakeLogFile = System.getProperty("baseDir") + "/file1";
        Response response = new LogsHandler(fakeLogFile).getResponseFor(authorizedRequest);
        assertEquals("file1 contents", new String(response.body));
    }

    @After
    public void tearDown() throws IOException {
        Helper.tearDownTestServer();
    }
}
