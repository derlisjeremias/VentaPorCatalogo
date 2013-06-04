package ventaporcatalogo.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jere
 */
public class ManejoPersistencia {

    private EntityManagerFactory emf = null;
    private CtrlPersisAdministrador cpAdministrador = null;
    private CtrlPersisArticulo cpArticulo = null;
    private CtrlPersisCargo cpCargo = null;
    private CtrlPersisCategoria cpCategoria = null;
    private CtrlPersisItemCatalogo cpItemCatalogo = null;
    private CtrlPersisOrdenCompra cpOrdenCompra = null;
    private CtrlPersisProducto cpProducto = null;
    private CtrlPersisUsuario cpUsuario = null;
    private CtrlPersisVendedor cpVendedor = null;

    public ManejoPersistencia() {
        this.emf = Persistence.createEntityManagerFactory("VentaPorCatalogoPU");
        this.cpAdministrador = new CtrlPersisAdministrador(emf);
        this.cpArticulo = new CtrlPersisArticulo(emf);
        this.cpCargo = new CtrlPersisCargo(emf);
        this.cpCategoria = new CtrlPersisCategoria(emf);
        this.cpItemCatalogo = new CtrlPersisItemCatalogo(emf);
        this.cpOrdenCompra = new CtrlPersisOrdenCompra(emf);
        this.cpProducto = new CtrlPersisProducto(emf);
        this.cpUsuario = new CtrlPersisUsuario(emf);
        this.cpVendedor = new CtrlPersisVendedor(emf);
    }

    public CtrlPersisAdministrador getCpAdministrador() {
        return cpAdministrador;
    }

    public CtrlPersisArticulo getCpArticulo() {
        return cpArticulo;
    }

    public CtrlPersisCargo getCpCargo() {
        return cpCargo;
    }

    public CtrlPersisCategoria getCpCategoria() {
        return cpCategoria;
    }

    public CtrlPersisItemCatalogo getCpItemCatalogo() {
        return cpItemCatalogo;
    }

    public CtrlPersisOrdenCompra getCpOrdenCompra() {
        return cpOrdenCompra;
    }

    public CtrlPersisProducto getCpProducto() {
        return cpProducto;
    }

    public CtrlPersisUsuario getCpUsuario() {
        return cpUsuario;
    }

    public CtrlPersisVendedor getCpVendedor() {
        return cpVendedor;
    }
}
