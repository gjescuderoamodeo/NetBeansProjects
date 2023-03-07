/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperacionesXML;

/**
 *
 * @author DON GUILLERMO
 */

import controladores_JPA.LugarJpaController;
import controladores_JPA.RutaJpaController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Lugar;
import modelo.Ruta;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RutasHandlerXML extends DefaultHandler {
    
    //creacion objetos
    private StringBuilder cadena;
    private Ruta ruta;
    private ArrayList<Lugar> rutas=new ArrayList<Lugar>();
    private Lugar lugar;
    
    //creacion jpa controllers
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdRutas");
    RutaJpaController daoRuta = new RutaJpaController(emf);
    LugarJpaController daoLugar = new LugarJpaController(emf);
  
    // Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public ArrayList<Lugar> getRutas() {
        return rutas;
    }
     
    // Parte que mapea el documento e introduce los datos en objetos
    
    @Override
    public void startDocument() throws SAXException {
        cadena=new StringBuilder();
        //recetas=new ArrayList<Receta>();
        System.out.println("startDocument");
          
    }
    
    @Override
    public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atrbts) throws SAXException {
        cadena.setLength(0);
        if (nombreCualif.equals("lugar")){
            lugar=new Lugar();
            lugar.setNombre(atrbts.getValue("nombre"));
            if(atrbts.getValue("descripcion")!=null){
                lugar.setDescripcion(atrbts.getValue("descripcion"));
            }
            //para que no la lie con Sevilla, lo creo antes y me quito problemas
            try {
                daoLugar.create(lugar);
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //si empieza con 
        if (nombreCualif.equals("ruta")){
            ruta=new Ruta();
            ruta.setNombre(atrbts.getValue("nombre"));
            ruta.setDistancia(atrbts.getValue("distancia"));
            
            //consulto base de datos si existe este lugar
            if(daoLugar.findLugar(atrbts.getValue("destino"))!=null){
                ruta.setDestino(daoLugar.findLugar(atrbts.getValue("destino")));
            }else{
                //lo creo y luego lo añado si no existe
                Lugar lugarsito = new Lugar();
                lugarsito.setNombre(atrbts.getValue("destino"));
                try {
                    daoLugar.create(lugarsito);
                } catch (Exception ex) {
                    Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
                }
                ruta.setDestino(lugarsito);
            }
            
        }       
       
         System.out.println("startElement: "+nombreLocal+ " "+nombreCualif);
     }
    
    @Override
    public void characters(char[] chars, int inicio, int lon) throws SAXException {
        
         cadena.append(chars, inicio, lon);
        
         System.out.println("dato final: "+cadena);
            
    }
    
    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {

        //si termina ruta, se lo añado al lugar y añado la ruta a la BD
        if (nombreCualif.equals("ruta")){
            lugar.addRuta(ruta);
            try {
                daoRuta.create(ruta);
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //si termina lugar, añado lugar a rutas (local) y si no existe, lo creo (BD). o lo modifico (BD)
        if (nombreCualif.equals("lugar")){
            rutas.add(lugar);
            try {
                if(daoLugar.findLugar(lugar.getNombre())==null){
                    daoLugar.create(lugar);
                }else{
                    daoLugar.edit(lugar);
                }                
                
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        
        //!!!!! con esto cogo el numero de en medio !!!!!!!!!!
        // si termina cantidad, establezco la cantidad del ingrediente actual
        //if (nombreCualif.equals("cantidad")) {
        //double cantidad = Double.parseDouble(cadena.toString().trim());
        //    ingrediente.setCantidad((int) cantidad);
        //}
        
        
        System.out.println("endtElement: "+nombreLocal+ " "+nombreCualif);
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
       
    }
}


