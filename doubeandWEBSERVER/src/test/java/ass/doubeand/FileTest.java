/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Andrej
 */
public class FileTest extends TestCase {
    String path = "./src/main/resources/test/testHTML.html";
    File instance = new File(path);
    FileInputStream r = new FileInputStream(path);
    
    public FileTest(String testName) throws FileNotFoundException, IOException {
        super(testName);
    }
    /**
     * Test of getLength method, of class File.
     */
    public void testGetLength() throws Exception {
        int expResult = r.available();
        int result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMIMEType method, of class File.
     */
    public void testGetMIMEType() {
        String expResult = "text/html";
        String result = instance.getMIMEType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getByteOut method, of class File.
     */
    public void testGetByteOut() throws Exception {
        byte[] expResult = new byte[r.available()];
        r.read(expResult);
        byte[] result = instance.getByteOut();
        assertEquals(expResult.length, result.length);

    }
}
