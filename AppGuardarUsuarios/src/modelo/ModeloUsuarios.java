/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import appguardarusuarios.Usuario;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author GuillermoJ
 */
public class ModeloUsuarios {
    
    private ArrayList<Usuario> listaUsuarios= new ArrayList<Usuario>();


    
    public void guardar(Usuario u){
        this.getListaUsuarios().add(u);    
        //System.out.println(u.getApellidos());
    }
    
     public void mostrarUsuario(){   
        Iterator it = this.listaUsuarios.iterator();
        Usuario us;
        while(it.hasNext()){
            us =(Usuario) it.next();
            System.out.println("nombre: " + us.getNombre() + " apellidos: " + us.getApellidos());
        }
         //System.out.println("pepe");
             
//        for(int i=0;i<=this.listaUsuarios.size();i++){
//            String nombre = this.listaUsuarios.get(i).getNombre();
//            String apellidos = this.listaUsuarios.get(i).getApellidos();
//            System.out.println("Usuario" + i + " " + nombre + " " + apellidos);
//        }
    }

    /**
     * @return the listaUsuarios
     */
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    
}

