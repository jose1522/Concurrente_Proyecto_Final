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
    private Contraseña contraseña;
    OpcionesUsuario opcionesUsuario;
    private String fechaNacimiento;
    private String genero;

    public Usuario(Contraseña contraseña,  String fechaNacimiento, String genero, String id, String email, String nombre, String apellidos, String celular) {
        super(id, email, nombre, apellidos, celular);
        this.contraseña = contraseña;
        this.opcionesUsuario = new OpcionesUsuario();
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    public OpcionesUsuario getOpcionesUsuario() {
        return opcionesUsuario;
    }

    public void setOpcionesUsuario(OpcionesUsuario opcionesUsuario) {
        this.opcionesUsuario = opcionesUsuario;
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
