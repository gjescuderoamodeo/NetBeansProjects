package entidades;

import entidades.Ingrediente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-29T12:53:26")
@StaticMetamodel(Alimento.class)
public class Alimento_ { 

    public static volatile SingularAttribute<Alimento, String> grgrasa;
    public static volatile SingularAttribute<Alimento, String> grhidratos;
    public static volatile ListAttribute<Alimento, Ingrediente> ingredienteList;
    public static volatile SingularAttribute<Alimento, Integer> idAlimento;
    public static volatile SingularAttribute<Alimento, String> grprot;
    public static volatile SingularAttribute<Alimento, String> nombre;

}