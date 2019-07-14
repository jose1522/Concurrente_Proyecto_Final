package proyecto_concurrente;

import java.util.ArrayList;

public class AppServidor {
    
    
    public static void main(String[] args) {
        ServidorTCP srv = new ServidorTCP();
        srv.run();
    }   
}
