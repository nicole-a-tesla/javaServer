package nmccabe.Handlers;

import nmccabe.HTMLifier;
import nmccabe.Request;
import nmccabe.Response;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DirectoryHandler extends Handler {
    HTMLifier HTMLifier;

    public DirectoryHandler() {
        HTMLifier = new HTMLifier();
    }

    @Override
    public Response getResponseFor(Request request) throws IOException {
        Response response = buildResponseForStatus(OK_STATUS);
        byte[] bodyBytes = buildBody();
        response.updateBody(bodyBytes);
        return response;
    }

    private byte[] buildBody() {
        File directory = new File(System.getProperty("baseDir"));
        List<String> files = Arrays.asList(directory.list());

        String htmlStart = HTMLifier.getHtmlStart();
        String htmlBody = HTMLifier.asUnorderedListOfLinks(files);
        String htmlEnd = HTMLifier.getHTMLEnd();

        return (htmlStart + htmlBody + htmlEnd).getBytes();
    }

}
