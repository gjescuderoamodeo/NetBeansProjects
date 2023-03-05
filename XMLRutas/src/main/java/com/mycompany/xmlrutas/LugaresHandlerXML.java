/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xmlrutas;

import java.util.ArrayList;
import modelo.Itinerario;
import modelo.Lugar;
import modelo.Ruta;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LugaresHandlerXML extends DefaultHandler{
    
    private StringBuilder cadena;
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<Itinerario> itinerarios = new ArrayList<>();
    private ArrayList<Lugar> lugares = new ArrayList<>();
    private Lugar lugar = new Lugar();
    Ruta ruta;
    Itinerario itinerario;
   
  
  //Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public ArrayList<Itinerario> getItinerarios() {
        return itinerarios;
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
        //System.out.println(nombreCualif);
        if (nombreCualif.equals("lugar")){
            Lugar lugar=new Lugar(); 
            lugar.setNombre(atrbts.getValue("nombre"));
            this.lugares.add(lugar);
        }else if (nombreCualif.equals("ruta")){       
            
            Boolean ok=true;
            this.ruta = new Ruta();
            
            for(Ruta r:this.rutas){                
                if(r.getNombre().equals(atrbts.getValue("nombre"))){
                    ok=false;
                    this.ruta=r;
                }
            }
            //System.out.println(ok);
            if(ok){            
            this.ruta.setNombre(atrbts.getValue("nombre"));
            for(Lugar l:this.lugares){                
                if(l.getNombre().equals(atrbts.getValue("origen"))){
                    this.ruta.setOrigen(l);
                }
                if(l.getNombre().equals(atrbts.getValue("destino"))){
                    this.ruta.setDestino(l);
                }
            }
            this.ruta.setDistancia(atrbts.getValue("distancia"));
            this.rutas.add(this.ruta);
            }else{
                if(this.itinerario!=null){                             
                    this.itinerario.addRuta(this.ruta);  
                    //this.itinerario.getRutas();           
                }                
            }            
        }else if (nombreCualif.equals("itinerario")){
            this.itinerario=new Itinerario(); 
            itinerario.setNombre(atrbts.getValue("nombre"));         
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
        if(nombreCualif.equals("itinerario")){
            //Lugar origen = new Lugar();
            //origen.setNombre(cadena.toString());      
            //ruta.setOrigen(this.origen);
            this.itinerarios.add(this.itinerario);
            //this.itinerario=null;
        }else if(nombreCualif.equals("ruta")){
            //this.ruta=null;
        } 
        //System.out.println("endtElement: "+nombreLocal+ " "+nombreCualif);
    }
    
    @Override
    public void endDocument() throws SAXException {
        //System.out.println("endDocument");
       
    }
   
  
}
