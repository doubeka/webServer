/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.IOException;
import java.net.Socket;
import junit.framework.TestCase;

/**
 *
 * @author Andrej
 */
public class RequestThreadTest extends TestCase {
    
    public RequestThreadTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of authentication method, of class RequestThread.
     */
    public void testAuthenticationRightHeader() throws IOException{
        System.out.println("testAuthenticationRightHeader");
        String header = "User-Agent:IE\nHost: localhost:5003\nAuthorization: Basic cm9vdDp0b29y\nOthertext\n";
        boolean expResult = true;
        boolean result = RequestThread.authentication(header);
        assertEquals(expResult, result);
    }
    
     public void testAuthenticationBadHeader() throws IOException{
        System.out.println("testAuthenticationBadHeader");
        String header = "User-Agent:IE\nHost: localhost:5003\nOthertext\n";
        boolean expResult = false;
        boolean result = RequestThread.authentication(header);
        assertEquals(expResult, result);
    }
}
