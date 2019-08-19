
package proyecto_concurrente;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Coordenadas {
    
    private String longitud;
    private String latitud;
    private String barrio;

    public Coordenadas(){
        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
            //Llamada API para obtener coordenadas actuales
            this.longitud = new String(df.format(Util.numeroAleatorio(9.931258,9.940981)));
            this.latitud = new String(df.format(Util.numeroAleatorio(-84.075752,-84.081359)));
            this.barrio = "San Jose";
    }

    public Coordenadas(String longitud, String latitud, String barrio) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.barrio = barrio;
    }

    
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Coordenadas{" + "longitud=" + longitud + ", latitud=" + latitud + '}';
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
   
}
