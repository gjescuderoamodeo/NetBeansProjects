/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIdAsignatura", query = "SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private Integer idAsignatura;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<AlumnoAsignatura> alumnoAsignaturaList;

    public Asignatura() {
    }

    public Asignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Asignatura(Integer idAsignatura, String nombre) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<AlumnoAsignatura> getAlumnoAsignaturaList() {
        return alumnoAsignaturaList;
    }

    public void setAlumnoAsignaturaList(List<AlumnoAsignatura> alumnoAsignaturaList) {
        this.alumnoAsignaturaList = alumnoAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asignatura[ idAsignatura=" + idAsignatura + " ]";
    }
    
}
