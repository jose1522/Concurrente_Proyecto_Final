
package proyecto_concurrente;


public class Persona {

    private String id;
    private String email;
    private String nombre;
    private String apellidos;
    private String celular;

    public Persona(){
        
    }

    public Persona(String id, String email, String nombre, String apellidos, String celular) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.celular = celular;
    }

      
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Persona{" + "\n\tid ->\t" + id + ",\n\temail ->\t" + email + ",\n\tnombre ->\t" + nombre + ",\n\tapellidos ->\t" + apellidos + ",\n\tcelular ->\t" + celular;
    }

    
    
}
