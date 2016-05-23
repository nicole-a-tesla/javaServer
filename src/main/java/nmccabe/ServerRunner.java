package nmccabe;

import java.io.IOException;
import java.util.HashMap;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        System.out.print("RUNNING");
        System.out.print("\r\n");
        Server server = new Server();
        CommandLineArgsParser parser = new CommandLineArgsParser(args);
        HashMap parsedArgs = parser.parse();

        server.start(parsedArgs);
    }

}
