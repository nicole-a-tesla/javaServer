import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class WorkerTest {
    @Test
    public void responds200OKForValidRequest() throws IOException {
        String str = "GET /nice/file.html HTTP/1.0\r\nI'm-A-Key: I'm-A-Value\r\nAnother-Key: Another-Value\r\n\r\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        RequestBuilder builder = new RequestBuilder();
        Request request = builder.build(reader);
        Worker worker = new Worker();
        Response response = worker.getResponse(request);
        String expectedResponseString = "HTTP/1.0 200 OK\r\n" +
                "Content-Length: 0\r\n" + "Content-Type: text/html\r\n";
        assertEquals(expectedResponseString, response.toString());
    }

}
