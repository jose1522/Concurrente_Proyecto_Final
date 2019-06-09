
package proyecto_concurrente;

import java.util.Date;


public class Eventos extends Coordenadas {
    private String idUsuario;
    private Date fecha;
    private String tipoAlerta;


    public Eventos() {
        super();
        this.idUsuario = "";
        this.fecha = null;
        this.tipoAlerta = "";
    }

    public Eventos(String idUsuario, Date fecha, String tipoAlerta) {
        super();
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.tipoAlerta = tipoAlerta;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
