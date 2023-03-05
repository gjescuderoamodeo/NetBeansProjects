/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import conexionDB.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author GuillermoJ
 */
public class ModeloAlumnos {
    
    private ArrayList<Alumno> listaAlumnos= new ArrayList<Alumno>();
    private ArrayList<Grupo> listaGrupos= new ArrayList<Grupo>();

    
    public void guardar(Alumno u){
        this.getListaUsuarios().add(u);    
        //System.out.println(u.getApellidos());
    }
    
     public void mostrarUsuario(){   
        Iterator it = this.listaAlumnos.iterator();
        Alumno us;
        while(it.hasNext()){
            us =(Alumno) it.next();
            System.out.println("nombre: " + us.getNombre());
        }
    }
     
     public void guardarBDAlumno(String nombre,int id_grupo) throws SQLException{
        
        ModeloDatosBD conex = new ModeloDatosBD();
        conex.insertarAlumno(nombre,id_grupo);      
            
    }
     
    public void cargarListaGrupo(){
        ConexionBD conex = new ConexionBD("mysql","usuariosygrupos","root","root");
        
        conex.ejecutarSQL("select * from grupo");
        //conex.mostrarRegistrosln();
        //System.out.println("*******************");
                
    } 

    /**
     * @return the listaUsuarios
     */
    public ArrayList<Alumno> getListaUsuarios() {
        return listaAlumnos;
    }

    
}

