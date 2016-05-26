import nmccabe.*;
import nmccabe.Handlers.AddingStuffHandler;
import nmccabe.Handlers.DeleteHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class DeleteHandlerTest {
    String testFileName;
    String testFilePath;

    @Before
    public void setUp() throws IOException {
        new Server().setUp(new HashMap());
        testFileName = "/testForm";
        testFilePath = System.getProperty("baseDir")  + testFileName;

        File testFile = new File(testFilePath);
        testFile.createNewFile();
    }

    @Test
    public void returns200() throws Exception {
        Request request = Helper.buildRequestFromString("DELETE " + testFileName + " HTTP/1.0\r\n\r\n");
        Response response = new DeleteHandler().getResponseFor(request);

        assertEquals("200 OK", response.statusCode());
    }

    @Test
    public void deletesStuff() throws Exception {
        Request postRequest = Helper.buildRequestFromString("POST " + testFileName + " HTTP/1.0\r\n\r\ndata=whoa\r\n\r\n");
        new AddingStuffHandler().getResponseFor(postRequest);

        Request deleteRequest = Helper.buildRequestFromString("DELETE " + testFileName +  " /form HTTP/1.0\r\n\r\n");
        new DeleteHandler().getResponseFor(deleteRequest);
        String newData = new FileReader().read(testFilePath);
        assertEquals("", newData);

    }
}
