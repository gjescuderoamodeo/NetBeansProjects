/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "ingrediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingrediente.findAll", query = "SELECT i FROM Ingrediente i"),
    @NamedQuery(name = "Ingrediente.findByIdIngrediente", query = "SELECT i FROM Ingrediente i WHERE i.ingredientePK.idIngrediente = :idIngrediente"),
    @NamedQuery(name = "Ingrediente.findByIdAlimento", query = "SELECT i FROM Ingrediente i WHERE i.ingredientePK.idAlimento = :idAlimento"),
    @NamedQuery(name = "Ingrediente.findByIdReceta", query = "SELECT i FROM Ingrediente i WHERE i.ingredientePK.idReceta = :idReceta"),
    @NamedQuery(name = "Ingrediente.findByCantidad", query = "SELECT i FROM Ingrediente i WHERE i.cantidad = :cantidad")})
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IngredientePK ingredientePK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private String cantidad;
    @JoinColumn(name = "id_alimento", referencedColumnName = "id_alimento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alimento alimento;
    @JoinColumn(name = "id_receta", referencedColumnName = "id_receta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public Ingrediente() {
    }

    public Ingrediente(IngredientePK ingredientePK) {
        this.ingredientePK = ingredientePK;
    }

    public Ingrediente(IngredientePK ingredientePK, String cantidad) {
        this.ingredientePK = ingredientePK;
        this.cantidad = cantidad;
    }

    public Ingrediente(int idIngrediente, int idAlimento, int idReceta) {
        this.ingredientePK = new IngredientePK(idIngrediente, idAlimento, idReceta);
    }

    public IngredientePK getIngredientePK() {
        return ingredientePK;
    }

    public void setIngredientePK(IngredientePK ingredientePK) {
        this.ingredientePK = ingredientePK;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientePK != null ? ingredientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.ingredientePK == null && other.ingredientePK != null) || (this.ingredientePK != null && !this.ingredientePK.equals(other.ingredientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ingrediente[ ingredientePK=" + ingredientePK + " ]";
    }
    
}
