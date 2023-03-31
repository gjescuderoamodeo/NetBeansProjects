/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainJPA;


import OperacionesXML.RutasHandlerXML;
import controladores_JPA.LugarJpaController;
import controladores_JPA.RutaJpaController;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Lugar;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DON GUILLERMO
 */
public class mainJPA {
      public static void main(String[] args) throws Exception { 
          
        //String ruta = "E:\\DAM\\Ejercicios\\ADA\\NetBeansProjects\\XMLs\\rutas.xml";
        String ruta = "src/main/java/XML/rutas.xml";
        RutasHandlerXML handler = new RutasHandlerXML();

        leerXML(handler, ruta);
        ArrayList<Lugar> rutas = handler.getRutas();

        for (Lugar p : rutas) {
            System.out.println("rutas: " + p.toString());
        }

        //listado rutas determinado lugar
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdRutas");
      

        LugarJpaController daoLugar = new LugarJpaController(emf);
        RutaJpaController daoRuta = new RutaJpaController(emf);
        
        String nombre = "Granada";
        
        System.out.println("Rutas de un determinado lugar");
        System.out.println(daoRuta.findRutasOfLugar(nombre));        

    }  
      
      private static void leerXML(DefaultHandler handler, String nombre) throws Exception {
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
