/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

/**
 *
 * @author Andrej
 */
public class File {
    private String path;
    private FileInputStream filein;
    private int length;

    
    public File(String path) throws FileNotFoundException, IOException{
        this.path=path;
        this.filein = new FileInputStream(path);
        this.length = filein.available();
    }
    
    public int getLength() throws IOException{
        return length;
    }
    
    public String getMIMEType(){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            return fileNameMap.getContentTypeFor(path);
    }
    
    public byte[] getByteOut() throws IOException{
            byte[] content = Cache.getStaticCache().get(path);
            if(content == null){
                content = new byte[length];
                filein.read(content);
                if (length >= 1000000) {
                        Cache.getStaticCache().addByteObj(path, content);
                    }
            }
            return content;
    }    
}
