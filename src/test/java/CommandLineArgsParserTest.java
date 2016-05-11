import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class CommandLineArgsParserTest {
    HashMap parsedArgs;

    @Before
    public void setUp() {
        String[] args = {"-p", "5000", "-d", "this/rad/file.html"};
        CommandLineArgsParser parser = new CommandLineArgsParser(args);
        parsedArgs = parser.parse();
    }

    @Test
    public void testGetsCorrectPort() {
        assertEquals(parsedArgs.get("-p"), 5000);
    }

    @Test
    public void testGetsCorrectDir() {
        assertEquals(parsedArgs.get("-d"), "this/rad/file.html");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsErrorOnTooManyArgs() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-p 5000 -d this/rad/file.html -x HELLO!".split(" "));
        parsedArgs = parser.parse();
    }

    @Test
    public void testNoErrorThrownOnMissingPort() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-d this/rad/file.html".split(" "));
        parsedArgs = parser.parse();
        assertEquals(parsedArgs.get("-d"), "this/rad/file.html");
    }

    @Test
    public void testNoErrorThrownOnMissingDir() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-p 5000".split(" "));
        parsedArgs = parser.parse();
        assertEquals(parsedArgs.get("-p"), 5000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorThrownOnNonsenseArg() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-z someValue".split(" "));
        parsedArgs = parser.parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorThrownOnNonsenseArgPlusValidArg() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-p 5000 -z someValue".split(" "));
        parsedArgs = parser.parse();
    }

    @Test
    public void testNoErrorOnReversedArgs() {
        CommandLineArgsParser parser = new CommandLineArgsParser("-d this/rad/file.html -p 5000".split(" "));
        parsedArgs = parser.parse();
        assertEquals(parsedArgs.get("-p"), 5000);
        assertEquals(parsedArgs.get("-d"), "this/rad/file.html");
    }
}
