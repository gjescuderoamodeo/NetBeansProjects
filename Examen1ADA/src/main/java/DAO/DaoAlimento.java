/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexionDB.ConexionBD;
import entidades.Alimento;
import entidades.Ingrediente;
import entidades.Receta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author enrique
 */


public class DaoAlimento implements InterfaceDaoAlimento{

    private ConexionBD conexion;

    public DaoAlimento(){
       conexion=new ConexionBD("mysql","examen_ada","usuario","usuario");
    }
    
    
    @Override
    public boolean insertarAlimento(String nombre,int proteinas, int grasas, float hidratos) {
        boolean resultado=false;
        String sql="insert into alimento (nombre,grprot,grgrasa,grhidratos) values ('"+nombre+"','"+proteinas+"','"+grasas+"','"+hidratos+"')";    //JDBC
        
        try{
            conexion.abrirConexion();
            resultado=conexion.ejecutarUpdate(sql);
                      
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            return false;
        }
         return resultado;
    }
        
    
    //jpa
    private EntityManagerFactory emf = null;
   
    public DaoAlimento(EntityManagerFactory emf) {
        this.emf = emf;
    }
   

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public List<Alimento> obtenerAlimento() {
        
        EntityManager em=this.getEntityManager();
        
        TypedQuery <Alimento> consulta=em.createNamedQuery("Alimento.findAll",Alimento.class);
       
        
        
        return (List<Alimento>)consulta.getResultList();
      

    }

    //JPA
    @Override
    public void ej5(String nombreReceta) {      
        
        try{
        EntityManager em=this.getEntityManager();
        TypedQuery <Receta> consulta=em.createNamedQuery("Receta.findAll",Receta.class);
        List<Receta> recetas = (List<Receta>)consulta.getResultList();
        int idReceta=0;
        
        for(int i=0;i<recetas.size();i++){
            if(recetas.get(i).getNombre().equalsIgnoreCase(nombreReceta)){
                idReceta=recetas.get(i).getIdReceta();
            }
        }
        
        TypedQuery<Ingrediente> query = em.createQuery(
        "SELECT i FROM Ingrediente i WHERE i.ingredientePK.idReceta = :idreceta" , Ingrediente.class);
        List<Ingrediente> ingredientes = query
        .setParameter("idreceta", idReceta)
        .getResultList();
        
        /*TypedQuery<Alimento> query2 = em.createQuery(
        "SELECT a FROM Alimento a WHERE i.ingredientePK.idReceta = :idreceta" , Alimento.class);
        List<Alimento> alimentos = query
        .setParameter("idreceta", idReceta)
        .getResultList();*/
        
        for(int i=0;i<ingredientes.size();i++){
            System.out.println("cantidades: " + ingredientes.get(i).getCantidad());
        } 
        
        
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
        }
        
    }
    
    
    
    
}
