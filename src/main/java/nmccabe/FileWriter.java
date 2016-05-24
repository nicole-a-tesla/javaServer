package nmccabe;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public void deleteFrom(String deleteFrom) throws IOException {
        write(deleteFrom, "");
    }

    public void write(String fileName, String writeThis) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        writeOrAppend(out, writeThis);
    }

    public void append(String fileName, String writeThis) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName, true);
        writeOrAppend(out, writeThis);
    }

    private void writeOrAppend(FileOutputStream out, String writeThis) throws IOException {
        byte[] data = writeThis.getBytes();
        out.write(data);
        out.close();
    }
}
