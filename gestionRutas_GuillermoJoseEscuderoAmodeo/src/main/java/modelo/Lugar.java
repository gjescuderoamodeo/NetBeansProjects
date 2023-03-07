/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GuillermoJ
 */
@Entity
@Table(name = "lugar")
@XmlRootElement
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@Column(name = "id_lugar")
    //private Integer idLugar;
    
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")    
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private List<Ruta> rutaList;

    public Lugar() {
      rutaList = new ArrayList<Ruta>();   
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void addRuta(Ruta ruta){
        this.rutaList.add(ruta);
    }

    
    
    /*public Lugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public Integer getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Ruta> getRutaList() {
        return rutaList;
    }

    public void setRutaList(List<Ruta> rutaList) {
        this.rutaList = rutaList;
    }


    @Override
    public String toString() {
        return "Lugar{" + "nombre=" + nombre + '}';
    }


    
}
