/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionDB;

/**
 *
 * @author GuillermoJ
 */
public class PruebaConexiónBD {
    
    public static void main(String[] args){
        
        ConexionBD conex = new ConexionBD("mysql","usuariosygrupos","root","root");
        
        //conex.abrirConexion();
        
        conex.ejecutarSQL("select * from alumno");
        conex.mostrarRegistrosln();
        System.out.println("*******************");
        conex.mostrarRegistros();
        conex.imprimirTabla();
        
    }
    
    
    
}
