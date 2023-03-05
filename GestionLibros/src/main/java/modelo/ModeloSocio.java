/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import conexionDB.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import modeloDatos.ModeloDatosBD;
import modeloDatos.Socio;

/**
 *
 * @author GuillermoJ
 */
public class ModeloSocio {
    
    private ArrayList<Socio> listaUsuarios= new ArrayList<Socio>();


    
    public void guardar(Socio u){
        this.getListaUsuarios().add(u);    
        //System.out.println(u.getApellidos());
    }
    
    public void guardarBD(Socio u) throws SQLException{
        this.getListaUsuarios().add(u); 
        
        ModeloDatosBD conex = new ModeloDatosBD();
        
        //recorro la lista y voy añadiendo a la BD
        Iterator it = this.listaUsuarios.iterator();
        Socio so;
        while(it.hasNext()){
            so = (Socio) it.next();
            //conex.ejecutarSQL("INSERT INTO `gestionlibros`.`socio` (`nombre`, `perfil`) "
            //        + "VALUES ('"+ so.getNombre() + "', '" + so.getPerfil() +"')");
            //conex.ejecutarSQL("INSERT INTO `gestionlibros`.`socio` (`nombre`, `perfil`) VALUES ('y', 'y');");
            conex.insertarSocio(so);      
            
        }     
       
        //ahora borro la lista
        this.listaUsuarios.clear();
        
        //System.out.println(u.getApellidos());
    }
    
    public void mostrarSocioBD(){   
        ConexionBD conex = new ConexionBD("mysql","gestionlibros","root","root");
        
        conex.ejecutarSQL("select * from socio");
        conex.mostrarRegistrosln();
        System.out.println("*******************");
        
    }
    
     public void mostrarUsuario(){   
        Iterator it = this.listaUsuarios.iterator();
        Socio us;
        while(it.hasNext()){
            us =(Socio) it.next();
            System.out.println("nombre: " + us.getNombre() + " perfil: " + us.getPerfil());
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
    public ArrayList<Socio> getListaUsuarios() {
        return listaUsuarios;
    }

    
}

