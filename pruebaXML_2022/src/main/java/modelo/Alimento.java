/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author DON GUILLERMO
 */
public class Alimento {
    private String nombre;
    private double proteinas;
    private double grasas;
    private double hidratos;

    public Alimento() {
    }
    
    

    public Alimento(String nombre, double proteinas, double grasas, double hidratos) {
        this.nombre = nombre;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.hidratos = hidratos;
    }

    // getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getGrasas() {
        return grasas;
    }

    public void setGrasas(double grasas) {
        this.grasas = grasas;
    }

    public double getHidratos() {
        return hidratos;
    }

    public void setHidratos(double hidratos) {
        this.hidratos = hidratos;
    }

    @Override
    public String toString() {
        return "Alimento{" + "nombre=" + nombre + ", proteinas=" + proteinas + ", grasas=" + grasas + ", hidratos=" + hidratos + '}';
    }
    
    
}

