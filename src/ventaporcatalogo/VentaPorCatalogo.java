/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ventaporcatalogo.persistencia.ManejoPersistencia;

/**
 *
 * @author Jere
 */
public class VentaPorCatalogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        ManejoPersistencia persistencia = new ManejoPersistencia();
//        EntityManager em = persistencia.getCpProducto().getEntityManager();
//        em.getTransaction().begin();
//        System.out.println("inicia ");
//        String see = "SELECT I FROM ITEMCATALOGO AS I";
//        int j = em.createQuery(see).executeUpdate();
//        System.out.println("Elementos eliminados " + j);
//        em.getTransaction().commit();
//        em.close();




        ManejoPersistencia persistencia = new ManejoPersistencia();
        EntityManager em = persistencia.getCpProducto().getEntityManager();
        em.getTransaction().begin();
        System.out.println("inicia ");

        List list_p;
        String see_p = "SELECT ITEM_ID FROM CATEGORIA_ITEMS";
        Query q = em.createNativeQuery(see_p);
        list_p = q.getResultList();
        if (list_p.size() > 0) {
            for (Object p : list_p) {
                System.out.println("Dato " + p.toString());
            }
        } else {
            System.out.println("Sin datos");
        }
        em.getTransaction().commit();
        em.close();


//        List list_i;
//        String u = "SELECT ITEM_ID FROM CATEGORIA_ITEMS";
//        q = em.createNativeQuery(u);
//        list_i = q.getResultList();
//        if (list_i.size() > 0) {
//            for (Object p : list_i) {
//                System.out.println("Dato " + p.toString());
//            }
//        } else {
//            System.out.println("Sin datos");
//        }


//        String u = "";
//        q = em.createNativeQuery(U);
//        int j = q.executeUpdate();
//        System.out.println("Cantidad " + j);
//        em.getTransaction().commit();
//        em.close();


//        try {
//            Connection con = DriverManager.getConnection("jdbc:derby:VentaPorCatalogoDB;create=true", "admin", "admin");
//            DatabaseMetaData dbm = con.getMetaData();
//            ResultSet rsc = dbm.getColumns(null, null, "%", "%");
//            while (rsc.next()) {
//                System.out.println("    " + rsc.getString(3)+"   "+rsc.getString(4)+"   "+rsc.getString(6));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(VentaPorCatalogo.class.getName()).log(Level.SEVERE, null, ex);
//        }


        //        ManejoPersistencia persistencia = new ManejoPersistencia();
        //        CtrlPersisProducto ce = persistencia.getCpProducto();
        //
        //        List<Producto> l = ce.encontrarEntidadesProducto();
        //
        //        for (Producto p : l) {
        //            System.out.println("Id " + p.getId() + " codigo " + p.getCodigo() + " descripcion " + p.getDescripcion() + " stock " + p.getStock() + " imagen " + p.getPathImagen());
        //        }




//        ManejoPersistencia persistencia = new ManejoPersistencia();
//        CtrlPersisCategoria ce = persistencia.getCpCategoria();
//
//        List<Categoria> l = ce.encontrarEntidadesCategoria();
//
//        for (Categoria c : l) {
//            System.out.println("Id " + c.getId() + " nombre " + c.getNombre() + " padre " + c.getPadre());
//        }


//        ManejoPersistencia persistencia = new ManejoPersistencia();
//        CtrlPersisUsuario ce = persistencia.getCpUsuario();
//
//        List<Usuario> l = ce.encontrarEntidadesUsuario();
//        System.out.println("lista " + l.size());
//        for (Usuario p : l) {
//            System.out.println("Id " + p.getId() + " codigo " + p.getCodigo() + " nombre " + p.getNombre() + " cargo " + p.getCargo().toString() + " clave " + p.getClaveAcceso() + " empresa " + p.getEmpresa());
//        }

    }
}
