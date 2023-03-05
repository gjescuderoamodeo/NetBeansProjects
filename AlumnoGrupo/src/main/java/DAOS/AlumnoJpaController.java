/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import DAOS.exceptions.IllegalOrphanException;
import DAOS.exceptions.NonexistentEntityException;
import entidades.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Grupo;
import entidades.AlumnoAsignatura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author GuillermoJ
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        if (alumno.getAlumnoAsignaturaList() == null) {
            alumno.setAlumnoAsignaturaList(new ArrayList<AlumnoAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo idGrupo = alumno.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdGrupo());
                alumno.setIdGrupo(idGrupo);
            }
            List<AlumnoAsignatura> attachedAlumnoAsignaturaList = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignaturaToAttach : alumno.getAlumnoAsignaturaList()) {
                alumnoAsignaturaListAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListAlumnoAsignaturaToAttach.getAlumnoAsignaturaPK());
                attachedAlumnoAsignaturaList.add(alumnoAsignaturaListAlumnoAsignaturaToAttach);
            }
            alumno.setAlumnoAsignaturaList(attachedAlumnoAsignaturaList);
            em.persist(alumno);
            if (idGrupo != null) {
                idGrupo.getAlumnoList().add(alumno);
                idGrupo = em.merge(idGrupo);
            }
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : alumno.getAlumnoAsignaturaList()) {
                Alumno oldAlumnoOfAlumnoAsignaturaListAlumnoAsignatura = alumnoAsignaturaListAlumnoAsignatura.getAlumno();
                alumnoAsignaturaListAlumnoAsignatura.setAlumno(alumno);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
                if (oldAlumnoOfAlumnoAsignaturaListAlumnoAsignatura != null) {
                    oldAlumnoOfAlumnoAsignaturaListAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListAlumnoAsignatura);
                    oldAlumnoOfAlumnoAsignaturaListAlumnoAsignatura = em.merge(oldAlumnoOfAlumnoAsignaturaListAlumnoAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getIdAlumno());
            Grupo idGrupoOld = persistentAlumno.getIdGrupo();
            Grupo idGrupoNew = alumno.getIdGrupo();
            List<AlumnoAsignatura> alumnoAsignaturaListOld = persistentAlumno.getAlumnoAsignaturaList();
            List<AlumnoAsignatura> alumnoAsignaturaListNew = alumno.getAlumnoAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (AlumnoAsignatura alumnoAsignaturaListOldAlumnoAsignatura : alumnoAsignaturaListOld) {
                if (!alumnoAsignaturaListNew.contains(alumnoAsignaturaListOldAlumnoAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AlumnoAsignatura " + alumnoAsignaturaListOldAlumnoAsignatura + " since its alumno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdGrupo());
                alumno.setIdGrupo(idGrupoNew);
            }
            List<AlumnoAsignatura> attachedAlumnoAsignaturaListNew = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignaturaToAttach : alumnoAsignaturaListNew) {
                alumnoAsignaturaListNewAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getAlumnoAsignaturaPK());
                attachedAlumnoAsignaturaListNew.add(alumnoAsignaturaListNewAlumnoAsignaturaToAttach);
            }
            alumnoAsignaturaListNew = attachedAlumnoAsignaturaListNew;
            alumno.setAlumnoAsignaturaList(alumnoAsignaturaListNew);
            alumno = em.merge(alumno);
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getAlumnoList().remove(alumno);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getAlumnoList().add(alumno);
                idGrupoNew = em.merge(idGrupoNew);
            }
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignatura : alumnoAsignaturaListNew) {
                if (!alumnoAsignaturaListOld.contains(alumnoAsignaturaListNewAlumnoAsignatura)) {
                    Alumno oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura = alumnoAsignaturaListNewAlumnoAsignatura.getAlumno();
                    alumnoAsignaturaListNewAlumnoAsignatura.setAlumno(alumno);
                    alumnoAsignaturaListNewAlumnoAsignatura = em.merge(alumnoAsignaturaListNewAlumnoAsignatura);
                    if (oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura != null && !oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura.equals(alumno)) {
                        oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListNewAlumnoAsignatura);
                        oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura = em.merge(oldAlumnoOfAlumnoAsignaturaListNewAlumnoAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alumno.getIdAlumno();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
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
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getIdAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AlumnoAsignatura> alumnoAsignaturaListOrphanCheck = alumno.getAlumnoAsignaturaList();
            for (AlumnoAsignatura alumnoAsignaturaListOrphanCheckAlumnoAsignatura : alumnoAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the AlumnoAsignatura " + alumnoAsignaturaListOrphanCheckAlumnoAsignatura + " in its alumnoAsignaturaList field has a non-nullable alumno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Grupo idGrupo = alumno.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getAlumnoList().remove(alumno);
                idGrupo = em.merge(idGrupo);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
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

    public Alumno findAlumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
