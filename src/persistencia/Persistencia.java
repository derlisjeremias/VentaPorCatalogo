
package persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jere
 */
public class Persistencia {
   private EntityManagerFactory emf = null;
   
   public Persistencia(){
       this.emf = Persistence.createEntityManagerFactory("VentaPorCatalogoPU");
   }
}
