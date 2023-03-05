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
public class DAO {
    private ConexionBD conexion;

    public DAO(){
       conexion=new ConexionBD("mysql","usuariosygrupos","usuario","usuario");
    } 
}
