import nmccabe.Request;
import nmccabe.RequestBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helper {
    public static Request buildRequestFromString(String string) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        return new RequestBuilder().build(inputStream);
    }
}
