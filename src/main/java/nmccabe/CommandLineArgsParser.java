package nmccabe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandLineArgsParser {
    private HashMap argsMap;
    private String[] args;
    private List<Integer> validLengths = Arrays.asList(0, 2, 4);
    private String errorMessage = "Correct format: -p PORT_NUMBER -d DIRECTORY";

    public CommandLineArgsParser(String[] args) {
        this.argsMap = new HashMap<String, String>();
        this.args = args;
    }

    public HashMap parse() {

        if (validLengths.contains(args.length)) {
            if (args.length >= 2) setArgs(args[0], args[1]);
            if (args.length == 4) setArgs(args[2], args[3]);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
        return argsMap;
    }

    private void setArgs(String key, String value) {
        switch (key) {
            case "-p":
                argsMap.put(key, Integer.parseInt(value));
                break;
            case "-d":
                argsMap.put(key, value);
                break;
            default:
                throw new IllegalArgumentException(errorMessage);
        }
    }
}
