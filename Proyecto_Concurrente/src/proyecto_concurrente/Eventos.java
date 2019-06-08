
package proyecto_concurrente;

import java.util.Date;


public class Eventos {
    private String idUsuario;
    private Date fecha;
    private String tipoAlerta;
    private double longitud;
    private double latitud;

    public Eventos() {
        this.idUsuario = "";
        this.fecha = null;
        this.tipoAlerta = "";
        this.longitud = 0.0;
        this.latitud = 0.0;
    }

    public Eventos(String idUsuario, Date fecha, String tipoAlerta, double longitud, double latitud) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.tipoAlerta = tipoAlerta;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
