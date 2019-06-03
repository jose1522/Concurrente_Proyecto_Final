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
public class ContactoEmergencia extends Persona{
    private String idUsuarioPrincipal;

    public ContactoEmergencia(String idUsuarioPrincipal, String id, String email, String nombre, String apellidos, String celular) {
        super(id, email, nombre, apellidos, celular);
        this.idUsuarioPrincipal = idUsuarioPrincipal;
    }

    public String getIdUsuarioPrincipal() {
        return idUsuarioPrincipal;
    }

    public void setIdUsuarioPrincipal(String idUsuarioPrincipal) {
        this.idUsuarioPrincipal = idUsuarioPrincipal;
    }
        
}
