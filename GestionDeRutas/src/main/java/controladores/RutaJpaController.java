/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.mycompany.gestionderutas.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Lugar;
import entidades.Ruta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GuillermoJ
 */
public class RutaJpaController implements Serializable {

    public RutaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ruta ruta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lugar origen = ruta.getOrigen();
            if (origen != null) {
                origen = em.getReference(origen.getClass(), origen.getIdLugar());
                ruta.setOrigen(origen);
            }
            Lugar destino = ruta.getDestino();
            if (destino != null) {
                destino = em.getReference(destino.getClass(), destino.getIdLugar());
                ruta.setDestino(destino);
            }
            em.persist(ruta);
            if (origen != null) {
                origen.getRutaList().add(ruta);
                origen = em.merge(origen);
            }
            if (destino != null) {
                destino.getRutaList().add(ruta);
                destino = em.merge(destino);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ruta ruta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta persistentRuta = em.find(Ruta.class, ruta.getIdRuta());
            Lugar origenOld = persistentRuta.getOrigen();
            Lugar origenNew = ruta.getOrigen();
            Lugar destinoOld = persistentRuta.getDestino();
            Lugar destinoNew = ruta.getDestino();
            if (origenNew != null) {
                origenNew = em.getReference(origenNew.getClass(), origenNew.getIdLugar());
                ruta.setOrigen(origenNew);
            }
            if (destinoNew != null) {
                destinoNew = em.getReference(destinoNew.getClass(), destinoNew.getIdLugar());
                ruta.setDestino(destinoNew);
            }
            ruta = em.merge(ruta);
            if (origenOld != null && !origenOld.equals(origenNew)) {
                origenOld.getRutaList().remove(ruta);
                origenOld = em.merge(origenOld);
            }
            if (origenNew != null && !origenNew.equals(origenOld)) {
                origenNew.getRutaList().add(ruta);
                origenNew = em.merge(origenNew);
            }
            if (destinoOld != null && !destinoOld.equals(destinoNew)) {
                destinoOld.getRutaList().remove(ruta);
                destinoOld = em.merge(destinoOld);
            }
            if (destinoNew != null && !destinoNew.equals(destinoOld)) {
                destinoNew.getRutaList().add(ruta);
                destinoNew = em.merge(destinoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ruta.getIdRuta();
                if (findRuta(id) == null) {
                    throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta ruta;
            try {
                ruta = em.getReference(Ruta.class, id);
                ruta.getIdRuta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.", enfe);
            }
            Lugar origen = ruta.getOrigen();
            if (origen != null) {
                origen.getRutaList().remove(ruta);
                origen = em.merge(origen);
            }
            Lugar destino = ruta.getDestino();
            if (destino != null) {
                destino.getRutaList().remove(ruta);
                destino = em.merge(destino);
            }
            em.remove(ruta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ruta> findRutaEntities() {
        return findRutaEntities(true, -1, -1);
    }

    public List<Ruta> findRutaEntities(int maxResults, int firstResult) {
        return findRutaEntities(false, maxResults, firstResult);
    }

    private List<Ruta> findRutaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ruta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ruta findRuta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ruta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ruta> rt = cq.from(Ruta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
