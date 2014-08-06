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
public class CacheTest extends TestCase {
    public CacheTest(String testName) {
        super(testName);
    }

    /**
     * Test of addByteObj method, of class Cache.
     */
    
    
    /**
     * Test of getStaticCache method, of class Cache.
     */
    public void testGetStaticCache() {
        System.out.println("getStaticCache");
        Cache expResult = Cache.getStaticCache();
        Cache result = Cache.getStaticCache();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class Cache.
     */
    public void testGetNonExistData() {
        System.out.println("Get dat ktere v cachi nejsou");
        Cache instance = Cache.getStaticCache();
        byte[] expResult = null;
        byte[] result = instance.get("somePath");
        assertEquals(expResult, result);
    }
    
    public void testGetExistData(){
         System.out.println("Get dat ktere v cachi jsou");
         Cache instance = Cache.getStaticCache();
         byte[] prvek = new byte[100];
         instance.addByteObj("path", prvek);
         byte[] expResult = prvek;
         byte[] result = instance.get("path");
         assertEquals(expResult,result);
         
         
    }
    
    
     public void testAddByteObj() {
        System.out.println("addByteObj");
        String path = "path";
        boolean expResult = true;
        boolean result = false;
        byte[] content = new byte[70];
        byte[] content1 = new byte[70];
        byte[] content2 = new byte[70];
        Cache instance = Cache.getStaticCache();
        instance.addByteObj("path", content);
        instance.addByteObj("path1", content1);
        instance.addByteObj("path2", content2);
        if(instance.get("path").equals(content) && instance.get("path1").equals(content1)&&instance.get("path2").equals(content2)){
        result = true;
        }
        assertEquals(expResult,result);
    }
}
