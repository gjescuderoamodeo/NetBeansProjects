/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pruebaherenciajpa2;

import controllers.PersonaJpaController;
import entidades.Persona;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GuillermoJ
 */
public class PruebaHerenciaJPA2 {

    public static void main(String[] args) throws Exception {        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pruebaHerenciaJPAPU");
      
        
              
        PersonaJpaController daoPersona = new PersonaJpaController(emf);
        
        //crear persona
        Persona persona=new Persona();
        persona.setEstudios("pepe");
        persona.setNombre("Luis");
        daoPersona.create(persona);
        
        System.out.println("conexión realizada");
    }
}
