/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import com.mycompany.gestionderutas.exceptions.IllegalOrphanException;
import com.mycompany.gestionderutas.exceptions.NonexistentEntityException;
import entidades.Lugar;
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
public class LugarJpaController implements Serializable {

    public LugarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lugar lugar) {
        if (lugar.getRutaList() == null) {
            lugar.setRutaList(new ArrayList<Ruta>());
        }
        if (lugar.getRutaList1() == null) {
            lugar.setRutaList1(new ArrayList<Ruta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ruta> attachedRutaList = new ArrayList<Ruta>();
            for (Ruta rutaListRutaToAttach : lugar.getRutaList()) {
                rutaListRutaToAttach = em.getReference(rutaListRutaToAttach.getClass(), rutaListRutaToAttach.getIdRuta());
                attachedRutaList.add(rutaListRutaToAttach);
            }
            lugar.setRutaList(attachedRutaList);
            List<Ruta> attachedRutaList1 = new ArrayList<Ruta>();
            for (Ruta rutaList1RutaToAttach : lugar.getRutaList1()) {
                rutaList1RutaToAttach = em.getReference(rutaList1RutaToAttach.getClass(), rutaList1RutaToAttach.getIdRuta());
                attachedRutaList1.add(rutaList1RutaToAttach);
            }
            lugar.setRutaList1(attachedRutaList1);
            em.persist(lugar);
            for (Ruta rutaListRuta : lugar.getRutaList()) {
                Lugar oldOrigenOfRutaListRuta = rutaListRuta.getOrigen();
                rutaListRuta.setOrigen(lugar);
                rutaListRuta = em.merge(rutaListRuta);
                if (oldOrigenOfRutaListRuta != null) {
                    oldOrigenOfRutaListRuta.getRutaList().remove(rutaListRuta);
                    oldOrigenOfRutaListRuta = em.merge(oldOrigenOfRutaListRuta);
                }
            }
            for (Ruta rutaList1Ruta : lugar.getRutaList1()) {
                Lugar oldDestinoOfRutaList1Ruta = rutaList1Ruta.getDestino();
                rutaList1Ruta.setDestino(lugar);
                rutaList1Ruta = em.merge(rutaList1Ruta);
                if (oldDestinoOfRutaList1Ruta != null) {
                    oldDestinoOfRutaList1Ruta.getRutaList1().remove(rutaList1Ruta);
                    oldDestinoOfRutaList1Ruta = em.merge(oldDestinoOfRutaList1Ruta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lugar lugar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lugar persistentLugar = em.find(Lugar.class, lugar.getIdLugar());
            List<Ruta> rutaListOld = persistentLugar.getRutaList();
            List<Ruta> rutaListNew = lugar.getRutaList();
            List<Ruta> rutaList1Old = persistentLugar.getRutaList1();
            List<Ruta> rutaList1New = lugar.getRutaList1();
            List<String> illegalOrphanMessages = null;
            for (Ruta rutaListOldRuta : rutaListOld) {
                if (!rutaListNew.contains(rutaListOldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaListOldRuta + " since its origen field is not nullable.");
                }
            }
            for (Ruta rutaList1OldRuta : rutaList1Old) {
                if (!rutaList1New.contains(rutaList1OldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaList1OldRuta + " since its destino field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ruta> attachedRutaListNew = new ArrayList<Ruta>();
            for (Ruta rutaListNewRutaToAttach : rutaListNew) {
                rutaListNewRutaToAttach = em.getReference(rutaListNewRutaToAttach.getClass(), rutaListNewRutaToAttach.getIdRuta());
                attachedRutaListNew.add(rutaListNewRutaToAttach);
            }
            rutaListNew = attachedRutaListNew;
            lugar.setRutaList(rutaListNew);
            List<Ruta> attachedRutaList1New = new ArrayList<Ruta>();
            for (Ruta rutaList1NewRutaToAttach : rutaList1New) {
                rutaList1NewRutaToAttach = em.getReference(rutaList1NewRutaToAttach.getClass(), rutaList1NewRutaToAttach.getIdRuta());
                attachedRutaList1New.add(rutaList1NewRutaToAttach);
            }
            rutaList1New = attachedRutaList1New;
            lugar.setRutaList1(rutaList1New);
            lugar = em.merge(lugar);
            for (Ruta rutaListNewRuta : rutaListNew) {
                if (!rutaListOld.contains(rutaListNewRuta)) {
                    Lugar oldOrigenOfRutaListNewRuta = rutaListNewRuta.getOrigen();
                    rutaListNewRuta.setOrigen(lugar);
                    rutaListNewRuta = em.merge(rutaListNewRuta);
                    if (oldOrigenOfRutaListNewRuta != null && !oldOrigenOfRutaListNewRuta.equals(lugar)) {
                        oldOrigenOfRutaListNewRuta.getRutaList().remove(rutaListNewRuta);
                        oldOrigenOfRutaListNewRuta = em.merge(oldOrigenOfRutaListNewRuta);
                    }
                }
            }
            for (Ruta rutaList1NewRuta : rutaList1New) {
                if (!rutaList1Old.contains(rutaList1NewRuta)) {
                    Lugar oldDestinoOfRutaList1NewRuta = rutaList1NewRuta.getDestino();
                    rutaList1NewRuta.setDestino(lugar);
                    rutaList1NewRuta = em.merge(rutaList1NewRuta);
                    if (oldDestinoOfRutaList1NewRuta != null && !oldDestinoOfRutaList1NewRuta.equals(lugar)) {
                        oldDestinoOfRutaList1NewRuta.getRutaList1().remove(rutaList1NewRuta);
                        oldDestinoOfRutaList1NewRuta = em.merge(oldDestinoOfRutaList1NewRuta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lugar.getIdLugar();
                if (findLugar(id) == null) {
                    throw new NonexistentEntityException("The lugar with id " + id + " no longer exists.");
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
            Lugar lugar;
            try {
                lugar = em.getReference(Lugar.class, id);
                lugar.getIdLugar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lugar with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ruta> rutaListOrphanCheck = lugar.getRutaList();
            for (Ruta rutaListOrphanCheckRuta : rutaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lugar (" + lugar + ") cannot be destroyed since the Ruta " + rutaListOrphanCheckRuta + " in its rutaList field has a non-nullable origen field.");
            }
            List<Ruta> rutaList1OrphanCheck = lugar.getRutaList1();
            for (Ruta rutaList1OrphanCheckRuta : rutaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lugar (" + lugar + ") cannot be destroyed since the Ruta " + rutaList1OrphanCheckRuta + " in its rutaList1 field has a non-nullable destino field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(lugar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lugar> findLugarEntities() {
        return findLugarEntities(true, -1, -1);
    }

    public List<Lugar> findLugarEntities(int maxResults, int firstResult) {
        return findLugarEntities(false, maxResults, firstResult);
    }

    private List<Lugar> findLugarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lugar.class));
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

    public Lugar findLugar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lugar.class, id);
        } finally {
            em.close();
        }
    }

    public int getLugarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lugar> rt = cq.from(Lugar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
