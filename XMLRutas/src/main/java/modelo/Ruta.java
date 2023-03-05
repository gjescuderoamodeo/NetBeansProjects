/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Ruta {
    
    private String nombre;
    private Lugar origen;
    private Lugar destino;
    private String distancia;

    public Ruta() {
    }

    public Ruta(String nombre, Lugar origen, Lugar destino, String distancia) {
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lugar getOrigen() {
        return origen;
    }

    public void setOrigen(Lugar origen) {
        this.origen = origen;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
    
    
    
    
    
    

    

    
    
    
}
