/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;


public class Itinerario {
    
    private String nombre;
    private ArrayList<Ruta> ruta;

    public Itinerario(String nombre) {
        this.nombre = nombre;
    }

    public Itinerario(String nombre, ArrayList<Ruta> ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }
    
    

    public ArrayList<Ruta> getRuta() {
        return ruta;
    }
    
    public void getRutas() {
        for(int i=0;i<ruta.size();i++){
            System.out.println(this.ruta.get(i).getNombre());
        }
        
        if(ruta.isEmpty()){
            System.out.println("Sin rutas");
        }
    }

    public void setRuta(ArrayList<Ruta> ruta) {
        this.ruta = ruta;
    }
    
    public void addRuta(Ruta ruta){
        this.ruta.add(ruta);
    }
    
    
    
    public Itinerario() {
        this.ruta=new ArrayList<>();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    
}
