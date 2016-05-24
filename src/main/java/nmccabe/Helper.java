package nmccabe;

import java.io.*;
import java.util.HashMap;

import static java.nio.file.Files.deleteIfExists;

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

    public static void configTestServer() throws IOException {
        String mockLoggingFilePath = System.getProperty("user.dir") + "/src/test/testResources/testLogFile.txt";
        System.setProperty("testLogPath", mockLoggingFilePath);

        HashMap<String, String> args = new HashMap<>();
        args.put("--logs", mockLoggingFilePath);
        new Server().setUp(args);
    }

    public static void tearDownTestServer() throws IOException {
        String mockPath = System.getProperty("testLogPath");
        File mockLogFile = new File(mockPath);
        deleteIfExists(mockLogFile.toPath());

    }

}
