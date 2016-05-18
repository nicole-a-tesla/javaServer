import nmccabe.Server;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void setsBaseDir() throws IOException {
        Server server = new Server();
        HashMap args = new HashMap();
        args.put("-d", "/Users");

        server.setUp(args);

        assertEquals("/Users", System.getProperty("baseDir"));
    }

}
