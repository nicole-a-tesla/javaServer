package nmccabe;
import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public String read(String filePath) throws IOException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath);
        BufferedReader buffReader = new BufferedReader(fileReader);
        return Helper.readFromBufferToString(buffReader);
    }
}

