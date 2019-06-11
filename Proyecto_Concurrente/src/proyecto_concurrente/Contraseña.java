package proyecto_concurrente;

import javax.swing.JOptionPane;

public class Contraseña {
    private String contraseña;
    
    
    public boolean validarTamaño(String input){
        boolean tamañoValido = false; //El valor por defecto es falso, a menos que cumpla el condicional
        if (input.length() > 6){ //Verifica que el largo de la variable input sea como minimo 6
            tamañoValido = true;
        }
        return tamañoValido;
    }
    
    public boolean validarSeguridad (String input){
        boolean contraseñaSegura = false;
        input = input.toUpperCase(); //Convierte todo a mayúscula para tener listas más pequeñas.
        String [] malasContraseñas = {"contraseña","password","123456","654321","012345","543210"}; //Lista de palabras no permitidas en contraseña
        for (String malasContraseña : malasContraseñas) {
            if (input.contains(malasContraseña.toUpperCase())){ //Si el input contiene un item no permitido, finaliza el ciclo y devuelve false
                JOptionPane.showMessageDialog(null, "La contraseña no puede contener: "+malasContraseña); //Mensaje de error.
                break;
            } else{
                contraseñaSegura = true;
            }
        }
        return contraseñaSegura;
    }
    

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = Util.hashString(contraseña);
    }

    @Override
    public String toString() {
        return  contraseña;
    }

}
