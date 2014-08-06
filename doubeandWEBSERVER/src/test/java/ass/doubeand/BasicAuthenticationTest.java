/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import junit.framework.TestCase;

/**
 *
 * @author Andrej
 */
public class BasicAuthenticationTest extends TestCase {
    String goodAuthHeader = "User-Agent:IE\nHost: localhost:5003\nAuthorization: Basic cm9vdDp0b29y\nOthertext\n";
    String badAuthHeader = "User-Agent:IE\nHost: localhost:5003\nAuthorization: Basic am1lbm86aGVzbG8=\nOthertext\n";
    String authHeaderSpecificPosition = "Othertext\nHost: localhost:5003\nsometextfromheader\nUser-Agent:IE\nAuthorization: Basic cm9vdDp0b29y\nOthertext\n";
    
    
    public BasicAuthenticationTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of validAuth method with header pass and user, of class BasicAuthentication.
     */
    public void testValidAuthWithGoodAuthHeader() {
        System.out.println("Test se správnýmy údaji v hlavičce");
        BasicAuthentication instance = new BasicAuthentication(goodAuthHeader);
        boolean expResult = true;
        boolean result = instance.validAuth();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of validAuth method, of class BasicAuthentication.
     */
        public void testValidAuthWithBadAuthHeader() {
        System.out.println("Test se špatnými udaji v hlavicce");
        BasicAuthentication instance = new BasicAuthentication(badAuthHeader);
        boolean expResult = false;
        boolean result = instance.validAuth();
        assertEquals(expResult, result);
    }
        
     /**
     * Test of validAuth method, of class BasicAuthentication.
     */
        public void testValidAuthWithSpecificAuthHeader() {
        System.out.println("Test se spravnymi udaji na jinem nez obvyklem miste");
        BasicAuthentication instance = new BasicAuthentication(authHeaderSpecificPosition);
        boolean expResult = true;
        boolean result = instance.validAuth();
        assertEquals(expResult, result);
    }
        

}
