/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GuillermoJ
 */
@Entity
@Table(name = "habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h"),
    @NamedQuery(name = "Habitacion.findByIdHabitacion", query = "SELECT h FROM Habitacion h WHERE h.idHabitacion = :idHabitacion"),
    @NamedQuery(name = "Habitacion.findByCantidad", query = "SELECT h FROM Habitacion h WHERE h.cantidad = :cantidad"),
    @NamedQuery(name = "Habitacion.findByPlantaIdPlanta", query = "SELECT h FROM Habitacion h WHERE h.plantaIdPlanta = :plantaIdPlanta")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_habitacion")
    private Integer idHabitacion;
    @Column(name = "cantidad")
    private String cantidad;
    @Basic(optional = false)
    @Column(name = "planta_id_planta")
    private int plantaIdPlanta;

    public Habitacion() {
    }

    public Habitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Habitacion(Integer idHabitacion, int plantaIdPlanta) {
        this.idHabitacion = idHabitacion;
        this.plantaIdPlanta = plantaIdPlanta;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getPlantaIdPlanta() {
        return plantaIdPlanta;
    }

    public void setPlantaIdPlanta(int plantaIdPlanta) {
        this.plantaIdPlanta = plantaIdPlanta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabitacion != null ? idHabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.idHabitacion == null && other.idHabitacion != null) || (this.idHabitacion != null && !this.idHabitacion.equals(other.idHabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Habitacion[ idHabitacion=" + idHabitacion + " ]";
    }
    
}
