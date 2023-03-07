/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperacionesXML;

/**
 *
 * @author DON GUILLERMO
 */

import controladores.LugarJpaController;
import controladores.RutaJpaController;
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
    private ArrayList<Ruta> rutas=new ArrayList<Ruta>();
    private Lugar lugar;
    
    //creacion jpa controllers
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdRutas");
    RutaJpaController daoRuta = new RutaJpaController(emf);
    LugarJpaController daoLugar = new LugarJpaController(emf);
  
    // Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public ArrayList<Ruta> getRutas() {
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
        }
        //si empieza con 
        if (nombreCualif.equals("ruta")){
            ruta=new Ruta();
            ruta.setNombre(atrbts.getValue("nombre"));
            ruta.setDistancia(atrbts.getValue("distancia"));
            
            //consulto base de datos si existe este lugar
            if(daoLugar.findLugar(atrbts.getValue("destino"))!=null){
                
            }
            
        }
        //si empieza con 
        if (nombreCualif.equals("proteinas")){
            alimento.setProteinas(Double.parseDouble(atrbts.getValue("cantidad100g")));
        }
        //si empieza con 
        if (nombreCualif.equals("grasas")){
            alimento.setGrasas(Double.parseDouble(atrbts.getValue("cantidad100g")));
        }
        //si empieza con 
        if (nombreCualif.equals("hidratos")){
            alimento.setHidratos(Double.parseDouble(atrbts.getValue("cantidad100g")));
        }
        //si empieza con 
        //if (nombreCualif.equals("cantidad")){
        //    ingrediente.setCantidad(20);
        //}
        
       
         System.out.println("startElement: "+nombreLocal+ " "+nombreCualif);
     }
    
    @Override
    public void characters(char[] chars, int inicio, int lon) throws SAXException {
        
         cadena.append(chars, inicio, lon);
        
         System.out.println("dato final: "+cadena);
            
    }
    
    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {

        //si termina ingrediente, añado ingrediente a receta
        if (nombreCualif.equals("ingrediente")){
            receta.addIngrediente(ingrediente);
            try {
                daoIngrediente.create(ingrediente);
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //si termina alimento, añado alimento a ingrediente 
        if (nombreCualif.equals("alimento")){
            ingrediente.setAlimento(alimento);
            try {
                daoAlimento.create(alimento);
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nombreCualif.equals("receta")){
            recetas.add(receta);
            try {
                daoReceta.create(receta);
            } catch (Exception ex) {
                Logger.getLogger(RutasHandlerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //!!!!! con esto cogo el numero de en medio !!!!!!!!!!
        // si termina cantidad, establezco la cantidad del ingrediente actual
        if (nombreCualif.equals("cantidad")) {
        double cantidad = Double.parseDouble(cadena.toString().trim());
            ingrediente.setCantidad((int) cantidad);
        }
        
        
        System.out.println("endtElement: "+nombreLocal+ " "+nombreCualif);
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
       
    }
}


