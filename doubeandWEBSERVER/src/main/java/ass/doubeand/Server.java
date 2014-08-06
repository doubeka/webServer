/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Andrej
 */
public class Server implements Runnable{
   private ServerSocket serverSocket; 
   private ExecutorService workerPool;
   //Zde jsou vsechna dulezita data
   private static String serverName = "Default web server name";
   private static String baseHref = "./src/main/resources/WEBSERVER";
   private static int port = 5004;
   private final int backlog = 100;
   private boolean run = true;
   //Autentifikace
   private static final String user="root";
   private static final String password = "toor";
   
    /**
    * Konstruktor pro server s defaultnim nastavenim
    */
   public Server(){
       System.out.println("Server s nazvem: "+ serverName +" je spusten na portu "+port);
   }

   
   /**
    * Metoda pro spojení se sockety
    */
    public void run(){
    int numOfThreads = Runtime.getRuntime().availableProcessors();
    try {
             serverSocket = new ServerSocket(port,backlog);
             workerPool = Executors.newFixedThreadPool(numOfThreads);
             
             while(run){             
             Socket s1 = serverSocket.accept();
             workerPool.execute(new RequestThread(s1));
            }
             
    }
    
    catch (IOException e) {
        System.out.println("Could not listen on port:"+port);
        System.out.println(e.toString());
        return;
    }
    }
    
    public void shutdown(){
        this.run = false;
    }

public static String getServerName(){
    return serverName;
}

public static String getBaseHref(){
    return baseHref;
}

public static int getPort(){
    return port;
}

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }



}
