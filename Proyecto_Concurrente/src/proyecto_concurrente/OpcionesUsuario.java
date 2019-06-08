
package proyecto_concurrente;

public class OpcionesUsuario {
    private String idioma;
    private String medioComunicacionPreferido;
    private boolean alertasActivas;

    public OpcionesUsuario() {
    }

    public OpcionesUsuario(String idioma, String medioComunicacionPreferido, boolean alertasActivas) {
        this.idioma = idioma;
        this.medioComunicacionPreferido = medioComunicacionPreferido;
        this.alertasActivas = alertasActivas;
    }

    
    public String getMedioComunicacionPreferido() {
        return medioComunicacionPreferido;
    }

    public void setMedioComunicacionPreferido(String medioComunicacionPreferido) {
        this.medioComunicacionPreferido = medioComunicacionPreferido;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isAlertasActivas() {
        return alertasActivas;
    }

    public void setAlertasActivas(boolean alertasActivas) {
        this.alertasActivas = alertasActivas;
    }
    
    
    
}
