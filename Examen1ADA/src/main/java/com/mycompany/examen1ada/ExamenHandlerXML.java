/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.examen1ada;

import entidades.Alimento;
import entidades.Ingrediente;
import entidades.IngredientePK;
import entidades.Receta;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ExamenHandlerXML extends DefaultHandler{
    
    private StringBuilder cadena;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private Receta receta = new Receta();
    private Ingrediente ingrediente = new Ingrediente();
    private Alimento alimento;
    private IngredientePK ingredientePK = new IngredientePK();
   
  
  //Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public Receta getRecetas() {
        return receta;
    }
     
    
    //parte que mapea el documento e introduce los datos en objetos
    
          
    @Override
    public void startDocument() throws SAXException {
        cadena=new StringBuilder();
        //itinerarios=new ArrayList<Itinerario>();
        //itinerario=new Itinerario();
        //System.out.println("startDocument");
          
    }
    
    @Override
    public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atrbts) throws SAXException {
        cadena.setLength(0);
        if (nombreCualif.equals("ingrediente")){
            Ingrediente ingrediente=new Ingrediente();
            ingrediente.getAlimento().setNombre(atrbts.getValue("nombre"));
            
            //lugar.setNombre(atrbts.getValue("nombre"));
            //this.lugares.add(lugar);
        }else if (nombreCualif.equals("alimento")){   
            this.alimento=new Alimento();
           
        }else if (nombreCualif.equals("receta")){
            this.receta=new Receta(); 
            receta.setNombre(atrbts.getValue("nombre"));         
        }else if (nombreCualif.equals("proteinas")){   
            this.alimento.setGrprot(atrbts.getValue("cantidad100"));           
        }else if (nombreCualif.equals("grasas")){   
            this.alimento.setGrgrasa(atrbts.getValue("cantidad100"));           
        }else if (nombreCualif.equals("hidratos")){   
            this.alimento.setGrhidratos(atrbts.getValue("cantidad100"));           
        }
         //System.out.println("startElement: "+nombreLocal+ " "+nombreCualif);
     }
    
    @Override
    public void characters(char[] chars, int inicio, int lon) throws SAXException {
        
         cadena.append(chars, inicio, lon);
        
         //System.out.println("dato final: "+cadena);
            
    }
    
    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {
        if(nombreCualif.equals("alimento")){
            this.ingredientePK.setIdAlimento(0);
            
        }
        //System.out.println("endtElement: "+nombreLocal+ " "+nombreCualif);
    }
    
    @Override
    public void endDocument() throws SAXException {
        //System.out.println("endDocument");
       
    }
   
  
}
