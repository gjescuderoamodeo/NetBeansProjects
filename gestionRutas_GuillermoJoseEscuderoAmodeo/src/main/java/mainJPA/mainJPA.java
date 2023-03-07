/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainJPA;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DON GUILLERMO
 */
public class mainJPA {
      public static void main(String[] args) throws Exception { 
          
        String ruta = "I:\\DAM\\Ejercicios\\ADA\\NetBeansProjects\\XMLs\\rutas.xml";
        RecetasHandlerXML2 handler = new RecetasHandlerXML2();

        leerXML(handler, ruta);
        ArrayList<Receta> recetas = handler.getRecetas();

        for (Receta p : recetas) {
            System.out.println("receta: " + p.toString());
        }
        
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("examen");
      
        
              
        //AlimentoJpaController daoAlimento = new AlimentoJpaController(emf);
        
        //crear persona
        /*Alimento alimento=new Alimento();
        alimento.setNombre("test");
        alimento.setProteinas(333);
        daoAlimento.create(alimento);*/
        
        //Ingrediente ingrediente=new Ingrediente();
        //ingrediente.setAlimento("test");
        //ingrediente.setCantidad(0);
        //daoAlimento.create(ingrediente);
        
        //System.out.println("conexión realizada");
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
