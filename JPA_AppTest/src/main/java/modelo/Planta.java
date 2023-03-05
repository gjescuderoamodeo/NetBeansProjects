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
@Table(name = "planta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planta.findAll", query = "SELECT p FROM Planta p"),
    @NamedQuery(name = "Planta.findByIdPlanta", query = "SELECT p FROM Planta p WHERE p.idPlanta = :idPlanta"),
    @NamedQuery(name = "Planta.findByNombre", query = "SELECT p FROM Planta p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Planta.findByHotelIdHotel", query = "SELECT p FROM Planta p WHERE p.hotelIdHotel = :hotelIdHotel")})
public class Planta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_planta")
    private Integer idPlanta;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "hotel_id_hotel")
    private int hotelIdHotel;

    public Planta() {
    }

    public Planta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public Planta(Integer idPlanta, int hotelIdHotel) {
        this.idPlanta = idPlanta;
        this.hotelIdHotel = hotelIdHotel;
    }

    public Integer getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHotelIdHotel() {
        return hotelIdHotel;
    }

    public void setHotelIdHotel(int hotelIdHotel) {
        this.hotelIdHotel = hotelIdHotel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanta != null ? idPlanta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planta)) {
            return false;
        }
        Planta other = (Planta) object;
        if ((this.idPlanta == null && other.idPlanta != null) || (this.idPlanta != null && !this.idPlanta.equals(other.idPlanta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Planta[ idPlanta=" + idPlanta + " ]";
    }
    
}
