
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;

/**
 *
 * @author Jere
 */
public class CtrlPersisItemCatalogo implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisItemCatalogo(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(ItemCatalogo itemcatalogo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(itemcatalogo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(ItemCatalogo itemcatalogo) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            itemcatalogo = em.merge(itemcatalogo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = itemcatalogo.getId();
                if (encontrarItemCatalogo(id) == null) {
                    throw new NoExisteEntidadException("No existe el itemcatalogo con id " + id);
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
            ItemCatalogo itemcatalogo;
            try {
                itemcatalogo = em.getReference(ItemCatalogo.class, id);
                itemcatalogo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el itemcatalogo con id " + id, enfe);
            }
            em.remove(itemcatalogo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemCatalogo> encontrarEntidadesItemCatalogo() {
        return encontrarEntidadesItemCatalogo(true, -1, -1);
    }

    public List<ItemCatalogo> encontrarEntidadesItemCatalogo(int maxResultados, int primerResultado) {
        return encontrarEntidadesItemCatalogo(false, maxResultados, primerResultado);
    }

    private List<ItemCatalogo> encontrarEntidadesItemCatalogo(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemCatalogo.class));
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

    public ItemCatalogo encontrarItemCatalogo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemCatalogo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadItemCatalogo() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemCatalogo> rt = cq.from(ItemCatalogo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

