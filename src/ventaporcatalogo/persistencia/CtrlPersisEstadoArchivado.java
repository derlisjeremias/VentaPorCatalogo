
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.ordencompra.EstadoArchivado;

/**
 *
 * @author Jere
 */
public class CtrlPersisEstadoArchivado implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisEstadoArchivado(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(EstadoArchivado estadoarchivado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoarchivado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(EstadoArchivado estadoarchivado) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoarchivado = em.merge(estadoarchivado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoarchivado.getId();
                if (encontrarEstadoArchivado(id) == null) {
                    throw new NoExisteEntidadException("No existe el estadoarchivado con id " + id);
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
            EstadoArchivado estadoarchivado;
            try {
                estadoarchivado = em.getReference(EstadoArchivado.class, id);
                estadoarchivado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el estadoarchivado con id " + id, enfe);
            }
            em.remove(estadoarchivado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoArchivado> encontrarEntidadesEstadoArchivado() {
        return encontrarEntidadesEstadoArchivado(true, -1, -1);
    }

    public List<EstadoArchivado> encontrarEntidadesEstadoArchivado(int maxResultados, int primerResultado) {
        return encontrarEntidadesEstadoArchivado(false, maxResultados, primerResultado);
    }

    private List<EstadoArchivado> encontrarEntidadesEstadoArchivado(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoArchivado.class));
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

    public EstadoArchivado encontrarEstadoArchivado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoArchivado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadEstadoArchivado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoArchivado> rt = cq.from(EstadoArchivado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

