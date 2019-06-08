
package proyecto_concurrente;


public class Coordenadas {
    
    private double longitud;
    private double latitud;

    public Coordenadas(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
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
   
}
