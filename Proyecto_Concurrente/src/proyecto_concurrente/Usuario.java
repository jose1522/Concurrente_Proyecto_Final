
package proyecto_concurrente;


public class Usuario {


    private String idUsuario;
    private Contraseña contraseña;
    private String email;
    private String nombre;
    private String apellidos;
    private String celular;
    private String fechaNacimiento;
    private String genero;
    private ContactosEmergencia [] contactosEmergencia;
    private Eventos [] eventos;
    OpcionesUsuario opcionesUsuario;
    
    public Usuario() {
        this.idUsuario = "";
        this.contraseña = null;
        this.email = "";
        this.nombre = "";
        this.apellidos = "";
        this.celular = "";
        this.fechaNacimiento = "";
        this.genero = "";
        this.contactosEmergencia = new ContactosEmergencia[1];
        this.eventos = new Eventos[1];
        this.opcionesUsuario = new OpcionesUsuario();
        
    }
    
    
    public Contraseña getContraseña() {
        return contraseña;
    }

    public void setContraseña(Contraseña contraseña) {
        this.contraseña = contraseña;
    }

    public OpcionesUsuario getOpcionesUsuario() {
        return opcionesUsuario;
    }

    public void setOpcionesUsuario(OpcionesUsuario opcionesUsuario) {
        this.opcionesUsuario = opcionesUsuario;
    }
    
    
    public boolean verificarContraseña(String input){
        boolean output = false;
        return output;
    }
    
    public void guardarContraseña(String input){
        
    }
    
    public void nuevoContactoEmergencia(ContactosEmergencia input){
        
    }
    
    public void nuevoEvento(Eventos input){
        
    }
    
    public Eventos [] extraerEvento(){
        
    }
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ContactosEmergencia[] getContactosEmergencia() {
        return contactosEmergencia;
    }

    public void setContactosEmergencia(ContactosEmergencia[] contactosEmergencia) {
        this.contactosEmergencia = contactosEmergencia;
    }

    public Eventos[] getEventos() {
        return eventos;
    }

    public void setEventos(Eventos[] eventos) {
        this.eventos = eventos;
    }
    
    
    
}
