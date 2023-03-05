package entidades;

import entidades.Alimento;
import entidades.IngredientePK;
import entidades.Receta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-29T12:53:26")
@StaticMetamodel(Ingrediente.class)
public class Ingrediente_ { 

    public static volatile SingularAttribute<Ingrediente, IngredientePK> ingredientePK;
    public static volatile SingularAttribute<Ingrediente, Receta> receta;
    public static volatile SingularAttribute<Ingrediente, String> cantidad;
    public static volatile SingularAttribute<Ingrediente, Alimento> alimento;

}