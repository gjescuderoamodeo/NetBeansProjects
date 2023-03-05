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
@Table(name = "alimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alimento.findAll", query = "SELECT a FROM Alimento a"),
    //@NamedQuery(name = "Alimento.ej5", query = "SELECT a FROM Alimento a, Ingrediente i, Receta r, IngredientePK ipk"),
    @NamedQuery(name = "Alimento.findByIdAlimento", query = "SELECT a FROM Alimento a WHERE a.idAlimento = :idAlimento"),
    @NamedQuery(name = "Alimento.findByNombre", query = "SELECT a FROM Alimento a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alimento.findByGrprot", query = "SELECT a FROM Alimento a WHERE a.grprot = :grprot"),
    @NamedQuery(name = "Alimento.findByGrgrasa", query = "SELECT a FROM Alimento a WHERE a.grgrasa = :grgrasa"),
    @NamedQuery(name = "Alimento.findByGrhidratos", query = "SELECT a FROM Alimento a WHERE a.grhidratos = :grhidratos")})
public class Alimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alimento")
    private Integer idAlimento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "grprot")
    private String grprot;
    @Basic(optional = false)
    @Column(name = "grgrasa")
    private String grgrasa;
    @Basic(optional = false)
    @Column(name = "grhidratos")
    private String grhidratos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alimento")
    private List<Ingrediente> ingredienteList;

    public Alimento() {
    }

    public Alimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public Alimento(Integer idAlimento, String nombre, String grprot, String grgrasa, String grhidratos) {
        this.idAlimento = idAlimento;
        this.nombre = nombre;
        this.grprot = grprot;
        this.grgrasa = grgrasa;
        this.grhidratos = grhidratos;
    }

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrprot() {
        return grprot;
    }

    public void setGrprot(String grprot) {
        this.grprot = grprot;
    }

    public String getGrgrasa() {
        return grgrasa;
    }

    public void setGrgrasa(String grgrasa) {
        this.grgrasa = grgrasa;
    }

    public String getGrhidratos() {
        return grhidratos;
    }

    public void setGrhidratos(String grhidratos) {
        this.grhidratos = grhidratos;
    }

    @XmlTransient
    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    public void setIngredienteList(List<Ingrediente> ingredienteList) {
        this.ingredienteList = ingredienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlimento != null ? idAlimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimento)) {
            return false;
        }
        Alimento other = (Alimento) object;
        if ((this.idAlimento == null && other.idAlimento != null) || (this.idAlimento != null && !this.idAlimento.equals(other.idAlimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Alimento[ idAlimento=" + idAlimento + " ]";
    }
    
}
