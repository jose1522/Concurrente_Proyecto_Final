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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jose9
 */
public class ClienteAPI {
    
    public void inciarUDP() {

        System.out.println("Iniciando Servidor");
        byte[] b = new byte[1024];

        try {
            DatagramSocket serverSocket = new DatagramSocket(5005);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String(receivePacket.getData());
                  System.out.println("UDP recibe en puerto 5005: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String url = crearURL();
                  sendData = url.getBytes();
                   System.out.println("Servidor UDP envia: " +url);
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
//            System.out.println("Conexión cerrada");
        } catch (SocketException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }

    }    
    private String crearURL () {                                      
        String payload = "";
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
                String latitud = jsonObject.get("latitud").getAsString();
                String longitud = jsonObject.get("longitud").getAsString();
                Eventos e = new Eventos("", Date.from(Instant.EPOCH), tipo);
                e.setLatitud(latitud);
                e.setLongitud(longitud);
                eventos.add(e);
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
        payload = msg; 
        return payload;
    }   
    
    private JsonArray extraerAlertas(){
        Gson gson = new Gson(); //Objeto gson para serializar String a formato Json
        ClienteTCP client = new ClienteTCP(); //Cliente TCP para enviar datos
        HashMap reporte = new HashMap(); //Hashmap para enviar parametros al servidor
        JsonArray output = null; //Crea objeto nuevo de tipo JsonArray para poder acceder a cada evento individualmente
        
        //Agrega parametros
        reporte.put("accion","reporte alertas");  
//        reporte.put("email", "usuario@correo.com");
        
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
