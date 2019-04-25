package sdp.shop.now.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encrypter {
    
    public static String encrypt(String password){
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
            password.getBytes(StandardCharsets.UTF_8));
            return byteToHex(encodedHash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String byteToHex(byte[] encodedHash) {
        StringBuilder hexString = new StringBuilder();
        for(int i = 0; i < encodedHash.length; i++){
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if(hex.length() == 1) 
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}