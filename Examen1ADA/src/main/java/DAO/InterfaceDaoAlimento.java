/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Alimento;
import java.util.List;

/**
 *
 * @author enrique
 */
public interface InterfaceDaoAlimento {
    
    public boolean insertarAlimento(String nombre,int proteinas, int grasas, float hidratos);
    public void ej5(String nombreReceta);
    public List<Alimento> obtenerAlimento ();
    
    
}
