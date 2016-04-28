package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class CommandLineArgsParser {
    public HashMap argsMap;
    private String[] splitArgs;
    private List<Integer> validLengths = Arrays.asList(0, 2, 4);
    private List<String> validFlags = Arrays.asList("-p", "-d");
    private String errorMessage = "Correct format: -p PORT_NUMBER -d DIRECTORY";

    public CommandLineArgsParser(String args) {
        argsMap = new HashMap<String, String>();
        splitArgs = args.split(" ");
    }

    public HashMap parse() {

        if (validLengths.contains(splitArgs.length)) {

            if (splitArgs.length >= 2) {
                setFirstArgs();
            }
            if (splitArgs.length == 4) {
                setSecondArgs();
            }
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
        return argsMap;
    } 

    private void setFirstArgs() {
        if (validFlags.contains(splitArgs[0])) {
            argsMap.put(splitArgs[0], splitArgs[1]);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }

    }

    private void setSecondArgs() {
        if (validFlags.contains(splitArgs[2])) {
            argsMap.put(splitArgs[2], splitArgs[3]);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }

    }
}
