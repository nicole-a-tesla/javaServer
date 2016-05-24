package nmccabe;

import java.io.IOException;

public class Logger {
    private final String filePath;

    public Logger(String logsFilePath) {
        this.filePath = logsFilePath;
    }

    public void log(Request request) throws IOException {
        String requestInfo = firstLineOfRequest(request);
        new FileWriter().append(this.filePath, requestInfo);
    }

    private String firstLineOfRequest(Request request) {
        return request.method + " " + request.route + " " + request.httpVersion + " ";
    }
}
