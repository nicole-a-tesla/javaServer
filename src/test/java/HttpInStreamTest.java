import nmccabe.HttpInStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;

public class HttpInStreamTest {
    @Test
    public void returnsStringRepresentationOfByteData() throws Exception {
        String myString = "lol";
        InputStream fakeInnerIn = new ByteArrayInputStream(myString.getBytes());
        HttpInStream httpIn = new HttpInStream(fakeInnerIn);
        assertEquals(httpIn.readString().substring(0, 3), myString);

    }
}
