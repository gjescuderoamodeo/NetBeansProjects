/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.HotelServicio;
import modelo.HotelServicioPK;

/**
 *
 * @author GuillermoJ
 */
public class HotelServicioJpaController implements Serializable {

    public HotelServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HotelServicio hotelServicio) throws PreexistingEntityException, Exception {
        if (hotelServicio.getHotelServicioPK() == null) {
            hotelServicio.setHotelServicioPK(new HotelServicioPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hotelServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHotelServicio(hotelServicio.getHotelServicioPK()) != null) {
                throw new PreexistingEntityException("HotelServicio " + hotelServicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HotelServicio hotelServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hotelServicio = em.merge(hotelServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HotelServicioPK id = hotelServicio.getHotelServicioPK();
                if (findHotelServicio(id) == null) {
                    throw new NonexistentEntityException("The hotelServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HotelServicioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HotelServicio hotelServicio;
            try {
                hotelServicio = em.getReference(HotelServicio.class, id);
                hotelServicio.getHotelServicioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hotelServicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(hotelServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HotelServicio> findHotelServicioEntities() {
        return findHotelServicioEntities(true, -1, -1);
    }

    public List<HotelServicio> findHotelServicioEntities(int maxResults, int firstResult) {
        return findHotelServicioEntities(false, maxResults, firstResult);
    }

    private List<HotelServicio> findHotelServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HotelServicio.class));
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

    public HotelServicio findHotelServicio(HotelServicioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HotelServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getHotelServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HotelServicio> rt = cq.from(HotelServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
