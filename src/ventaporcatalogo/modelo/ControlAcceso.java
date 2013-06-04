package ventaporcatalogo.modelo;

import java.util.ArrayList;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.List;

/**
 *
 * @author Jere
 */
public class ControlAcceso {

    private Empresa empresa;
    private Usuario usuarioActivo;
    private boolean inicioSistema;

    public ControlAcceso() {
    }

    protected ControlAcceso(Empresa e) {
        this.empresa = e;
        this.usuarioActivo = null;
        this.inicioSistema = false;
    }

    protected boolean iniciarSesion(String n, String c) {
        List<Usuario> usuarios = this.empresa.obtenerUsuarios(this);
        for (Usuario u : usuarios) {
            if (u.comprobarUsuario(n, c)) {
                this.usuarioActivo = u;
                return true;
            }
        }
        return false;
    }

    protected void cerrarSesion() {
        this.usuarioActivo = null;
    }

    protected boolean sesionActiva() {
        return (this.inicioSistema || !(this.usuarioActivo == null));
    }

    protected void inicioCargaSistema() {
        this.inicioSistema = true;
    }

    protected void finCargaSistema() {
        this.inicioSistema = false;
    }

    protected boolean permitidoAdministrarUsuarios() {
        return (this.inicioSistema || (this.sesionActiva() && this.usuarioActivo.permitidoAdministrarUsuarios()));
    }

    protected boolean permitidoAdministrarCatalogo() {
        return (this.inicioSistema || (this.sesionActiva() && this.usuarioActivo.permitidoAdministrarCatalogo()));
    }

    protected boolean permitidoHacerPedidos() {
        return (this.inicioSistema || (this.sesionActiva() && this.usuarioActivo.permitidoHacerPedidos()));
    }

    protected boolean agregarOrdenCompraParaUsuarioActivo(OrdenCompra oc) {
        return this.usuarioActivo.agregarOrdenCompra(oc);
    }

    protected List<OrdenCompra> obtenerOrdenesCompraDeUsuarioActivo() {
        if (this.inicioSistema) {
            return new ArrayList();
        }
        return this.usuarioActivo.obtenerOrdenesCompra();
    }

    protected boolean agregarArticuloParaOrdenCompraUsuarioActivo(Articulo articulo, OrdenCompra ordenCompra) {
        return this.usuarioActivo.agregarArticuloParaOrdenCompra(articulo, ordenCompra);
    }

    protected boolean eliminarArticuloParaOrdenCompraUsuarioActivo(Articulo articulo, OrdenCompra ordenCompra) {
        return this.usuarioActivo.eliminarArticuloParaOrdenCompra(articulo, ordenCompra);
    }

    protected void crearOrdenCompraParaUsuarioActivo(String nombreComprador, String direccionComprador) {
        this.usuarioActivo.crearOrdenCompra(nombreComprador, direccionComprador);
    }
}
