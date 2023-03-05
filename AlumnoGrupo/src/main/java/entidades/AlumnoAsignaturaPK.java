/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author GuillermoJ
 */
@Embeddable
public class AlumnoAsignaturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "alumno_idalumno")
    private int alumnoIdalumno;
    @Basic(optional = false)
    @Column(name = "asignatura_idasignatura")
    private int asignaturaIdasignatura;
    @Basic(optional = false)
    @Column(name = "id_matricula")
    private String idMatricula;

    public AlumnoAsignaturaPK() {
    }

    public AlumnoAsignaturaPK(int alumnoIdalumno, int asignaturaIdasignatura, String idMatricula) {
        this.alumnoIdalumno = alumnoIdalumno;
        this.asignaturaIdasignatura = asignaturaIdasignatura;
        this.idMatricula = idMatricula;
    }

    public int getAlumnoIdalumno() {
        return alumnoIdalumno;
    }

    public void setAlumnoIdalumno(int alumnoIdalumno) {
        this.alumnoIdalumno = alumnoIdalumno;
    }

    public int getAsignaturaIdasignatura() {
        return asignaturaIdasignatura;
    }

    public void setAsignaturaIdasignatura(int asignaturaIdasignatura) {
        this.asignaturaIdasignatura = asignaturaIdasignatura;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) alumnoIdalumno;
        hash += (int) asignaturaIdasignatura;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoAsignaturaPK)) {
            return false;
        }
        AlumnoAsignaturaPK other = (AlumnoAsignaturaPK) object;
        if (this.alumnoIdalumno != other.alumnoIdalumno) {
            return false;
        }
        if (this.asignaturaIdasignatura != other.asignaturaIdasignatura) {
            return false;
        }
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AlumnoAsignaturaPK[ alumnoIdalumno=" + alumnoIdalumno + ", asignaturaIdasignatura=" + asignaturaIdasignatura + ", idMatricula=" + idMatricula + " ]";
    }
    
}
