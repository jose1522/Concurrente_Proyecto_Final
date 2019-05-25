/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose9
 */
public class Util {
    public static String hashString (String input){
        byte [] arr = null;
        String output = "";
        try {
            final MessageDigest hash = MessageDigest.getInstance("SHA-512"); //Instance MessageDigest para hacer el hash
            arr = hash.digest(input.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Contraseña.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (arr != null){
            for (int i = 0; i < arr.length; i++) {
              output += Integer.toHexString(arr[i]); 
            }
        }
        return output;
    }
}
