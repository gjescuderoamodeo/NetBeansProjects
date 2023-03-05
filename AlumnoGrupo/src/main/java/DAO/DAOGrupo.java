/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexionDB.ConexionBD;

/**
 *
 * @author GuillermoJ
 */
public class DAOGrupo extends DAO implements DAOGrupoInterface{
    
    private ConexionBD conexion;

    public DAOGrupo(){
       conexion=new ConexionBD("mysql","usuariosygrupos","usuario","usuario");
    }    
    
    //private DAO conexion = new DAO();
    
    //insertar grupos
    
    public boolean insertarGrupo (String nombre){
           
        boolean resultado=false;
        String sql="insert into grupo (nombre) values ('"+nombre+"')";    
        
        try{
            conexion.abrirConexion();
            resultado=conexion.ejecutarUpdate(sql);
                      
        }catch (Exception e){
            System.out.println("error");
            return false;
        }       
        
        return resultado;
    }
    
    //obtener grupos
    public void obtenerGrupos (){
        
       System.out.println("*******************");       
       conexion.ejecutarSQL("select * from grupo");
       conexion.mostrarRegistrosln();
       System.out.println("*******************");
       
    }
    
    //borrar grupos
    public boolean borrarGrupo (String nombre){
           
        boolean resultado=false;
        String sql="delete from grupo where (nombre = '"+nombre+"')";    
        
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
