package ass.doubeand;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Main 
{
    
    public static void main( String[] args ) throws InterruptedException
    {
       // Spousteni serveru s prednastavenymi parametry
       Server s = new Server();
       Thread serverThread = new Thread(s);
       serverThread.start();
    }
}
