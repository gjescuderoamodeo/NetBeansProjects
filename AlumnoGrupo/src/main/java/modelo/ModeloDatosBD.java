/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import conexionDB.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author españa
 */
public class ModeloDatosBD {

   
    
    private ConexionBD conexion;

    public ModeloDatosBD(){
       conexion=new ConexionBD("mysql","usuariosygrupos","usuario","usuario");
    }    
    
    public boolean insertarAlumno (String nombre,int id_grupo){
           
        boolean resultado=false;
        String sql="insert into alumno (nombre, id_grupo) values ('"+nombre+"','"+id_grupo+"')";    
        
        try{
            conexion.abrirConexion();
            resultado=conexion.ejecutarUpdate(sql);
                      
        }catch (Exception e){
            System.out.println("error");
            return false;
        }
        
       
        
        
        return resultado;
    }
    
    
}
