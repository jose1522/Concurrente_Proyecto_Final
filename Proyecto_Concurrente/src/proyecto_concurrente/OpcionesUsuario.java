
package proyecto_concurrente;

public class OpcionesUsuario {
    private String idioma;
    private String medioComunicacionPreferido;

    public OpcionesUsuario() {
    }

    
    public OpcionesUsuario(String idioma, String medioComunicacionPreferido) {
        this.idioma = idioma;
        this.medioComunicacionPreferido = medioComunicacionPreferido;
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
    
    
    
}
