/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionDB_JDBC;

/**
 *
 * @author GuillermoJ
 */
public class PruebaConexiónBD_JDBC {
    
    public static void main(String[] args){
        
        ConexionBD_JDBC conex = new ConexionBD_JDBC("mysql","bdrutasGuillermoJose","root","root");
        
        //conex.abrirConexion();
        
        conex.ejecutarSQL("select * from lugar");
        conex.mostrarRegistrosln();
        System.out.println("*******************");
        conex.mostrarRegistros();
        conex.imprimirTabla();
        
        conex.ejecutarSQL("select * from ruta");
        conex.mostrarRegistrosln();
        System.out.println("*******************");
        conex.mostrarRegistros();
        conex.imprimirTabla();
        
    }
    
    
    
}
