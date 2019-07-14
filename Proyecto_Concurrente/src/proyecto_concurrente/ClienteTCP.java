package proyecto_concurrente;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteTCP { 
    Dictionary usuario = new Hashtable();
    
    public void enviaServidor(String payload){
        try {
            int serverPort = 5001;
            InetAddress host = InetAddress.getByName("localhost"); 
            System.out.println("Conectando al servidor en el puerto: " + serverPort); 
            Socket socket = new Socket(host,serverPort); 
//            socket = new Socket("127.0.0.1", serverPort);
            System.out.println("Conectado a " + socket.getRemoteSocketAddress()); 
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            toServer.println(   mensaje+"%>%"+socket.getLocalSocketAddress());
            toServer.println(payload);
            String line = fromServer.readLine();
            System.out.println("Cliente recibe: " + line + " del servidor");
            toServer.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
