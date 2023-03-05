/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.AlumnoAsignatura;
import entidades.Asignatura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GuillermoJ
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getAlumnoAsignaturaList() == null) {
            asignatura.setAlumnoAsignaturaList(new ArrayList<AlumnoAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AlumnoAsignatura> attachedAlumnoAsignaturaList = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignaturaToAttach : asignatura.getAlumnoAsignaturaList()) {
                alumnoAsignaturaListAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListAlumnoAsignaturaToAttach.getAlumnoAsignaturaPK());
                attachedAlumnoAsignaturaList.add(alumnoAsignaturaListAlumnoAsignaturaToAttach);
            }
            asignatura.setAlumnoAsignaturaList(attachedAlumnoAsignaturaList);
            em.persist(asignatura);
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : asignatura.getAlumnoAsignaturaList()) {
                Asignatura oldAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = alumnoAsignaturaListAlumnoAsignatura.getAsignatura();
                alumnoAsignaturaListAlumnoAsignatura.setAsignatura(asignatura);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
                if (oldAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura != null) {
                    oldAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListAlumnoAsignatura);
                    oldAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = em.merge(oldAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getIdAsignatura());
            List<AlumnoAsignatura> alumnoAsignaturaListOld = persistentAsignatura.getAlumnoAsignaturaList();
            List<AlumnoAsignatura> alumnoAsignaturaListNew = asignatura.getAlumnoAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (AlumnoAsignatura alumnoAsignaturaListOldAlumnoAsignatura : alumnoAsignaturaListOld) {
                if (!alumnoAsignaturaListNew.contains(alumnoAsignaturaListOldAlumnoAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AlumnoAsignatura " + alumnoAsignaturaListOldAlumnoAsignatura + " since its asignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AlumnoAsignatura> attachedAlumnoAsignaturaListNew = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignaturaToAttach : alumnoAsignaturaListNew) {
                alumnoAsignaturaListNewAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getAlumnoAsignaturaPK());
                attachedAlumnoAsignaturaListNew.add(alumnoAsignaturaListNewAlumnoAsignaturaToAttach);
            }
            alumnoAsignaturaListNew = attachedAlumnoAsignaturaListNew;
            asignatura.setAlumnoAsignaturaList(alumnoAsignaturaListNew);
            asignatura = em.merge(asignatura);
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignatura : alumnoAsignaturaListNew) {
                if (!alumnoAsignaturaListOld.contains(alumnoAsignaturaListNewAlumnoAsignatura)) {
                    Asignatura oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = alumnoAsignaturaListNewAlumnoAsignatura.getAsignatura();
                    alumnoAsignaturaListNewAlumnoAsignatura.setAsignatura(asignatura);
                    alumnoAsignaturaListNewAlumnoAsignatura = em.merge(alumnoAsignaturaListNewAlumnoAsignatura);
                    if (oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura != null && !oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.equals(asignatura)) {
                        oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListNewAlumnoAsignatura);
                        oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = em.merge(oldAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getIdAsignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getIdAsignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AlumnoAsignatura> alumnoAsignaturaListOrphanCheck = asignatura.getAlumnoAsignaturaList();
            for (AlumnoAsignatura alumnoAsignaturaListOrphanCheckAlumnoAsignatura : alumnoAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the AlumnoAsignatura " + alumnoAsignaturaListOrphanCheckAlumnoAsignatura + " in its alumnoAsignaturaList field has a non-nullable asignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
