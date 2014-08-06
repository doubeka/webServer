/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Andrej
 */
public class HeadersAndPages {
    private static final String fheader200 = "HTTP/1.0 200 OK\r\n";
    private static final String fheader400 = "HTTP/1.0 400 Bad request\r\n";
    private static final String fheader401 = "HTTP/1.0 401 Authorization Required\r\n";
    private static final String fheader404 = "HTTP/1.0 404 Not found\r\n";
    private static final String serverName = Server.getServerName();
    
    public static String getHeader(String header,int length,String mimeType){
            String outH = "";
            String first = "HTTP/1.0 200 OK\r\n";
            String contentType = "Content-type: " +  mimeType + "\r\n";
            String contentLength = "Content-Length: " + length + "\r\n";
            StringBuilder headerBuild= new StringBuilder();
            headerBuild.append(first);
            headerBuild.append(contentType);
            headerBuild.append(contentLength);
            headerBuild.append("\r\n");
            outH = headerBuild.toString();
            return outH;        
    }
    
    public static String get400Page(){
            String outH = "";
            String body = "<html><head><title>400 Bad Request</title></head><body><p><center><H1>400 Bad request</H1></center></p></body></html>";
            int length = body.getBytes().length;
            String first = "HTTP/1.0 400 Bad request\r\n";
            String contentType = "Content-type:text/html\r\n";
            String contentLength = "Content-Length: " + length + "\r\n";
            StringBuilder headerBuild= new StringBuilder();
            headerBuild.append(first);
            headerBuild.append(contentType);
            headerBuild.append(contentLength);
            headerBuild.append("\r\n");
            headerBuild.append(body);
            outH = headerBuild.toString();
            return outH;
    }
    
    public static String get404Page(){
            String outH = "";
            String body = "<html><head><title>404 Not found</title></head><body><p><center><H1>404 Not found</H1></center></p></body></html>";
            int length = body.getBytes().length;
            String first = "HTTP/1.0 404 Not found\r\n";
            String contentType = "Content-type:text/html\r\n";
            String contentLength = "Content-Length: " + length + "\r\n";
            StringBuilder headerBuild= new StringBuilder();
            headerBuild.append(first);
            headerBuild.append(contentType);
            headerBuild.append(contentLength);
            headerBuild.append("\r\n");
            headerBuild.append(body);
            outH = headerBuild.toString();
            return outH;
    }
    
    public static String get401Page(){
            DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
            Date date = new Date();
            String actualTime = dateFormat.format(date);
            String outH = "";
            String body = "<html><head><title>401 Authorization Required</title></head><body><p><center><H1>401 Authorization Required</H1></center></p></body></html>";
            int length = body.getBytes().length;
            String first = "HTTP/1.0 401 Authorization Required\r\n";
            String contentType = "Content-type:text/html\r\n";
            String contentLength = "Content-Length: " + length + "\r\n";
            StringBuilder headerBuild= new StringBuilder();
            headerBuild.append(first);
            headerBuild.append("Date: "+actualTime +"GMT\r\nWWW-Authenticate: Basic realm=\"Secure Area\"\r\n");
            headerBuild.append(contentType);
            headerBuild.append(contentLength);
            headerBuild.append("\r\n");
            headerBuild.append(body);
            outH = headerBuild.toString();
            return outH;
    }
    
}
