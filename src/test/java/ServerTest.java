import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void setsBaseDirAndPort() throws IOException {
        Server server = new Server();
        HashMap args = new HashMap();
        args.put("-d", "/Users");
        args.put("-p", 8080);

        server.setUp(args);

        assertEquals("/Users", server.BASE_DIR);
        assertEquals(8080, server.PORT);
    }

}
