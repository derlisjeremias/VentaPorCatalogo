
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;

/**
 *
 * @author Jere
 */
public class CtrlPersisOrdenCompra implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisOrdenCompra(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(OrdenCompra ordencompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ordencompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(OrdenCompra ordencompra) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ordencompra = em.merge(ordencompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ordencompra.getId();
                if (encontrarOrdenCompra(id) == null) {
                    throw new NoExisteEntidadException("No existe el ordencompra con id " + id);
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
            OrdenCompra ordencompra;
            try {
                ordencompra = em.getReference(OrdenCompra.class, id);
                ordencompra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el ordencompra con id " + id, enfe);
            }
            em.remove(ordencompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenCompra> encontrarEntidadesOrdenCompra() {
        return encontrarEntidadesOrdenCompra(true, -1, -1);
    }

    public List<OrdenCompra> encontrarEntidadesOrdenCompra(int maxResultados, int primerResultado) {
        return encontrarEntidadesOrdenCompra(false, maxResultados, primerResultado);
    }

    private List<OrdenCompra> encontrarEntidadesOrdenCompra(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenCompra.class));
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

    public OrdenCompra encontrarOrdenCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadOrdenCompra() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenCompra> rt = cq.from(OrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

