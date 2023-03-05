package entidades;

import entidades.Lugar;
import entidades.RutaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-15T12:41:21")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, String> descripcion;
    public static volatile SingularAttribute<Ruta, RutaPK> rutaPK;
    public static volatile SingularAttribute<Ruta, Integer> distanciaReal;
    public static volatile SingularAttribute<Ruta, Lugar> lugar;
    public static volatile SingularAttribute<Ruta, Integer> distanciaGeometrica;
    public static volatile SingularAttribute<Ruta, String> nombre;
    public static volatile SingularAttribute<Ruta, Lugar> lugar1;

}