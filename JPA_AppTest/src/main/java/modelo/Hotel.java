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
@Table(name = "hotel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findByIdHotel", query = "SELECT h FROM Hotel h WHERE h.idHotel = :idHotel"),
    @NamedQuery(name = "Hotel.findByNombre", query = "SELECT h FROM Hotel h WHERE h.nombre = :nombre")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_hotel")
    private Integer idHotel;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    public Hotel() {
    }

    public Hotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Hotel(Integer idHotel, String nombre) {
        this.idHotel = idHotel;
        this.nombre = nombre;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHotel != null ? idHotel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.idHotel == null && other.idHotel != null) || (this.idHotel != null && !this.idHotel.equals(other.idHotel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Hotel[ idHotel=" + idHotel + " ]";
    }
    
}
