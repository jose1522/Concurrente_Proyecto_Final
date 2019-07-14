
package proyecto_concurrente;
import com.google.gson.Gson;
import java.net.*;
import java.io.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

public class ServidorTCP {
    static ArrayList <Usuario> usuarios = new ArrayList();
    Gson gson = new Gson();
    
    public void run() {
          try {
                
                int serverPort = 5001;
                ServerSocket serverSocket = new ServerSocket(serverPort);
                
         
//		serverSocket.setSoTimeout(10000); 
                while(true) {
                    
                    System.out.println("Esperando cliente en el puerto " + serverSocket.getLocalPort() + "..."); 
                    Socket server = serverSocket.accept();
                    System.out.println("Conectado a " + server.getRemoteSocketAddress()); 
                    PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    String line = fromClient.readLine();
                    JsonObject jsonObject =  (JsonObject) new JsonParser().parse(this.comandoJson(line));
                    System.out.println("Servidor recibio: " + jsonObject.get("accion").getAsString() + ". Exito: " + jsonObject.get("exito").getAsString()  +"\n"); 
                    toClient.println(jsonObject); 
                }
          }
          catch(UnknownHostException ex) {
                  ex.printStackTrace();
          }
          catch(IOException e){
                  e.printStackTrace();
          }
    }

    public boolean crearUsuario(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        UUID uuid = UUID.randomUUID();
        
        String email = jsonObject.get("email").getAsString();
        boolean usuarioDuplicado = false;
        
        if (usuarios.size()>0){
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email)){
                    usuarioDuplicado=true;
                    break;
                }
            }
        }
        
        if (!usuarioDuplicado){
            String nombre = jsonObject.get("nombre").getAsString();
            String apellidos = jsonObject.get("apellidos").getAsString();
            Contraseña contraseña = new Contraseña();
            contraseña.setContraseña(jsonObject.get("contraseña").getAsString());
            String genero = jsonObject.get("genero").getAsString();
            String fechaNacimiento = jsonObject.get("fecha de nacimiento").getAsString();
            String celular = jsonObject.get("celular").getAsString();
            String id = uuid.toString();

            Usuario u = new Usuario(contraseña, fechaNacimiento, genero, id, email, nombre, apellidos, celular);
            usuarios.add(u);
            System.out.println(u.toString());
            return true;
        } else {
            return false;
        }
      
    }
    
    public boolean autenticarUsiario(String payload){
        Dictionary respuesta = new Hashtable();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String inputEmail = jsonObject.get("email").getAsString();
        String inputContraseña = jsonObject.get("contraseña").getAsString(); 
        respuesta.put("accion", accion);
        
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (aux.getEmail().equals(inputEmail)){
                if (aux.verificarContraseña(inputContraseña)){
                    return true;
                }
            }
        }
        return false;
    }

    public String comandoJson(String payload){
        Dictionary respuesta = new Hashtable();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();  
        respuesta.put("accion", accion);

        switch (accion){
            case "crear usuario":
                if (this.crearUsuario(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }              
                break;
            case "crear contacto de emergencia":
                
                break;
            case "autenticar usuario":
                if (this.autenticarUsiario(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;
            case "emergencia":
                break;
            default:
                System.out.printf("Error: Accion %s no encontrada.\n", accion);
        }
        
        return gson.toJson(respuesta);
    }
    
    
}

  
    
    

