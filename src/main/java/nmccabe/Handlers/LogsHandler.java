package nmccabe.Handlers;

import nmccabe.FileReader;
import nmccabe.Request;
import nmccabe.Response;

import java.io.IOException;
import java.util.Objects;

public class LogsHandler extends Handler {
    private String logFilePath;

    public LogsHandler(String logFilePath) {
       this.logFilePath = logFilePath;
    }

    public LogsHandler() {
        this(System.getProperty("baseDir") + "/logs.txt");
    }

    @Override
    public Response getResponseFor(Request request) throws IOException {
        if (requestIsAuthorized(request)) {
            return buildOKLogsResponse();
        } else {
            return buildUnauthorizedResponse();
        }
    }

    private boolean requestIsAuthorized(Request request) {
        return Objects.equals(request.getHeader("Authorization:"), "Basic");
    }

    private Response buildOKLogsResponse() throws IOException {
        Response response = buildResponseForStatus(OK_STATUS);
        response.addBody(getLogs());
        return response;
    }

    private byte[] getLogs() throws IOException {
        FileReader reader = new FileReader();
        String fileContents = reader.read(logFilePath);
        return fileContents.getBytes();
    }

    private Response buildUnauthorizedResponse() {
        Response response = buildResponseForStatus(UNAUTHORIZED_STATUS);
        response.addHeader("WWW-Authenticate:", "Basic realm=ohHai");
        return response;
    }
}
