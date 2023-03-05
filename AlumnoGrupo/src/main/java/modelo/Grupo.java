/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author GuillermoJ
 */
public class Grupo {
    
    private int id_grupo;
    private String nombre;
    
    private ArrayList<Alumno> alumnos_del_grupo;
    

    /**
     * @return the id_grupo
     */
    public int getId_grupo() {
        return id_grupo;
    }

    /**
     * @param id_grupo the id_grupo to set
     */
    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the alumnos_del_grupo
     */
    public ArrayList<Alumno> getAlumnos_del_grupo() {
        return alumnos_del_grupo;
    }

    /**
     * @param alumnos_del_grupo the alumnos_del_grupo to set
     */
    public void setAlumnos_del_grupo(ArrayList<Alumno> alumnos_del_grupo) {
        this.alumnos_del_grupo = alumnos_del_grupo;
    }
    
    
    
}
