/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

/**
 *
 * @author jose9
 */
public class Usuario extends Persona{
    private ContactosEmergencia [] contactosEmergencia;
    private Contraseña contraseña;
    OpcionesUsuario opcionesUsuario;
    private String fechaNacimiento;
    private String genero;

    public Usuario(ContactosEmergencia[] contactosEmergencia, Contraseña contraseña, OpcionesUsuario opcionesUsuario, String fechaNacimiento, String genero, String id, String email, String nombre, String apellidos, String celular) {
        super(id, email, nombre, apellidos, celular);
        this.contactosEmergencia = contactosEmergencia;
        this.contraseña = contraseña;
        this.opcionesUsuario = opcionesUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    public OpcionesUsuario getOpcionesUsuario() {
        return opcionesUsuario;
    }

    public void setOpcionesUsuario(OpcionesUsuario opcionesUsuario) {
        this.opcionesUsuario = opcionesUsuario;
    }
    
    public ContactosEmergencia[] getContactosEmergencia() {
        return contactosEmergencia;
    }

    public void setContactosEmergencia(ContactosEmergencia[] contactosEmergencia) {
        this.contactosEmergencia = contactosEmergencia;
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
}
