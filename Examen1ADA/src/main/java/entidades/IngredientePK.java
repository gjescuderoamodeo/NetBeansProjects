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
public class IngredientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_ingrediente")
    private int idIngrediente;
    @Basic(optional = false)
    @Column(name = "id_alimento")
    private int idAlimento;
    @Basic(optional = false)
    @Column(name = "id_receta")
    private int idReceta;

    public IngredientePK() {
    }

    public IngredientePK(int idIngrediente, int idAlimento, int idReceta) {
        this.idIngrediente = idIngrediente;
        this.idAlimento = idAlimento;
        this.idReceta = idReceta;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idIngrediente;
        hash += (int) idAlimento;
        hash += (int) idReceta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngredientePK)) {
            return false;
        }
        IngredientePK other = (IngredientePK) object;
        if (this.idIngrediente != other.idIngrediente) {
            return false;
        }
        if (this.idAlimento != other.idAlimento) {
            return false;
        }
        if (this.idReceta != other.idReceta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.IngredientePK[ idIngrediente=" + idIngrediente + ", idAlimento=" + idAlimento + ", idReceta=" + idReceta + " ]";
    }
    
}
