import nmccabe.HttpOutStream;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import static org.junit.Assert.assertEquals;

public class HttpOutStreamTest {
    @Test
    public void writesAndReturnsCorrectString() throws Exception {
        OutputStream innerOut = new ByteArrayOutputStream();
        HttpOutStream httpOut = new HttpOutStream(innerOut);

        byte[] stringBytes = "I'm bytes".getBytes();

        httpOut.write(stringBytes);

        assertEquals("I'm bytes", httpOut.toString());


    }
}
