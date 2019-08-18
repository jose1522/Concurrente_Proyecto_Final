/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author jose9
 */
public class ClienteAPI {
    
    public static void main(String[] args) {
        crearMapa();
    }
    public static String crearMapa () {                                      
        String payload = "";
        try {
            String markers = "";
            ArrayList <Eventos> eventos = new ArrayList();
            /*eventos.add(new Eventos("test", null, "test"));
            eventos.add(new Eventos("test", null, "Agresion"));
            eventos.add(new Eventos("test", null, "Asalto"));
            eventos.add(new Eventos("test", null, "Hurto"));
            eventos.add(new Eventos("test", null, "Vandalismo"));*/
            
            JsonArray aux= extraerAlertas();
            JsonObject jsonObject;
        //Se itera en cada item del array para agregar una linea nueva a la tabla
        if (aux!=null){
            for (int i = 0; i < aux.size(); i++) {
                jsonObject = aux.get(i).getAsJsonObject();
                //Se extraen elementos especificos y se agregan a un array string
                String fecha = jsonObject.get("fecha").getAsString();
                String tipo = jsonObject.get("tipoAlerta").getAsString();
                String lugar = jsonObject.get("barrio").getAsString();
                eventos.add(new Eventos("", Date.from(Instant.EPOCH), tipo));
            }
        }
            
            System.out.println(eventos.toString());
            for (Eventos evento : eventos) {
                System.out.println("Eventos: "+evento.getTipoAlerta());
                markers+="&markers=";
                switch (evento.getTipoAlerta()){
                    case "Agresion":
                        markers+="color:green%7Clabel:A%7C";
                        break;   
                    case "Asalto":
                        markers+="color:red%7Clabel:A%7C";
                        break;   
                    case "Hurto":
                        markers+="color:yellow%7Clabel:H%7C";
                        break;   
                    case "Vandalismo":
                        markers+="color:orange%7Clabel:V%7C";
                        break;  
                    default:
                        markers+="color:blue%7Clabel:O%7C";
                        break; 
                }
                markers+=evento.getLongitud()+"%2C+"+evento.getLatitud();
            }
            String startURL = "http://maps.googleapis.com/maps/api/staticmap?center=9.9360505%2C+-84.0790755&zoom=16&size=340x530&scale=1&format=png&maptype=terrain";
            String endURL = "&region=es&language=es&sensor=false&key=AIzaSyAWZLD2A6ltEb4HMzzRgNbOEgLJ1Sm89KI=";
            String msg = startURL+markers+endURL;
            URL url = new URL(msg);
            BufferedImage image = ImageIO.read(url);
            System.out.println(msg);
        } catch (IOException e) {
         
        } 
        return payload;
    }   
    
    private static JsonArray extraerAlertas(){
        Gson gson = new Gson(); //Objeto gson para serializar String a formato Json
        ClienteTCP client = new ClienteTCP(); //Cliente TCP para enviar datos
        HashMap reporte = new HashMap(); //Hashmap para enviar parametros al servidor
        JsonArray output = null; //Crea objeto nuevo de tipo JsonArray para poder acceder a cada evento individualmente
        
        //Agrega parametros
        reporte.put("accion","mis alertas");  
        reporte.put("email", "usuario@correo.com");
        
        String payload = gson.toJson(reporte);//Serializa parametros en formato JSON
        String resultado = client.enviaServidor(payload); //Agrega el mensaje devuelto por el servidor a un string

        //Se deserializa el string para extraer los eventos
        JsonObject jsonObject = new JsonParser().parse(resultado).getAsJsonObject();
        String stringTemporal = jsonObject.get("reporte").getAsString();
//        System.out.println(prueba);

        if (!stringTemporal.equals("")){
        jsonObject = new JsonParser().parse(stringTemporal).getAsJsonObject();
        stringTemporal = jsonObject.get("eventos").getAsString();
    //        System.out.println(prueba);
        output = new JsonParser().parse(stringTemporal).getAsJsonArray();
    //        System.out.println(aux.toString());        
        }
        return output;
    }
    
}
