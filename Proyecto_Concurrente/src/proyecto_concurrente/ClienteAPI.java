/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static proyecto_concurrente.ServidorTCP.eventos;

/**
 *
 * @author jose9
 */
public class ClienteAPI {
    public String crearMapa () {                                      
        String payload = "";
        try {
            String markers = "";
            for (Eventos evento : eventos) {
                markers+="&markers=";
                switch (evento.getTipoAlerta()){
                    case "Agresion":
                        markers+="color:red%7Clabel:A%";
                        break;   
                    case "Asalto":
                        markers+="color:red%7Clabel:A%";
                        break;   
                    case "Hurto":
                        markers+="color:orange%7Clabel:B%";
                        break;   
                    case "Vandalismo":
                        markers+="color:orange%7Clabel:B%";
                        break;  
                    default:
                        markers+="color:blue%7Clabel:C%";
                        break; 
                }
                markers+=evento.getLatitud()+"%2C+"+evento.getLongitud();
            }
            String startURL = "http://maps.googleapis.com/maps/api/staticmap?center=9.9360505%2C+-84.0790755&zoom=16&size=300x650&scale=1&format=png&maptype=terrain";
            String endURL = "&region=es&language=es&sensor=false&key=AIzaSyAWZLD2A6ltEb4HMzzRgNbOEgLJ1Sm89KI=";
            String msg = startURL+markers+endURL;
            URL url = new URL(msg);
            BufferedImage image = ImageIO.read(url);
            System.out.println(image);
        } catch (IOException e) {
         
        } 
        return payload;
    }           
    
}
