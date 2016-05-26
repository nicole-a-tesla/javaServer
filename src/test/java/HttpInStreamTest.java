import nmccabe.HttpInStream;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class HttpInStreamTest {
    @Test
    public void isStuff() throws Exception {
        InputStream fakeInnerIn = new InputStream() {
            public int read() throws IOException {return 0;}
        };
        HttpInStream in = new HttpInStream(fakeInnerIn);

    }
}
