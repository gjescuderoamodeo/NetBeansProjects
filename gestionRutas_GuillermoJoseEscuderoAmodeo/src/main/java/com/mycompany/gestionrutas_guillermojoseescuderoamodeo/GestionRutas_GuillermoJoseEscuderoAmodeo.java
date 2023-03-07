/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gestionrutas_guillermojoseescuderoamodeo;

import controladores.LugarJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Lugar;

/**
 *
 * @author GuillermoJ
 */
public class GestionRutas_GuillermoJoseEscuderoAmodeo {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examen");
      
        
              
        LugarJpaController daoLugar = new LugarJpaController(emf);
        
        //
        Lugar lugar=new Lugar();
        lugar.setNombre("test");
        daoLugar.create(lugar);
        
        //System.out.println("conexión realizada");
    }
}
