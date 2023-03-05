/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GuillermoJ
 */
@Entity
@Table(name = "hotel_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelServicio.findAll", query = "SELECT h FROM HotelServicio h"),
    @NamedQuery(name = "HotelServicio.findByIdHotel", query = "SELECT h FROM HotelServicio h WHERE h.hotelServicioPK.idHotel = :idHotel"),
    @NamedQuery(name = "HotelServicio.findByIdServicio", query = "SELECT h FROM HotelServicio h WHERE h.hotelServicioPK.idServicio = :idServicio")})
public class HotelServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HotelServicioPK hotelServicioPK;

    public HotelServicio() {
    }

    public HotelServicio(HotelServicioPK hotelServicioPK) {
        this.hotelServicioPK = hotelServicioPK;
    }

    public HotelServicio(int idHotel, int idServicio) {
        this.hotelServicioPK = new HotelServicioPK(idHotel, idServicio);
    }

    public HotelServicioPK getHotelServicioPK() {
        return hotelServicioPK;
    }

    public void setHotelServicioPK(HotelServicioPK hotelServicioPK) {
        this.hotelServicioPK = hotelServicioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelServicioPK != null ? hotelServicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelServicio)) {
            return false;
        }
        HotelServicio other = (HotelServicio) object;
        if ((this.hotelServicioPK == null && other.hotelServicioPK != null) || (this.hotelServicioPK != null && !this.hotelServicioPK.equals(other.hotelServicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HotelServicio[ hotelServicioPK=" + hotelServicioPK + " ]";
    }
    
}
