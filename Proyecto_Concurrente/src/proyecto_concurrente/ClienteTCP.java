package proyecto_concurrente;


import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteTCP { 
    HashMap usuario = new HashMap();
    
    public String enviaServidor(String payload){
        String output = null;
        try {
            int serverPort = 5001;
            InetAddress host = InetAddress.getByName("localhost"); 
            System.out.println("Conectando al servidor en el puerto: " + serverPort); 
            Socket socket = new Socket(host,serverPort); 
//            socket = new Socket("127.0.0.1", serverPort);
            System.out.println("Conectado a " + socket.getRemoteSocketAddress()); 
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServer.println(payload);
            String line = fromServer.readLine();
            System.out.println("Cliente recibe: " + line + " del servidor \n");
            toServer.close();
            socket.close();
            output = line;
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    

}
