package entidades;

import entidades.Ingrediente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-29T12:53:26")
@StaticMetamodel(Receta.class)
public class Receta_ { 

    public static volatile SingularAttribute<Receta, String> totalKcal;
    public static volatile ListAttribute<Receta, Ingrediente> ingredienteList;
    public static volatile SingularAttribute<Receta, Integer> idReceta;
    public static volatile SingularAttribute<Receta, String> nombre;

}