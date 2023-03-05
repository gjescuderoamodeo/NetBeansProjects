/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author DON GUILLERMO
 */
import java.util.ArrayList;

public class Receta {
    private String nombre;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

    public Receta() {
    }
    
    

    public Receta(String nombre, ArrayList<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }
    
    public void addIngrediente(Ingrediente ingrediente){
        ingredientes.add(ingrediente);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Receta [nombre=" + nombre + "\n , ingredientes=" + ingredientes.toString() + "]";
    }
}

