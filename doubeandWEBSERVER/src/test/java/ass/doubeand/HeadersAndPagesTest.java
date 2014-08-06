/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Andrej
 */
public class HeadersAndPagesTest extends TestCase {
    
    public HeadersAndPagesTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of getHeader method, of class HeadersAndPages.
     */
    public void testGetHeader() {
        System.out.println("getHeader");
        String header = "HTTP/1.0 200 OK\r\n";
        int length = 255;
        String mimeType = "text/html";
        String expResult = "HTTP/1.0 200 OK\r\nContent-type: text/html\r\nContent-Length: 255\r\n\r\n";
        String result = HeadersAndPages.getHeader(header, length, mimeType);
        assertEquals(expResult, result);
    }

    /**
     * Test of get400Page method, of class HeadersAndPages.
     */
    public void testGet400Page() {
        System.out.println("get400Page");
        String expResult = "HTTP/1.0 400 Bad request\r\nContent-type:text/html\r\nContent-Length: 117\r\n\r\n<html><head><title>400 Bad Request</title></head><body><p><center><H1>400 Bad request</H1></center></p></body></html>";
        String result = HeadersAndPages.get400Page();
        assertEquals(expResult, result);
    }

    /**
     * Test of get404Page method, of class HeadersAndPages.
     */
    public void testGet404Page() {
        System.out.println("get404Page");
        String expResult = "HTTP/1.0 404 Not found\r\nContent-type:text/html\r\nContent-Length: 113\r\n\r\n<html><head><title>404 Not found</title></head><body><p><center><H1>404 Not found</H1></center></p></body></html>";
        String result = HeadersAndPages.get404Page();
        assertEquals(expResult, result);
    }

    /**
     * Test of get401Page method, of class HeadersAndPages.
     */
    public void testGet401Page() {
        System.out.println("get401Page");
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        Date date = new Date();
        String actualTime = dateFormat.format(date);
        String expResult = "HTTP/1.0 401 Authorization Required\r\nDate: "+actualTime +"GMT\r\nWWW-Authenticate: Basic realm=\"Secure Area\"\r\n" + "Content-type:text/html\r\nContent-Length: 139\r\n\r\n<html><head><title>401 Authorization Required</title></head><body><p><center><H1>401 Authorization Required</H1></center></p></body></html>";
        String result = HeadersAndPages.get401Page();
        assertEquals(expResult, result);
    }
}
