
package proyecto_concurrente;

public class ContactosEmergencia {


    private String nombre;
    private String telefono;
    private String email;
    
    public ContactosEmergencia() {
        this.nombre = "";
        this.telefono = "";
        this.email = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
