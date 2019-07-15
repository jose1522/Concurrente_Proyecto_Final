
package proyecto_concurrente;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;



public class AppCliente {
    static ArrayList <Usuario> usuarios = new ArrayList<Usuario>();
    static ArrayList <ContactoEmergencia> contactos = new ArrayList<ContactoEmergencia>();
    static ArrayList <Eventos> eventos = new ArrayList<Eventos>();
       
    public static void main(String[] args) {
        //crea usuario
        Gson gson = new Gson();
        ClienteTCP client = new ClienteTCP();
        HashMap usuario = new HashMap();
        
        usuario.put("accion","crear usuario");
        usuario.put("email","usuario@correo.com");
        usuario.put("contraseña","fgh08h21");
        usuario.put("genero","Mujer");
        usuario.put("nombre","Jane");
        usuario.put("apellidos","Doe");
        usuario.put("celular","88888888");
        usuario.put("cedula","1111111111");
        
        String payload = gson.toJson(usuario);
        client.enviaServidor(payload);
        
//        //espera 1s
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AppCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        //verifica credenciales de usuario
//        usuario = new HashMap();
//        usuario.put("email","usuario@correo.com");
//        usuario.put("contraseña","fgh08h21");
//        usuario.put("accion","autenticar usuario");   
//        payload = gson.toJson(usuario);
//        client.enviaServidor(payload);
        usuario = new HashMap();
        usuario.put("accion","crear contacto de emergencia");
        usuario.put("id","usuario@correo.com");
        usuario.put("email","contacto1@correo.com");
        usuario.put("nombre","Jane");
        usuario.put("apellidos","Doe");
        usuario.put("celular","88888888");
        
        payload = gson.toJson(usuario);
        client.enviaServidor(payload);
        
        usuario = new HashMap();
        usuario.put("accion","crear contacto de emergencia");
        usuario.put("id","usuario@correo.com");
        usuario.put("email","contacto2@correo.com");
        usuario.put("nombre","Jane");
        usuario.put("apellidos","Doe");
        usuario.put("celular","88888888");
        
        payload = gson.toJson(usuario);
        client.enviaServidor(payload);

    }

    
}
