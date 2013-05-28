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
    private CtrlPersisCatalogo cpCatalogo = null;
    private CtrlPersisCategoria cpCategoria = null;
    private CtrlPersisEmpresa cpEmpresa = null;
    private CtrlPersisEstadoArchivado cpEstadoArchivado = null;
    private CtrlPersisEstadoCerrado cpEstadoCerrado = null;
    private CtrlPersisEstadoEditable cpEstadoEditable = null;
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
        this.cpCatalogo = new CtrlPersisCatalogo(emf);
        this.cpCategoria = new CtrlPersisCategoria(emf);
        this.cpEmpresa = new CtrlPersisEmpresa(emf);
        this.cpEstadoArchivado = new CtrlPersisEstadoArchivado(emf);
        this.cpEstadoCerrado = new CtrlPersisEstadoCerrado(emf);
        this.cpEstadoEditable = new CtrlPersisEstadoEditable(emf);
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

    public CtrlPersisCatalogo getCpCatalogo() {
        return cpCatalogo;
    }

    public CtrlPersisCategoria getCpCategoria() {
        return cpCategoria;
    }

    public CtrlPersisEmpresa getCpEmpresa() {
        return cpEmpresa;
    }

    public CtrlPersisEstadoArchivado getCpEstadoArchivado() {
        return cpEstadoArchivado;
    }

    public CtrlPersisEstadoCerrado getCpEstadoCerrado() {
        return cpEstadoCerrado;
    }

    public CtrlPersisEstadoEditable getCpEstadoEditable() {
        return cpEstadoEditable;
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
