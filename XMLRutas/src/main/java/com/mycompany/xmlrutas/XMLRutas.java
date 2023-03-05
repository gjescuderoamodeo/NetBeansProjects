/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.xmlrutas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Itinerario;
import modelo.Ruta;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author GuillermoJ
 */
public class XMLRutas {
    
    private static DefaultHandler handler;

    public static void main(String[] args) throws Exception {
        String ruta="D:\\DAM\\Ejercicios\\ADA\\NetBeansProjects\\XMLs\\rutas2.xml";
        LugaresHandlerXML handler=new LugaresHandlerXML();
        
        leerXML(handler,ruta);
        ArrayList<Itinerario> itinerarios=handler.getItinerarios();
        
        for(Itinerario i:itinerarios){
          System.out.print("Itinerario: "+i.getNombre()+"\n");
          System.out.print("Rutas \n");
          i.getRutas();
          System.out.print("\n");
        }
    }
    
    private static void leerXML(DefaultHandler handler, String nombre)throws Exception {
         //localizo el fichero XML
        File file = new File(nombre);
	InputStream entrada = new FileInputStream(file);
        
        //Instancio una factoria y la factoria me proporciona un objeto de la
        //clase SaxParser
        SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();  
        
        //Del objeto saxParser obtengo un lector de archivos XML
        XMLReader lector = saxParser.getXMLReader();
        
        //le pasamos al lector el manejador específico para ese archivo XML
        lector.setContentHandler(handler);
        lector.parse(new InputSource(entrada));
   }
}
