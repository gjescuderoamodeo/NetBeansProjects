/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author DON GUILLERMO
 */
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alimento")
public class Alimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    
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
        return "Alimento{" + "id=" + id + ", nombre=" + nombre + ", proteinas=" + proteinas + ", grasas=" + grasas + ", hidratos=" + hidratos + '}';
    }
    
}


