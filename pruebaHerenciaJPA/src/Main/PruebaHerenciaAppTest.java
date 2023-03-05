/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Main;

import controllers.PersonaJpaController;
import entidades.Persona;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillermoJ
 */
public class PruebaHerenciaAppTest {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pruebaHerenciaJPAPU");
      
        
              
        PersonaJpaController daoPersona = new PersonaJpaController(emf);
        
        //crear persona
        //Persona persona=new Persona();
        //persona.setEstudios("pepe");
        //persona.setNombre("Luis");
        
        System.out.println("conexión realizada");
    }
}
