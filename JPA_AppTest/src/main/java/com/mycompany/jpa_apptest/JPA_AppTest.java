/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.jpa_apptest;

import DAO.HotelJpaController;
import DAO.PlantaJpaController;
import DAO.ServicioJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Hotel;
import modelo.Planta;
import modelo.Servicio;

/**
 *
 * @author GuillermoJ
 */
public class JPA_AppTest {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany.jpa_apptest_JPA_AppTest_jar_1.0-SNAPSHOTPU");
      
        
              
        HotelJpaController daoHotel = new HotelJpaController(emf);
        PlantaJpaController daoPlanta = new PlantaJpaController(emf);
        ServicioJpaController daoServicio = new ServicioJpaController(emf);
        
        //crear hotel
        //Hotel hotel=new Hotel();
        //hotel.setNombre("Hotel1");
        //hotel.setIdHotel(1);
        //daoHotel.create(hotel);
        System.out.println("conexión realizada");
        
        //añadir planta hotel
        //Planta planta = new Planta();
        //planta.setHotelIdHotel(1);
        //planta.setIdPlanta(1);
        //planta.setNombre("sotano");
        //daoPlanta.create(planta);
        
        
        //añadir servicio
        Servicio serv = new Servicio();
        serv.setNombre("Restaurante");
        serv.setIdServicio(1);
        daoServicio.create(serv);
    }
}
