/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebaxml_2022;

/**
 *
 * @author DON GUILLERMO
 */
import controladores.AlimentoJpaController;
import controladores.IngredienteJpaController;
import controladores.RecetaJpaController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Alimento;
import modelo.Ingrediente;
import modelo.Receta;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RecetasHandlerXML2 extends DefaultHandler {
    
    private StringBuilder cadena;
    private Receta receta;
    private ArrayList<Receta> recetas=new ArrayList<Receta>();
    private Ingrediente ingrediente;
    private Alimento alimento;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("examen");
    AlimentoJpaController daoAlimento = new AlimentoJpaController(emf);
    RecetaJpaController daoReceta = new RecetaJpaController(emf);
    IngredienteJpaController daoIngrediente = new IngredienteJpaController(emf);

        //crear persona
        /*Alimento alimento=new Alimento();
        alimento.setNombre("test");
        alimento.setProteinas(333);
        daoAlimento.create(alimento);*/
  
    // Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public ArrayList<Receta> getRecetas() {
        return recetas;
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
        if (nombreCualif.equals("receta")){
            receta=new Receta();
            receta.setNombre(atrbts.getValue("nombre"));
        }
        //si empieza con 
        if (nombreCualif.equals("ingrediente")){
            ingrediente=new Ingrediente();
        }
        //si empieza con 
        if (nombreCualif.equals("alimento")){
            alimento=new Alimento();
            alimento.setNombre(atrbts.getValue("nombre"));
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
                Logger.getLogger(RecetasHandlerXML2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //si termina alimento, añado alimento a ingrediente 
        if (nombreCualif.equals("alimento")){
            ingrediente.setAlimento(alimento);
            try {
                daoAlimento.create(alimento);
            } catch (Exception ex) {
                Logger.getLogger(RecetasHandlerXML2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nombreCualif.equals("receta")){
            recetas.add(receta);
            try {
                daoReceta.create(receta);
            } catch (Exception ex) {
                Logger.getLogger(RecetasHandlerXML2.class.getName()).log(Level.SEVERE, null, ex);
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


