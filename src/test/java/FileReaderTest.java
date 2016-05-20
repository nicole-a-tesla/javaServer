import nmccabe.FileReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    String testResourceDir = System.getProperty("user.dir") + "/src/test/testResources";
    String testFilePath = testResourceDir + "/text-file.txt";

    @Test
    public void readsFromFile() throws Exception {
        FileReader reader = new FileReader();
        String expected = "file1 contents";
        String actual = reader.read(testFilePath);;
        assertEquals(expected, actual);
    }
}
