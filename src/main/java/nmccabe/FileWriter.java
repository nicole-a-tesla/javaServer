package nmccabe;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    public void write(String fileName, String writeThis) throws IOException {
        byte data[] = writeThis.getBytes();

        FileOutputStream out = new FileOutputStream(fileName);
        out.write(data);
        out.close();
    }

    public void deleteFrom(String deleteFrom) throws IOException {
        write(deleteFrom, "");
    }
}
