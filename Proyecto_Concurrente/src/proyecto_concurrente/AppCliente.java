
package proyecto_concurrente;

import com.google.gson.Gson;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppCliente {
    public static void main(String[] args) {
        //crea usuario
        Gson gson = new Gson();
        ClienteTCP client = new ClienteTCP();
        Dictionary usuario = new Hashtable();
        
        usuario.put("accion","crear usuario");
        usuario.put("email","usuario@correo.com");
        usuario.put("contraseña","fgh08h21");
        usuario.put("genero","Mujer");
        usuario.put("nombre","Jane");
        usuario.put("apellidos","Doe");
        usuario.put("celular","88888888");
        usuario.put("fecha de nacimiento","1/1/1998");
        
        String payload = gson.toJson(usuario);
        client.enviaServidor(payload);
        
        //espera 1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //verifica credenciales de usuario
        usuario = new Hashtable();
        usuario.put("email","usuario@correo.com");
        usuario.put("contraseña","fgh08h21");
        usuario.put("accion","autenticar usuario");   
        payload = gson.toJson(usuario);
        client.enviaServidor(payload);
    }    
}
