import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Resource {
    private String mimeType;
    private byte[] byteData;

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

        String fileMetaType = mimeTypeMap.get(fileSuffix);

        return fileMetaType + "/" + fileSuffix;

    }

    public String mimeType() {
        return mimeType;
    }

    public byte[] byteData() {
        return byteData;
    }

    public String stringData() {
        return new String(byteData);
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
