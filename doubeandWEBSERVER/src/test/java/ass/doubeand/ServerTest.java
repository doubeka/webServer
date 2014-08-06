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
public class ServerTest extends TestCase {
    
    public Server instance = new Server();
    public Thread t = new Thread(instance);
    
    public ServerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        t.start();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of run method, of class Server.
     */
    public void testRun() throws InterruptedException {
        System.out.println("run");
        boolean expResult = true;
        boolean result = t.isAlive();
        assertEquals(expResult, result);

    }

    /**
     * Test of shutdown method, of class Server.
     */
    public void testShutdown() throws InterruptedException {
        System.out.println("shutdown serveru");
        boolean expResult = false;
        instance.shutdown();
        Thread.sleep(1000);
        boolean result = t.isAlive();
        assertEquals(expResult,result);
    }
}
