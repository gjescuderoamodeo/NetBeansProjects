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
public class Alumno {
    
    private int id_alumno;
    private String nombre;
    
    private Grupo grupo_alumno;

    /**
     * @return the id_alumno
     */
    public int getId_alumno() {
        return id_alumno;
    }

    /**
     * @param id_alumno the id_alumno to set
     */
    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
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
     * @return the grupo_alumno
     */
    public Grupo getGrupo_alumno() {
        return grupo_alumno;
    }

    /**
     * @param grupo_alumno the grupo_alumno to set
     */
    public void setGrupo_alumno(Grupo grupo_alumno) {
        this.grupo_alumno = grupo_alumno;
    }
    
    
    
    
    
}
