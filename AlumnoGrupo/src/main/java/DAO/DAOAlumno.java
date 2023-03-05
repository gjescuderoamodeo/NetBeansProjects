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
public class DAOAlumno extends DAO implements DAOAlumnoInterface{
    
     private ConexionBD conexion;

    public DAOAlumno(){
       conexion=new ConexionBD("mysql","usuariosygrupos","usuario","usuario");
    }

    @Override
    public boolean insertarAlumno(String nombre, int id_grupo) {
        boolean resultado=false;
        String sql="insert into alumno (nombre, id_grupo) values ('"+nombre+"','"+id_grupo+"')";    //JDBC
        
        try{
            conexion.abrirConexion();
            resultado=conexion.ejecutarUpdate(sql);
                      
        }catch (Exception e){
            System.out.println("error");
            return false;
        }
        
       
        
        
        return resultado;
    }

    @Override
    public void obtenerAlumnos() {
       System.out.println("*******************");
       conexion.ejecutarSQL("select * from alumno");
       conexion.mostrarRegistrosln();
       System.out.println("*******************");
    }

    @Override
    public boolean borrarAlumno(String nombre) {
       boolean resultado=false;
        String sql="delete from alumno where (nombre = '"+nombre+"')";    
        
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
