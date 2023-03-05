package entidades;

import entidades.Ruta;
import javax.annotation.Generated;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-15T12:41:21")
@StaticMetamodel(Lugar.class)
public class Lugar_ { 

    public static volatile CollectionAttribute<Lugar, Ruta> rutaCollection1;
    public static volatile SingularAttribute<Lugar, Integer> idLugar;
    public static volatile SingularAttribute<Lugar, String> nombre;
    public static volatile CollectionAttribute<Lugar, Ruta> rutaCollection;
    public static volatile SingularAttribute<Lugar, EntityManagerFactory> emf;

}