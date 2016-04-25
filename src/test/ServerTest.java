package test;

import main.ServerRunner;
import main.Server;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void testHasRunMethod() {
        Server server = new Server();
        assertEquals(server.run(), "200");
    }

//    @Test
//    public void testTakesArgs
}
