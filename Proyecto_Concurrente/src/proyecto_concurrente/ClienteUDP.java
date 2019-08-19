/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author jose9
 */
public class ClienteUDP {
    
    public String enviaServidor() {                                      
        String output = "";
        try {
            String msg = "Conexion UDP";
            byte[] b = new byte[1024];
            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 5005); // cambiamos
            DatagramSocket s = new DatagramSocket();

            System.out.println("Mensaje enviado a: Servidor" + p.getAddress().getHostName() + " Puerto= " + p.getPort());
            s.send(p);
            p = new DatagramPacket(b, b.length);
            s.receive(p);
            String url = new String(b, 0, b.length);
            System.out.println("Respuesta desde:" + p.getAddress().getHostName() + " Mensaje: " + url);
            output = url;
            s.close();
        } catch (SocketException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
         
        } 
        return output;
    }             
}
