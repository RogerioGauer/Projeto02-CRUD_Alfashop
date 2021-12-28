package br.com.alfashop.extra;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Rogerio
 */
public class Util {
    
    //Método para encriptografar senha.
    public static String sha1(String txt){
        String varsha1;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(txt.getBytes("utf8"));
            varsha1 = String.format("%040x", new BigInteger(1, digest.digest())); 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            varsha1 = "";            
        }
        return varsha1;
    }   
}
