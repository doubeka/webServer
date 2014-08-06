/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.Socket;
import java.net.URLConnection;
import java.util.StringTokenizer;


/**
 *
 * @author Andrej
 */


public class RequestThread implements Runnable{
            private BufferedReader in = null;
            private String firstLine = null;
            private StringTokenizer tokenizer;
            private String path ="/";
            private String typeOfHttp = "";
            private boolean access = false;
            private final String basehref = Server.getBaseHref();
            private final String nameOfServer = Server.getServerName();
            private OutputStream out;
            private FileInputStream filein;
            private Socket s1;
            private File file;


    public RequestThread(Socket s1) throws IOException{
        this.s1 = s1;
        this.out = s1.getOutputStream();
    }
    
    
    public void run() {
        getData(); 
    }
    
    public void getData(){
        try {

               in = new BufferedReader(new InputStreamReader(s1.getInputStream()));             
               firstLine = in.readLine();

                
                // Test for null request from broswer
                if(firstLine == null){
                    System.out.println("Odpojeno");
                    closeSocket();
                    return;
                }
            
            // Zjisteni pozadovane cesty k souboru z GET radky  
            tokenizer = new StringTokenizer(firstLine);
            if(tokenizer.nextToken().equals("GET")){
                path = basehref + tokenizer.nextToken().toLowerCase();
                typeOfHttp = tokenizer.nextToken();
            }
            else{
            out.write(HeadersAndPages.get400Page().getBytes());
            closeSocket();
            }
            //Ulozeni dalsi casti headeru
            String s = "";
            StringBuilder headerBuild= new StringBuilder();
            while((s = in.readLine()).length() != 0){    
                headerBuild.append(s);
                headerBuild.append("\n");
            } 
            String header = headerBuild.toString();
                     
            access = authentication(header);
            if (!access) {
            out.write(HeadersAndPages.get401Page().getBytes());
                return;
            }
            //Soubor
            try{
            file = new File(path);
            }
            catch(FileNotFoundException ex){
            out.write(HeadersAndPages.get404Page().getBytes());
            closeSocket();
            return;
            }
            
            int length = file.getLength();
            
            //Zde cache
            byte[] content = file.getByteOut();
           
            String mimeType = file.getMIMEType(); 
            out.write(HeadersAndPages.getHeader(header,length,mimeType).getBytes());
            out.write(content);
            closeSocket();
            
        } catch (IOException ex) {
            System.out.println("I/O Chyba");
            System.out.println(ex.getMessage());
        }        
    }

        
    private void closeSocket() throws IOException {
            out.close();
            s1.close();
    }
       
    /*Tato metoda by mohla byt obsazena v tride BasicAuthentication, je zde kvuli rychlosti nebo nemusime kvuli kazdemu overovani zakladat 
    novy objekt, objekt zakladame pouze v pripade potreby autorizace*/
    public static boolean authentication(String header){
    if (header.contains("Authorization: Basic")) {
        BasicAuthentication auth = new BasicAuthentication(header);
        return auth.validAuth();
    }
    return false;
    }    
}
