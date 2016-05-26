import nmccabe.HttpInStream;
import nmccabe.HttpOutStream;
import nmccabe.Logger;
import nmccabe.ServerWorker;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerWorkerTest {
    @Test
    public void servesResponds() throws Exception {
        HttpInStream fakeIn = mock(HttpInStream.class);
        String fakeRequest = "GET /fake-path HTTP/1.1\r\n\r\n";
        when(fakeIn.readString()).thenReturn(fakeRequest);

        OutputStream spyStream = new ByteArrayOutputStream();
        HttpOutStream spyOut = new HttpOutStream(spyStream);

        Logger fakeLogger = mock(Logger.class);

        String expectedOutString = "HTTP/1.1 404 Not Found\r\n" +
                                   "Content-Length: 0\r\n" +
                                   "\r\n\r\n\r\n";

        new ServerWorker(fakeIn, spyOut, fakeLogger).run();
        assertEquals(expectedOutString, spyStream.toString());
    }
}
