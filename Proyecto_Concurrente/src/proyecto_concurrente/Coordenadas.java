
package proyecto_concurrente;


public class Coordenadas {
    
    private double longitud;
    private double latitud;

    public Coordenadas(){
            //Llamada API para obtener coordenadas actuales
            this.longitud = 0;
            this.latitud = 0;
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
