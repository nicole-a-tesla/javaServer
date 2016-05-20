import nmccabe.*;
import nmccabe.FileReader;
import nmccabe.Handlers.PostHandler;
import nmccabe.Handlers.ResourceHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.Assert.assertEquals;

public class PostHandlerTest {
    String testFileName = "/testForm";
    String testFilePath = System.getProperty("baseDir")  + testFileName;
    Request postRequest;
    File testFile;


    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
        postRequest = Helper.buildRequestFromString("POST " + testFileName + " HTTP/1.0\r\n\r\ndata=whoa\r\n\r\n");
        testFile = new File(testFilePath);
        testFile.createNewFile();
    }

    @Test
    public void returns200OKForFormPost() throws Exception {
        Response response = new PostHandler().getResponseFor(postRequest);
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void writesToFile() throws Exception {
        new PostHandler().getResponseFor(postRequest);
        String actual = new FileReader().read(testFilePath);
        assertEquals("data=whoa", actual);
    }

    @Test
    public void respondsWithBodyOfFileData() throws Exception {
        new PostHandler().getResponseFor(postRequest);
        Request getRequest = Helper.buildRequestFromString("GET " + testFileName + " HTTP/1.0\r\n\r\n");
        Response getResponse = new ResourceHandler().getResponseFor(getRequest);

        assertEquals("data=whoa\r\n\r\n", new String(getResponse.body));
    }

    @After
    public void tearDown() throws IOException {
        deleteIfExists(testFile.toPath());
    }
}
