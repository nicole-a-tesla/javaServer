import org.junit.Test;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class WorkerTest {
    Worker worker = new Worker();

    @Test
    public void responds200OKForValidRequest() throws IOException {
        Request request = Helper.buildRequestFromString("GET / HTTP/1.0\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n");
        Response response = worker.getResponse(request);

        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void responds404NotFoundForNonexistentResource() throws IOException {
        Request request = Helper.buildRequestFromString("GET /no/such/file.html HTTP/1.0\r\n\r\n");
        Response response = worker.getResponse(request);

        assertEquals("404 Not Found", response.statusCode);

    }

    @Test
    public void navigatesCorrectFilePathToContent() throws IOException {
        Server server = new Server();
        HashMap args = new HashMap();
        String testResourceDir = System.getProperty("user.dir") + "/src/test/testResources";
        args.put("-d", testResourceDir);
        server.setUp(args);

        Request request = Helper.buildRequestFromString("GET /file1 HTTP/1.0\r\n\r\n");
        Response response = worker.getResponse(request);

        String expectedBody = "file1 contents";

        assertEquals(expectedBody, response.body);
    }

}
