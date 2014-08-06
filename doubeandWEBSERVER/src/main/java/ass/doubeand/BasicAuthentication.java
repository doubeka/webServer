/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.doubeand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Andrej
 */
public class BasicAuthentication {
    private String name = "";
    private String pass = "";
    private final String defName = Server.getUser();
    private final String defPass = Server.getPassword();
    private String authLine = "";
    private String header = "";
    BufferedReader reader;
    
    
    /**
    * Konstruktor @param header
    */
    public BasicAuthentication(String header) {
        this.header = header;
    }
    
    /**
    * Metoda pro zjisteni spravnosti zadanych udaju
    */
    public boolean validAuth(){
        decode();
        return ((name.equals(defName) && pass.equals(defPass)))?true:false;     
    }
    
    
    /**
    * Metoda pro dekodovani udaju z BASE64 do String
    */
    private void decode() {
        try {
            reader = new BufferedReader(new StringReader(header));
            while (reader.ready()) {
                String s = reader.readLine();
                if (s.contains("Authorization: Basic")) {
                    StringTokenizer st = new StringTokenizer(s);
                    st.nextToken();
                    st.nextToken();
                    authLine = st.nextToken();
                    break;
                }
            }
            
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decode = null;

            try {
                decode = decoder.decodeBuffer(authLine);
            } catch (IOException ex) {
                System.out.println("Decode err");
            }

            String[] keys = new String(decode).split(":");
            if (keys.length != 2) {
                return;
            }
            name = keys[0]; pass = keys[1];
        } catch (IOException ex) {
            System.out.println("I/O exception");
        }
    }
}
    