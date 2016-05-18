package nmccabe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Objects;

public class Resource implements IResource {
    private final String mimeType;
    private final byte[] byteData;

    public Resource(String path) throws IOException {
        byteData = getByteData(path);
        mimeType = getMimeType(path);
    }

    private String getMimeType(String path) {
        HashMap<String, String> mimeTypeMap = buildTypeMap();
        String fileSubType = "";

        String[] parts = path.split("\\.");

        if (parts.length >= 2) {
            fileSubType = (Objects.equals(parts[1], "txt")) ? "plain" : parts[1];
        }

        String fileType = mimeTypeMap.getOrDefault(fileSubType, "noType");

        return fileType + "/" + fileSubType;
    }

    private HashMap<String, String> buildTypeMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("png", "image");
        map.put("gif", "image");
        map.put("jpeg", "image");
        map.put("html", "text");
        map.put("plain", "text");
        return map;
    }

    @Override
    public String mimeType() {
        return mimeType;
    }

    @Override
    public byte[] byteData() {
        return byteData;
    }

    @Override
    public String getContentLength() {
        return Integer.toString(byteData().length);
    }

    private byte[] getByteData(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
}
