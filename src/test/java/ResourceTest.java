import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class ResourceTest {
    public String testResourceDir = System.getProperty("user.dir") + "/src/test/testResources";

    @Test
    public void returnsGifType() throws IOException {
        Resource resource = new Resource(testResourceDir + "/image.gif");
        assertEquals("image/gif", resource.mimeType());
    }

    @Test
    public void returnsPngType() throws IOException {
        Resource resource = new Resource(testResourceDir + "/image.png");
        assertEquals("image/png", resource.mimeType());
    }

    @Test
    public void returnsHtmlType() throws IOException {
        Resource resource = new Resource(testResourceDir + "/test.html");
        assertEquals("text/html", resource.mimeType());
    }

    @Test
    public void returnsPlainTextType() throws Exception {
        Resource resource = new Resource(testResourceDir + "/text-file.txt");
        assertEquals("text/plain", resource.mimeType());
    }

    @Test
    public void returnsCorrectByteData() throws IOException {
        Resource resource = new Resource(testResourceDir + "/file1");
        byte[] expectedData = "file1 contents".getBytes();

        assertArrayEquals(expectedData, resource.byteData());
    }

    @Test
    public void returnsCorrectStringData() throws IOException {
        Resource resource = new Resource(testResourceDir + "/file1");
        String expectedData = "file1 contents";

        assertEquals(expectedData, resource.getBody());
    }
}
