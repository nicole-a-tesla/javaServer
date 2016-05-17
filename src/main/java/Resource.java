import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Resource implements IResource {
    private final String mimeType;
    private final byte[] byteData;

    public Resource(String path) throws IOException {
        byteData = getByteData(path);
        mimeType = getMimeType(path);
    }

    private String getMimeType(String path) {
        HashMap<String, String> mimeTypeMap = buildTypeMap();
        String fileSuffix;

        String[] parts = path.split("\\.");
        if (parts.length >= 2) {
            fileSuffix = parts[1];
        } else {
            fileSuffix = "";
        }

        String fileMetaType = mimeTypeMap.getOrDefault(fileSuffix, "noType");

        return fileMetaType + "/" + fileSuffix;
    }

    public String mimeType() {
        return mimeType;
    }

    public byte[] byteData() {
        return byteData;
    }

    @Override
    public String getBody() {
        return new String(byteData);
    }

    @Override
    public String getContentLength() {
        return Integer.toString(byteData().length);
    }

    private HashMap<String, String> buildTypeMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("png", "image");
        map.put("gif", "image");
        map.put("html", "text");
        return map;
    }

    private byte[] getByteData(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
}
