import nmccabe.HttpCodes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpCodesTest {

    @Test
    public void okReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.OK, "200 OK");
    }

    @Test
    public void notFoundReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.NOT_FOUND, "404 Not Found");
    }

    @Test
    public void partialStatusReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.PARTIAL, "206 Partial");
    }

    @Test
    public void noContentStatusReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.NO_CONTENT, "204 No Content");
    }

    @Test
    public void unauthorizedStatusReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.UNAUTHORIZED, "401 Unauthorized");
    }

    @Test
    public void methodNotAllowedReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.METHOD_NOT_ALLOWED, "405 Method Not Allowed");
    }

    @Test
    public void teapotReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.IM_A_TEAPOT, "418 I'm a teapot");
    }

    @Test
    public void foundReturnsCorrectString() throws Exception {
        assertEquals(HttpCodes.FOUND, "302 Found");
    }
}
