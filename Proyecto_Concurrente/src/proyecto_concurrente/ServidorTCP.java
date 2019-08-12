
package proyecto_concurrente;
import com.google.gson.Gson;
import java.net.*;
import java.io.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorTCP {
    static ArrayList <Usuario> usuarios = new ArrayList();
    static ArrayList <ContactoEmergencia> contactos = new ArrayList<ContactoEmergencia>();
    static ArrayList <Eventos> eventos = new ArrayList<Eventos>();
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
        if (usuarios.size()>0){
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email)){
                    return false;
                }
            }
        }
        
        String nombre = jsonObject.get("nombre").getAsString();
        String apellidos = jsonObject.get("apellidos").getAsString();
        Contraseña contraseña = new Contraseña();
        contraseña.setContraseña(jsonObject.get("contraseña").getAsString());
        String genero = jsonObject.get("genero").getAsString();
        String cedula = jsonObject.get("cedula").getAsString();
        String celular = jsonObject.get("celular").getAsString();
        String id = uuid.toString();

        Usuario u = new Usuario(contraseña, cedula, genero, id, email, nombre, apellidos, celular);
        usuarios.add(u);
        System.out.println(u.toString());
        return true;
     
    }
    
    public boolean autenticarUsuario(String payload){
//        HashMap respuesta = new HashMap();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String inputEmail = jsonObject.get("email").getAsString();
        String inputContraseña = jsonObject.get("contraseña").getAsString(); 
//        respuesta.put("accion", accion);
        
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

    public boolean recuperarContraseña(String payload){
//        HashMap respuesta = new HashMap();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String inputEmail = jsonObject.get("email").getAsString();
//        respuesta.put("accion", accion);
        
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (aux.getEmail().equals(inputEmail)){
                    return true;
            }
        }
        return false;
    }

        public boolean cambiarContraseña(String payload){
//        HashMap respuesta = new HashMap();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String inputEmail = jsonObject.get("email").getAsString();
        String nuevaContrasena = jsonObject.get("nueva contrasena").getAsString();
        String contrasenaActual = jsonObject.get("contrasena actual").getAsString();
        boolean resultado = false;
//        respuesta.put("accion", accion);
        
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario aux = usuarios.get(i);
            if (aux.getEmail().equals(inputEmail)){
                resultado = aux.modificarContraseña(contrasenaActual, nuevaContrasena);
            }
        }
        return resultado;
    }
    
    public boolean crearContactoEmergencia(String payload){
//        HashMap respuesta = new HashMap();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String email = jsonObject.get("email").getAsString();
        String nombre = jsonObject.get("nombre").getAsString();
        String apellidos = jsonObject.get("apellidos").getAsString();
        String celular = jsonObject.get("celular").getAsString();
        String idUsuarioPrincipal = jsonObject.get("id").getAsString();
//        respuesta.put("accion", accion);
        String id = UUID.randomUUID().toString();
        
        for (int i = 0; i < contactos.size(); i++) {
            ContactoEmergencia aux = contactos.get(i);
            if (aux.getEmail().equals(email)){
                return false;
            }
        }
        
        ContactoEmergencia c = new ContactoEmergencia(idUsuarioPrincipal, id, email, nombre, apellidos, celular);
        contactos.add(c);
        return true;
    }
    
    public boolean modificarContactoEmergencia(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String email = jsonObject.get("email").getAsString();
        String nombre = jsonObject.get("nombre").getAsString();
        String apellidos = jsonObject.get("apellidos").getAsString();
        String celular = jsonObject.get("celular").getAsString();
        String id = jsonObject.get("id").getAsString(); //es el unique ID, NO EL EMAIL
        
        for (int i = 0; i < contactos.size(); i++) {
            ContactoEmergencia aux = contactos.get(i);
            if (aux.getId().equals(id)){
                aux.setEmail(email);
                aux.setNombre(nombre);
                aux.setApellidos(apellidos);
                aux.setCelular(celular);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean emergencia (String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String email = jsonObject.get("email").getAsString();
        ArrayList <Thread> threads = new ArrayList();
        
        for (int i = 0; i < contactos.size(); i++) {
            ContactoEmergencia aux = contactos.get(i);
            if (aux.getIdUsuarioPrincipal().equals(email)){
                Thread hilocontacto = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("Enviando mensaje a "+ aux.getEmail());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Mensaje enviado a "+ aux.getEmail());
                        //Método para enviar el mensaje
                    }
                };
                threads.add(hilocontacto);
            }
        }
        
        if (threads.size()>0){
            for (Thread thread : threads) {
                thread.start();
            }
        } else {
            return false;
        }
        return true;
    }    

    public boolean crearAlerta(String payload){
//        HashMap respuesta = new HashMap();
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        String accion = jsonObject.get("accion").getAsString();
        String email = jsonObject.get("email").getAsString();
        String nombre = jsonObject.get("tipoAlerta").getAsString();
//        respuesta.put("accion", accion);
        Date fecha = Calendar.getInstance().getTime();

        Eventos e = new Eventos(email, fecha, nombre);
        e.setLatitud(0);
        e.setLongitud(0);
        e.setBarrio("San Jose");
        eventos.add(e);
        return true;
    }

    public String misAlertas(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject(); //Objeto para deserializar el input que recibe el servidor
        String email = jsonObject.get("email").getAsString(); //Se extrae un objeto especifico
        String reporteString = "";
        int contador = 0;
        HashMap reporte = new HashMap();
        ArrayList <Eventos> items = new ArrayList<Eventos>(); //Lista auxiliar de los eventos especificos del usuario
        
        //Se agregan eventos a la lista. Se extrae un maximo de 25 eventos, empezando desde el mas reciente
        if(eventos.size()>0){
            for (int i = eventos.size()-1; i > -1; i--) {
                 if (contador <=25){
                    if (eventos.get(i).getIdUsuario().equals(email)){
                        items.add(eventos.get(i));
                        contador++;
                    }
                } else {
                    break;
                }               
            }
            reporte.put("eventos", gson.toJson(items));
            reporteString = gson.toJson(reporte);
            System.out.println(reporteString);
        }
        
        System.out.println("Reporte Generado exitosamente");
        return reporteString;
    }
    public String reporteAlertas(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject(); //Objeto para deserializar el input que recibe el servidor
        String barrio = jsonObject.get("barrio").getAsString(); //Se extrae un objeto especifico
        String tipoAlerta = jsonObject.get("tipoAlerta").getAsString(); //Se extrae un objeto especifico
        int dias = jsonObject.get("dias").getAsInt(); //Se extrae un objeto especifico
        Date hoy = Calendar.getInstance().getTime();
        String reporteString = "";
//        int contador = 0;
        HashMap reporte = new HashMap();
        ArrayList <Eventos> items = new ArrayList<Eventos>(); //Lista auxiliar de los eventos especificos del usuario
        
        if(eventos.size()>0){
            for (int i = eventos.size()-1; i > -1; i--) {
                Date fecha = eventos.get(i).getFecha();
                int deltaDias = fecha.compareTo(hoy);
                 if (deltaDias <= dias){
                    if (eventos.get(i).getBarrio().equals(barrio)&eventos.get(i).getTipoAlerta().equals(tipoAlerta)){
                        items.add(eventos.get(i));
//                        contador++;                       
                    }
                } else {
                    break;
                }               
            }
            reporte.put("eventos", gson.toJson(items));
            reporteString = gson.toJson(reporte);
            System.out.println(reporteString);
        }
        System.out.println("Reporte Generado exitosamente");
        return reporteString;
    }
    
   public String misDatos(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject(); //Objeto para deserializar el input que recibe el servidor
        String email = jsonObject.get("email").getAsString(); //Se extrae un objeto especifico
        String reporteString = "";
        HashMap reporte = new HashMap();
        
        //Se agregan eventos a la lista. Se extrae un maximo de 25 eventos, empezando desde el mas reciente
        if(usuarios.size()>0){
            for (int i = 0; i > usuarios.size(); i++) {
                 if (usuarios.get(i).getEmail().equals(email)){
                    reporte.put("nombre", usuarios.get(i).getNombre());
                    reporte.put("apellidos", usuarios.get(i).getApellidos());
                    reporte.put("telefono", usuarios.get(i).getCelular());
                    reporte.put("genero", usuarios.get(i).getGenero());
                    break;
                }           
            }
            reporteString = gson.toJson(reporte);
            System.out.println(reporteString);
        }
        System.out.println("Reporte Generado exitosamente");
        return reporteString;
    }
   
   public boolean modificarMisDatos(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject(); //Objeto para deserializar el input que recibe el servidor
        String email = jsonObject.get("email").getAsString(); //Se extrae un objeto especifico
        String nombre = jsonObject.get("nombre").getAsString(); //Se extrae un objeto especifico
        String apellidos = jsonObject.get("apellidos").getAsString(); //Se extrae un objeto especifico
        String telefono = jsonObject.get("telefono").getAsString(); //Se extrae un objeto especifico
        String genero = jsonObject.get("genero").getAsString(); //Se extrae un objeto especifico
        boolean output = false;
        
        //Se agregan eventos a la lista. Se extrae un maximo de 25 eventos, empezando desde el mas reciente
        if(usuarios.size()>0){
            for (int i = 0; i > usuarios.size(); i++) {
                 if (usuarios.get(i).getEmail().equals(email)){
                    usuarios.get(i).setNombre(nombre);
                    usuarios.get(i).setApellidos(apellidos);
                    usuarios.get(i).setCelular(telefono);
                    usuarios.get(i).setGenero(genero);
                    output = true;
                    break;
                }              
            }

        }
        System.out.println("Reporte Generado exitosamente");
        return output;
    }
 
    public String misContactos(String payload){
        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject(); //Objeto para deserializar el input que recibe el servidor
        String email = jsonObject.get("email").getAsString(); //Se extrae un objeto especifico
        String reporteString = "";
        HashMap reporte = new HashMap();
        ArrayList <ContactoEmergencia> items = new ArrayList<>(); //Lista auxiliar de los eventos especificos del usuario
        
        //Se agregan eventos a la lista. Se extrae un maximo de 25 eventos, empezando desde el mas reciente
        if(contactos.size()>0){
            for (int i = 0; i > contactos.size(); i++) {
                 if (contactos.get(i).getIdUsuarioPrincipal().equals(email)){
                    items.add(contactos.get(i));
                }         
            }
            reporte.put("contactos", gson.toJson(items));
            reporteString = gson.toJson(reporte);
            System.out.println(reporteString);
        }
        return reporteString;
    }
        
    public String comandoJson(String payload){
        HashMap respuesta = new HashMap();
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
                if (this.crearContactoEmergencia(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }               
                break;

            case "modificar contacto de emergencia":
                if (this.crearContactoEmergencia(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }               
                break;
                
            case "autenticar usuario":
                if (this.autenticarUsuario(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;

             case "modificar mis datos":
                if (this.modificarMisDatos(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;               
            case "emergencia":
                if (this.emergencia(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;
                
            case "recuperar contraseña":
                if (this.recuperarContraseña(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;
                
            case "cambiar contraseña":
                if (this.recuperarContraseña(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;
                
            case "crear alerta":
                if (this.crearAlerta(payload)){
                    respuesta.put("exito", true);
                } else {
                    respuesta.put("exito", false);
                }
                break;
                
            case "mis alertas":
                respuesta.put("reporte",this.misAlertas(payload));
                respuesta.put("exito", true);
                break;
                
            case "reporte alertas":
                respuesta.put("reporte",this.reporteAlertas(payload));
                respuesta.put("exito", true);
                break;
                
            case "mis datos":
                respuesta.put("reporte",this.misDatos(payload));
                respuesta.put("exito", true);
                break;
                
            default:
                System.out.printf("Error: Accion %s no encontrada.\n", accion);
        }
        
        return gson.toJson(respuesta);
    }
    
    
}

  
    
    

