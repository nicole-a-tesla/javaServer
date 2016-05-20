import nmccabe.FileWriter;
import nmccabe.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.Assert.assertEquals;

public class FileWriterTest {
    public String testResourceDir = System.getProperty("user.dir") + "/src/test/testResources";
    String testFilePath = testResourceDir + "/testWriterFile.txt";
    File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = new File(testFilePath);
        testFile.createNewFile();
    }

    @Test
    public void writesToFiles() throws Exception {
        String writeThis = "I live in a file now";
        FileWriter writer = new FileWriter();
        writer.write(testFilePath, writeThis);

        FileReader fileReader = new FileReader(testFilePath);
        BufferedReader buffReader = new BufferedReader(fileReader);

        String actual = Helper.readFromBufferToString(buffReader);
        assertEquals(writeThis, actual);
    }

    @After
    public void tearDown() throws IOException {
        deleteIfExists(testFile.toPath());
    }
}
