
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.ordencompra.EstadoCerrado;

/**
 *
 * @author Jere
 */
public class CtrlPersisEstadoCerrado implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisEstadoCerrado(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(EstadoCerrado estadocerrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadocerrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(EstadoCerrado estadocerrado) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadocerrado = em.merge(estadocerrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadocerrado.getId();
                if (encontrarEstadoCerrado(id) == null) {
                    throw new NoExisteEntidadException("No existe el estadocerrado con id " + id);
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
            EstadoCerrado estadocerrado;
            try {
                estadocerrado = em.getReference(EstadoCerrado.class, id);
                estadocerrado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el estadocerrado con id " + id, enfe);
            }
            em.remove(estadocerrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoCerrado> encontrarEntidadesEstadoCerrado() {
        return encontrarEntidadesEstadoCerrado(true, -1, -1);
    }

    public List<EstadoCerrado> encontrarEntidadesEstadoCerrado(int maxResultados, int primerResultado) {
        return encontrarEntidadesEstadoCerrado(false, maxResultados, primerResultado);
    }

    private List<EstadoCerrado> encontrarEntidadesEstadoCerrado(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoCerrado.class));
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

    public EstadoCerrado encontrarEstadoCerrado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoCerrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadEstadoCerrado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoCerrado> rt = cq.from(EstadoCerrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

