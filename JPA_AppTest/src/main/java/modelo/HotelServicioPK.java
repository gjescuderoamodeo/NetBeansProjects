/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author GuillermoJ
 */
@Embeddable
public class HotelServicioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_hotel")
    private int idHotel;
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private int idServicio;

    public HotelServicioPK() {
    }

    public HotelServicioPK(int idHotel, int idServicio) {
        this.idHotel = idHotel;
        this.idServicio = idServicio;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHotel;
        hash += (int) idServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelServicioPK)) {
            return false;
        }
        HotelServicioPK other = (HotelServicioPK) object;
        if (this.idHotel != other.idHotel) {
            return false;
        }
        if (this.idServicio != other.idServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HotelServicioPK[ idHotel=" + idHotel + ", idServicio=" + idServicio + " ]";
    }
    
}
