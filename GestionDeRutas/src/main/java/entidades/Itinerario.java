/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GuillermoJ
 */
@Entity
@Table(name = "itinerario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itinerario.findAll", query = "SELECT i FROM Itinerario i"),
    @NamedQuery(name = "Itinerario.findByIdItinerario", query = "SELECT i FROM Itinerario i WHERE i.idItinerario = :idItinerario")})
public class Itinerario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_itinerario")
    private Integer idItinerario;
    @JoinTable(name = "itinerario_ruta", joinColumns = {
        @JoinColumn(name = "id_itinerario", referencedColumnName = "id_itinerario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")})
    @ManyToMany
    private List<Ruta> rutaList;

    public Itinerario() {
    }

    public Itinerario(Integer idItinerario) {
        this.idItinerario = idItinerario;
    }

    public Integer getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(Integer idItinerario) {
        this.idItinerario = idItinerario;
    }

    @XmlTransient
    public List<Ruta> getRutaList() {
        return rutaList;
    }

    public void setRutaList(List<Ruta> rutaList) {
        this.rutaList = rutaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItinerario != null ? idItinerario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerario)) {
            return false;
        }
        Itinerario other = (Itinerario) object;
        if ((this.idItinerario == null && other.idItinerario != null) || (this.idItinerario != null && !this.idItinerario.equals(other.idItinerario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Itinerario[ idItinerario=" + idItinerario + " ]";
    }
    
}
