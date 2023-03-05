/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.examen1ada;

import DAO.DaoAlimento;
import entidades.Alimento;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillermoJ
 */
public class Examen1ADA {

    public static void main(String[] args) {
        System.out.println("Ejercicio2");
        DaoAlimento ej2 = new DaoAlimento();
        ej2.insertarAlimento("queso",20, 24, (float) 2.5);
        
        
        //ej3
        System.out.println("----------");
        System.out.println("Ejercicio3");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examen1ada");   
        
              
        DaoAlimento daoAlimento= new DaoAlimento(emf);        
        List<Alimento> Alimentos = daoAlimento.obtenerAlimento();        
        //Buscar Alimentos
        for (Alimento Alimento:Alimentos){
            System.out.println("nombre: " + Alimento.getNombre()+ " grasas: " + Alimento.getGrgrasa());
        }
        
        //ej5
        System.out.println("----------");
        System.out.println("Ejercicio5");
        daoAlimento.ej5("test");
    }
}
