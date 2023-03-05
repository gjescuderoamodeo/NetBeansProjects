/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GuillermoJ
 */
@Entity
@Table(name = "alumno_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnoAsignatura.findAll", query = "SELECT a FROM AlumnoAsignatura a"),
    @NamedQuery(name = "AlumnoAsignatura.findByAlumnoIdalumno", query = "SELECT a FROM AlumnoAsignatura a WHERE a.alumnoAsignaturaPK.alumnoIdalumno = :alumnoIdalumno"),
    @NamedQuery(name = "AlumnoAsignatura.findByAsignaturaIdasignatura", query = "SELECT a FROM AlumnoAsignatura a WHERE a.alumnoAsignaturaPK.asignaturaIdasignatura = :asignaturaIdasignatura"),
    @NamedQuery(name = "AlumnoAsignatura.findByIdMatricula", query = "SELECT a FROM AlumnoAsignatura a WHERE a.alumnoAsignaturaPK.idMatricula = :idMatricula")})
public class AlumnoAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnoAsignaturaPK alumnoAsignaturaPK;
    @JoinColumn(name = "alumno_idalumno", referencedColumnName = "id_alumno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "asignatura_idasignatura", referencedColumnName = "id_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;

    public AlumnoAsignatura() {
    }

    public AlumnoAsignatura(AlumnoAsignaturaPK alumnoAsignaturaPK) {
        this.alumnoAsignaturaPK = alumnoAsignaturaPK;
    }

    public AlumnoAsignatura(int alumnoIdalumno, int asignaturaIdasignatura, String idMatricula) {
        this.alumnoAsignaturaPK = new AlumnoAsignaturaPK(alumnoIdalumno, asignaturaIdasignatura, idMatricula);
    }

    public AlumnoAsignaturaPK getAlumnoAsignaturaPK() {
        return alumnoAsignaturaPK;
    }

    public void setAlumnoAsignaturaPK(AlumnoAsignaturaPK alumnoAsignaturaPK) {
        this.alumnoAsignaturaPK = alumnoAsignaturaPK;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnoAsignaturaPK != null ? alumnoAsignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoAsignatura)) {
            return false;
        }
        AlumnoAsignatura other = (AlumnoAsignatura) object;
        if ((this.alumnoAsignaturaPK == null && other.alumnoAsignaturaPK != null) || (this.alumnoAsignaturaPK != null && !this.alumnoAsignaturaPK.equals(other.alumnoAsignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AlumnoAsignatura[ alumnoAsignaturaPK=" + alumnoAsignaturaPK + " ]";
    }
    
}
