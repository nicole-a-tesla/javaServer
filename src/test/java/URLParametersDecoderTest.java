import nmccabe.URLParametersDecoder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class URLParametersDecoderTest {
    URLParametersDecoder decoder = new URLParametersDecoder();
    String simpleParams = "variable_2=stuff";
    String complexParams = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";

    @Test
    public void decodesSimpleParams() throws Exception {
        String decoded = decoder.decode(simpleParams);
        assertEquals("variable_2 = stuff", decoded);
    }

    @Test
    public void decodesComplexParams() throws Exception {
        String decoded = decoder.decode(complexParams);
        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?", decoded);
    }

    @Test
    public void decodesMultipleParams() throws Exception {
        String decoded = decoder.decode(complexParams + "&" + simpleParams);
        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?&variable_2 = stuff", decoded);
    }
}
