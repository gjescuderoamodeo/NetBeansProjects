/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author GuillermoJ
 */
public interface DAOGrupoInterface {
    
    public boolean insertarGrupo(String nombre);
    public void obtenerGrupos ();
    public boolean borrarGrupo (String nombre);
    
}
