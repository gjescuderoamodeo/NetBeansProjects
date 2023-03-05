/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebaxml_2022;

/**
 *
 * @author DON GUILLERMO
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Ingrediente;
import modelo.Receta;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Main2 {

    private static DefaultHandler handler;

    public static void main(String[] args) throws Exception {

        String ruta = "I:\\DAM\\Ejercicios\\ADA\\NetBeansProjects\\XMLs\\recetas.xml";
        RecetasHandlerXML handler = new RecetasHandlerXML();

        leerXML(handler, ruta);
        ArrayList<Receta> recetas = handler.getRecetas();

        for (Receta p : recetas) {
            System.out.println("receta: " + p.toString());
        }
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
