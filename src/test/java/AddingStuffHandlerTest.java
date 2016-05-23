import nmccabe.*;
import nmccabe.FileReader;
import nmccabe.Handlers.AddingStuffHandler;
import nmccabe.Handlers.ResourceHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.Assert.assertEquals;

public class AddingStuffHandlerTest {
    String testFileName = "/testForm";
    String testFilePath;
    Request postRequest;
    File testFile;


    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
        testFilePath = System.getProperty("baseDir")  + testFileName;
        postRequest = Helper.buildRequestFromString("POST " + testFileName + " HTTP/1.0\r\n\r\ndata=whoa\r\n\r\n");
        testFile = new File(testFilePath);
        testFile.createNewFile();
    }

    @Test
    public void returns200OKForFormPost() throws Exception {
        Response response = new AddingStuffHandler().getResponseFor(postRequest);
        assertEquals("200 OK", response.statusCode);
    }

    @Test
    public void writesToFile() throws Exception {
        new AddingStuffHandler().getResponseFor(postRequest);
        String actual = new FileReader().read(testFilePath);
        assertEquals("data=whoa", actual);
    }

    @Test
    public void respondsWithBodyOfFileData() throws Exception {
        new AddingStuffHandler().getResponseFor(postRequest);
        Request getRequest = Helper.buildRequestFromString("GET " + testFileName + " HTTP/1.0\r\n\r\n");
        Response getResponse = new ResourceHandler().getResponseFor(getRequest);

        assertEquals("data=whoa\r\n", new String(getResponse.body));
    }

    @Test
    public void respondsToPATCHWith204() throws Exception {
        Request patchRequest = Helper.buildRequestFromString("PATCH " + testFileName + " HTTP/1.0\r\n\r\ndata=whoa\r\n\r\n");
        Response patchResponse = new AddingStuffHandler().getResponseFor(patchRequest);
        assertEquals("204 No Content", patchResponse.statusCode);
    }

    @After
    public void tearDown() throws IOException {
        deleteIfExists(testFile.toPath());
    }
}
