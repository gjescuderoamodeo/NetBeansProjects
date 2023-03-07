/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package MainTests;

import controladores_JPA.LugarJpaController;
import controladores_JPA.RutaJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Lugar;
import modelo.Ruta;

/**
 *
 * @author GuillermoJ
 */
public class GestionRutas_GuillermoJoseEscuderoAmodeo {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdRutas");
      

        LugarJpaController daoLugar = new LugarJpaController(emf);
        RutaJpaController daoRuta = new RutaJpaController(emf);
        
        //listado rutas determinado lugar
        String nombre = "Sevilla";
        
        System.out.println("Rutas de un determinado lugar");
        System.out.println(daoRuta.findRutasOfLugar(nombre));
        
        
        //
        /*Lugar lugar=new Lugar();
        lugar.setNombre("test");
        lugar.setDescripcion("precioso");
        Ruta ruta = new Ruta();
        ruta.setNombre("test-test");
        ruta.setDistancia("2000");
        ruta.setDestino(lugar);
        daoLugar.create(lugar);
        lugar.addRuta(ruta);
        daoRuta.create(ruta);
        daoLugar.edit(lugar);*/
        
        //System.out.println("conexión realizada");
    }
}
