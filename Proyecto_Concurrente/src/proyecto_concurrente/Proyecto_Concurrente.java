/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_concurrente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jose9
    */
   public class Proyecto_Concurrente {
       private static ArrayList <Usuario> usuarios = new ArrayList<Usuario>();
       private static ArrayList <ContactoEmergencia> contactos = new ArrayList<ContactoEmergencia>();
       private static ArrayList <Eventos> eventos = new ArrayList<Eventos>();
       
       /**
        * @param args the command line arguments
        */
       public static void main(String[] args) {
           crearUsuario();
           mostrarMapa();
           for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(usuarios.get(i).toString());
           }
           for (int i = 0; i < contactos.size(); i++) {
                System.out.println(contactos.get(i).toString());
           }
           for (int i = 0; i < eventos.size(); i++) {
                System.out.println(eventos.get(i).toString());
           }

       }
    
        public static void crearUsuario(){
            Contraseña contraseña = new Contraseña();
            contraseña.setContraseña("fgh08h21");
            String fechaNacimiento = "1/1/1998";
            String id = String.valueOf(usuarios.size());
            String genero = "Mujer";
            String email = "janeDoe@email.com";
            String nombre = "Jane";
            String apellidos = "Doe";
            String celular = "88888888";

            Usuario u = new Usuario(contraseña, fechaNacimiento, genero, id, email, nombre, apellidos, celular);
            usuarios.add(u);
            crearContactoEmergencia(id);
            crearAlerta(id);
        }
        
        
        public static void crearContactoEmergencia(String idUsuarioPrincipal){
            String id = String.valueOf(usuarios.size());
            String email = "johnDoe@email.com";
            String nombre = "john";
            String apellidos = "Doe";
            String celular = "99999999";
            
            ContactoEmergencia c = new ContactoEmergencia(idUsuarioPrincipal, id, email, nombre, apellidos, celular);
            contactos.add(c);
        }

        public static void crearAlerta(String idUsuario){
            Date fecha = new Date();
            String tipoAlerta = "Robo";
            Eventos e = new Eventos(idUsuario, fecha, tipoAlerta);
            eventos.add(e);
        }
       
        public static void mostrarMapa(){
            Mapa m = new Mapa();
            Coordenadas c = new Coordenadas();
            m.mapaEstatico(c);
        }
       
   }
