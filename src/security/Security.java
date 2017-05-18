/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;


import common.AppLogger;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
/**
 *
 * @author acer
 */
public class Security {
    
    /**
     * hash password
     * @param passwordToHash
     * @param salt
     * @return 
     */
    public static String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(salt.getBytes("UTF-8"));
             byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
             StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword = sb.toString();
            } 
           catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
                AppLogger.getLogger(Security.class.getName()).log(Level.SEVERE, "A serious Exception has occurred", e);
           }
        return generatedPassword;
    }
    
    /**
     * generate a random salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
    
    
}
