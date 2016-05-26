import nmccabe.Handlers.ParamsHandler;
import nmccabe.Helper;
import nmccabe.Request;
import nmccabe.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParamsHandlerTest {

    @Test
    public void respondsWithVariablesIfParamsPassed() throws Exception {
        String paramsReq = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        Request request = Helper.buildRequestFromString("GET " + paramsReq + " HTTP/1.1\r\n\r\n");
        Response response = new ParamsHandler().getResponseFor(request);
        String bodyString = new String(response.body());

        assertTrue(bodyString.contains("variable_2 = stuff"));
        assertTrue(bodyString.contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
    }
}
