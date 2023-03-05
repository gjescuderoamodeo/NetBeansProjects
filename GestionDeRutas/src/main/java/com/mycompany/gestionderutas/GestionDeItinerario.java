/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gestionderutas;

import entidades.Itinerario;
import entidades.Lugar;
import entidades.Ruta;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

import javax.persistence.Persistence;

/**
 *
 * @author GuillermoJ
 */
public class GestionDeItinerario {

    public static void main(String[] args) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionDeRutas");             
      
        
      //Crear Itinerario
      Itinerario itenerario=new Itinerario();
      //itenerario.setRutaList(rutaList);
       
      Lugar lugar2=new Lugar();
      lugar2.setNombre("lugar22");
       
      //Crear Ruta
      Ruta ruta = new Ruta();
      //ruta.setDestino(lugar);
      ruta.setOrigen(lugar2);    
        
      //daoLugar.create(lugar);
      EntityManager ef = emf.createEntityManager();
        try{
            //daoRuta.create(ruta);
          ef.getTransaction().begin();
          
          //ef.persist(lugar);
          ef.persist(lugar2);
          ef.persist(ruta);

          ef.getTransaction().commit();
          
          
        }catch(Exception e){
            System.out.println("Excepción: " + e);
        }
        
        
        System.out.println("conexión realizada");
        
    }
}
