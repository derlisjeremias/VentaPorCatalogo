
package ventaporcatalogo.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ventaporcatalogo.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class CtrlPersisUsuario implements Serializable{

    private EntityManagerFactory emf = null;

    public CtrlPersisUsuario(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Usuario usuario) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getId();
                if (encontrarUsuario(id) == null) {
                    throw new NoExisteEntidadException("No existe el usuario con id " + id);
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe el usuario con id " + id, enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> encontrarEntidadesUsuario() {
        return encontrarEntidadesUsuario(true, -1, -1);
    }

    public List<Usuario> encontrarEntidadesUsuario(int maxResultados, int primerResultado) {
        return encontrarEntidadesUsuario(false, maxResultados, primerResultado);
    }

    private List<Usuario> encontrarEntidadesUsuario(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario encontrarUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadUsuario() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

