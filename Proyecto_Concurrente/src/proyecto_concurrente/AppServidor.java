package proyecto_concurrente;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static proyecto_concurrente.ServidorTCP.eventos;

public class AppServidor {
    
    
    public static void main(String[] args) {
        ServidorTCP srv = new ServidorTCP();
        ClienteAPI api = new ClienteAPI();
        Thread hilo = new Thread() {
            @Override
            public void run() {
                srv.iniciarServidor();
            }
        };
//        srv.run();
        hilo.start();
        api.crearMapa();
        
        
    }   
}
