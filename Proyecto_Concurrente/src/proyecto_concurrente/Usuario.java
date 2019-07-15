
package proyecto_concurrente;

import java.util.Date;


public class Usuario extends Persona{
    private Contraseña contraseña;
    OpcionesUsuario opcionesUsuario;
    private String cedula;
    private String genero;

    public Usuario(Contraseña contraseña,  String cedula, String genero, String id, String email, String nombre, String apellidos, String celular) {
        super(id, email, nombre, apellidos, celular);
        this.contraseña = contraseña;
        this.opcionesUsuario = new OpcionesUsuario();
        this.cedula = cedula;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return super.toString()+"\n\tcontrase\u00f1a ->\t" + contraseña + ", \n\topcionesUsuario ->\t" + opcionesUsuario + ", \n\tfechaNacimiento ->\t" + cedula + ", \n\tgenero ->\t" + genero + "\n\t"+'}';
    }
    
    
    public boolean verificarContraseña(String input){
        boolean output = false;
        input = Util.hashString(input);
        if (contraseña == null||input.equals(contraseña.getContraseña())){
            output = true;
        }
        return output;
    }
    
    public void modificarContraseña(String input, String nuevaContraseña){
        boolean seguridadVerificada = this.verificarContraseña(input);
        boolean verificacionNuevaContraseña = contraseña.validarSeguridad(nuevaContraseña);
        verificacionNuevaContraseña = verificacionNuevaContraseña & contraseña.validarTamaño(input);
        
        if(seguridadVerificada&&verificacionNuevaContraseña){
            contraseña.setContraseña(nuevaContraseña);
        }
    }
    
    public void nuevoContactoEmergencia(ContactoEmergencia input){
        System.out.println("Crea un nuevo contacto de emergencia y lo envia al servidor por medio de una llamada API");
    }
    
    public void nuevoEvento(Eventos input){
        System.out.println("Crea un nuevo evento y lo envia al servidor por medio de una llamada API");
    }
    
    public Eventos [] extraerEvento(){
        System.out.println("Hace llamada API para extraer eventos");
        return null;
    }
    
    public Eventos [] extraerEvento(Date fecha, String tipoAlerta, double longitud, double latitud){
        System.out.println("Hace llamada API para extraer eventos");
        return null;
    }
    public OpcionesUsuario getOpcionesUsuario() {
        return opcionesUsuario;
    }

    public void setOpcionesUsuario(OpcionesUsuario opcionesUsuario) {
        this.opcionesUsuario = opcionesUsuario;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
