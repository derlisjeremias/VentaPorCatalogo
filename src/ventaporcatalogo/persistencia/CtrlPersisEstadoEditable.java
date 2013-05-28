
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.ordencompra.EstadoEditable;

/**
 *
 * @author Jere
 */
public class CtrlPersisEstadoEditable implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisEstadoEditable(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(EstadoEditable estadoeditable) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoeditable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(EstadoEditable estadoeditable) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoeditable = em.merge(estadoeditable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoeditable.getId();
                if (encontrarEstadoEditable(id) == null) {
                    throw new NoExisteEntidadException("No existe el estadoeditable con id " + id);
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destruir(Long id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoEditable estadoeditable;
            try {
                estadoeditable = em.getReference(EstadoEditable.class, id);
                estadoeditable.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el estadoeditable con id " + id, enfe);
            }
            em.remove(estadoeditable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoEditable> encontrarEntidadesEstadoEditable() {
        return encontrarEntidadesEstadoEditable(true, -1, -1);
    }

    public List<EstadoEditable> encontrarEntidadesEstadoEditable(int maxResultados, int primerResultado) {
        return encontrarEntidadesEstadoEditable(false, maxResultados, primerResultado);
    }

    private List<EstadoEditable> encontrarEntidadesEstadoEditable(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoEditable.class));
            Query q = em.createQuery(cq);
            if (!total) {
                q.setMaxResults(maxResultados);
                q.setFirstResult(primerResultado);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EstadoEditable encontrarEstadoEditable(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoEditable.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadEstadoEditable() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoEditable> rt = cq.from(EstadoEditable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

