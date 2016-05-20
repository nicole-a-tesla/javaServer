package nmccabe;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Helper {
    public static Request buildRequestFromString(String string) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        return new RequestBuilder().build(inputStream);
    }

    public static String readFromBufferToString(BufferedReader buffReader) throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        String line;

        while ((line = buffReader.readLine()) != null) {
            strBuilder.append(line);
        }

        return strBuilder.toString();
    }
}
