import nmccabe.FileReader;
import nmccabe.Helper;
import nmccabe.Logger;
import nmccabe.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

public class LoggerTest {
    File mockLogFile;
    String mockLoggingFilePath;
    Logger logger;

    @Before
    public void setUp() throws IOException {
        Helper.configTestServer();
        mockLoggingFilePath = System.getProperty("testLogPath");
        mockLogFile = new File(mockLoggingFilePath);
        mockLogFile.createNewFile();
        logger = new Logger(mockLoggingFilePath);
    }

    @Test
    public void logsRequests() throws Exception {
        Request request = Helper.buildRequestFromString("HEAD / HTTP/1.1\r\n");
        logger.log(request);
        String logFileContents = new FileReader().read(mockLoggingFilePath);

        assertEquals("HEAD / HTTP/1.1 ", logFileContents);

        Request secondRequest = Helper.buildRequestFromString("PUT / HTTP/1.1\r\n");
        logger.log(secondRequest);
        String newLogFileContents = new FileReader().read(mockLoggingFilePath);

        assertEquals("HEAD / HTTP/1.1 PUT / HTTP/1.1 ", newLogFileContents);

    }

    @After
    public void tearDown() throws IOException {
        Helper.tearDownTestServer();
    }
}
