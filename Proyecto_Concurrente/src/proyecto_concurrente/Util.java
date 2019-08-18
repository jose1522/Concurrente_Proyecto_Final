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
    
    public static double numeroAleatorio(double Max, double Min){
        /*
        Devuelve un numero aleatorio del 1 a max
        */
        double aux = (Math.random() * (Max-Min) + Min); 
        
        return aux;        
    }
}
