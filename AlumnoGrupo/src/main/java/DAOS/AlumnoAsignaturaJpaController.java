/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import DAOS.exceptions.NonexistentEntityException;
import DAOS.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Alumno;
import entidades.AlumnoAsignatura;
import entidades.AlumnoAsignaturaPK;
import entidades.Asignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GuillermoJ
 */
public class AlumnoAsignaturaJpaController implements Serializable {

    public AlumnoAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlumnoAsignatura alumnoAsignatura) throws PreexistingEntityException, Exception {
        if (alumnoAsignatura.getAlumnoAsignaturaPK() == null) {
            alumnoAsignatura.setAlumnoAsignaturaPK(new AlumnoAsignaturaPK());
        }
        alumnoAsignatura.getAlumnoAsignaturaPK().setAsignaturaIdasignatura(alumnoAsignatura.getAsignatura().getIdAsignatura());
        alumnoAsignatura.getAlumnoAsignaturaPK().setAlumnoIdalumno(alumnoAsignatura.getAlumno().getIdAlumno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = alumnoAsignatura.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getIdAlumno());
                alumnoAsignatura.setAlumno(alumno);
            }
            Asignatura asignatura = alumnoAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getIdAsignatura());
                alumnoAsignatura.setAsignatura(asignatura);
            }
            em.persist(alumnoAsignatura);
            if (alumno != null) {
                alumno.getAlumnoAsignaturaList().add(alumnoAsignatura);
                alumno = em.merge(alumno);
            }
            if (asignatura != null) {
                asignatura.getAlumnoAsignaturaList().add(alumnoAsignatura);
                asignatura = em.merge(asignatura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumnoAsignatura(alumnoAsignatura.getAlumnoAsignaturaPK()) != null) {
                throw new PreexistingEntityException("AlumnoAsignatura " + alumnoAsignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlumnoAsignatura alumnoAsignatura) throws NonexistentEntityException, Exception {
        alumnoAsignatura.getAlumnoAsignaturaPK().setAsignaturaIdasignatura(alumnoAsignatura.getAsignatura().getIdAsignatura());
        alumnoAsignatura.getAlumnoAsignaturaPK().setAlumnoIdalumno(alumnoAsignatura.getAlumno().getIdAlumno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoAsignatura persistentAlumnoAsignatura = em.find(AlumnoAsignatura.class, alumnoAsignatura.getAlumnoAsignaturaPK());
            Alumno alumnoOld = persistentAlumnoAsignatura.getAlumno();
            Alumno alumnoNew = alumnoAsignatura.getAlumno();
            Asignatura asignaturaOld = persistentAlumnoAsignatura.getAsignatura();
            Asignatura asignaturaNew = alumnoAsignatura.getAsignatura();
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getIdAlumno());
                alumnoAsignatura.setAlumno(alumnoNew);
            }
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getIdAsignatura());
                alumnoAsignatura.setAsignatura(asignaturaNew);
            }
            alumnoAsignatura = em.merge(alumnoAsignatura);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getAlumnoAsignaturaList().add(alumnoAsignatura);
                alumnoNew = em.merge(alumnoNew);
            }
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getAlumnoAsignaturaList().add(alumnoAsignatura);
                asignaturaNew = em.merge(asignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AlumnoAsignaturaPK id = alumnoAsignatura.getAlumnoAsignaturaPK();
                if (findAlumnoAsignatura(id) == null) {
                    throw new NonexistentEntityException("The alumnoAsignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AlumnoAsignaturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoAsignatura alumnoAsignatura;
            try {
                alumnoAsignatura = em.getReference(AlumnoAsignatura.class, id);
                alumnoAsignatura.getAlumnoAsignaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumnoAsignatura with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = alumnoAsignatura.getAlumno();
            if (alumno != null) {
                alumno.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                alumno = em.merge(alumno);
            }
            Asignatura asignatura = alumnoAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura.getAlumnoAsignaturaList().remove(alumnoAsignatura);
                asignatura = em.merge(asignatura);
            }
            em.remove(alumnoAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlumnoAsignatura> findAlumnoAsignaturaEntities() {
        return findAlumnoAsignaturaEntities(true, -1, -1);
    }

    public List<AlumnoAsignatura> findAlumnoAsignaturaEntities(int maxResults, int firstResult) {
        return findAlumnoAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<AlumnoAsignatura> findAlumnoAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AlumnoAsignatura.class));
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

    public AlumnoAsignatura findAlumnoAsignatura(AlumnoAsignaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlumnoAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AlumnoAsignatura> rt = cq.from(AlumnoAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
