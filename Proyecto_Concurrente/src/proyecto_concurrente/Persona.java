
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

      
    public boolean verificarContraseña(String input){
        boolean output = false;
        return output;
    }
    
    public void guardarContraseña(String input){
        
    }
    
    public void nuevoContactoEmergencia(ContactoEmergencia input){
        
    }
    
    public void nuevoEvento(Eventos input){
        
    }
    
    public Eventos [] extraerEvento(){
        return null;
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


    
}
