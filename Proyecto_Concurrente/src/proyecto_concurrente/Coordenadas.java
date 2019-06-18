
package proyecto_concurrente;


public class Coordenadas {
    
    private double longitud;
    private double latitud;
    private String barrio;

    public Coordenadas(){
            //Llamada API para obtener coordenadas actuales
            this.longitud = 0;
            this.latitud = 0;
            this.barrio = "";
    }

    public Coordenadas(double longitud, double latitud, String barrio) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.barrio = barrio;
    }

    
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
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
