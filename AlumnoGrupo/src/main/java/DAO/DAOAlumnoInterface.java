/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author GuillermoJ
 */
public interface DAOAlumnoInterface {
    
    public boolean insertarAlumno(String nombre, int id_grupo);
    public void obtenerAlumnos ();
    public boolean borrarAlumno (String nombre);
    
}
