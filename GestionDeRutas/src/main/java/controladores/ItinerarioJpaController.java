/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Itinerario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Ruta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GuillermoJ
 */
public class ItinerarioJpaController implements Serializable {

    public ItinerarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itinerario itinerario) throws PreexistingEntityException, Exception {
        if (itinerario.getRutaList() == null) {
            itinerario.setRutaList(new ArrayList<Ruta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ruta> attachedRutaList = new ArrayList<Ruta>();
            for (Ruta rutaListRutaToAttach : itinerario.getRutaList()) {
                rutaListRutaToAttach = em.getReference(rutaListRutaToAttach.getClass(), rutaListRutaToAttach.getIdRuta());
                attachedRutaList.add(rutaListRutaToAttach);
            }
            itinerario.setRutaList(attachedRutaList);
            em.persist(itinerario);
            for (Ruta rutaListRuta : itinerario.getRutaList()) {
                rutaListRuta.getItinerarioList().add(itinerario);
                rutaListRuta = em.merge(rutaListRuta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItinerario(itinerario.getIdItinerario()) != null) {
                throw new PreexistingEntityException("Itinerario " + itinerario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itinerario itinerario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itinerario persistentItinerario = em.find(Itinerario.class, itinerario.getIdItinerario());
            List<Ruta> rutaListOld = persistentItinerario.getRutaList();
            List<Ruta> rutaListNew = itinerario.getRutaList();
            List<Ruta> attachedRutaListNew = new ArrayList<Ruta>();
            for (Ruta rutaListNewRutaToAttach : rutaListNew) {
                rutaListNewRutaToAttach = em.getReference(rutaListNewRutaToAttach.getClass(), rutaListNewRutaToAttach.getIdRuta());
                attachedRutaListNew.add(rutaListNewRutaToAttach);
            }
            rutaListNew = attachedRutaListNew;
            itinerario.setRutaList(rutaListNew);
            itinerario = em.merge(itinerario);
            for (Ruta rutaListOldRuta : rutaListOld) {
                if (!rutaListNew.contains(rutaListOldRuta)) {
                    rutaListOldRuta.getItinerarioList().remove(itinerario);
                    rutaListOldRuta = em.merge(rutaListOldRuta);
                }
            }
            for (Ruta rutaListNewRuta : rutaListNew) {
                if (!rutaListOld.contains(rutaListNewRuta)) {
                    rutaListNewRuta.getItinerarioList().add(itinerario);
                    rutaListNewRuta = em.merge(rutaListNewRuta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itinerario.getIdItinerario();
                if (findItinerario(id) == null) {
                    throw new NonexistentEntityException("The itinerario with id " + id + " no longer exists.");
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
            Itinerario itinerario;
            try {
                itinerario = em.getReference(Itinerario.class, id);
                itinerario.getIdItinerario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itinerario with id " + id + " no longer exists.", enfe);
            }
            List<Ruta> rutaList = itinerario.getRutaList();
            for (Ruta rutaListRuta : rutaList) {
                rutaListRuta.getItinerarioList().remove(itinerario);
                rutaListRuta = em.merge(rutaListRuta);
            }
            em.remove(itinerario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itinerario> findItinerarioEntities() {
        return findItinerarioEntities(true, -1, -1);
    }

    public List<Itinerario> findItinerarioEntities(int maxResults, int firstResult) {
        return findItinerarioEntities(false, maxResults, firstResult);
    }

    private List<Itinerario> findItinerarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itinerario.class));
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

    public Itinerario findItinerario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itinerario.class, id);
        } finally {
            em.close();
        }
    }

    public int getItinerarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itinerario> rt = cq.from(Itinerario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
