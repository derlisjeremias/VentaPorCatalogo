package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.List;

/**
 *
 * @author Jere
 */
public class ControlAcceso {

    private Empresa empresa;
    private Usuario usuarioActivo;

    public ControlAcceso(Empresa e) {
        this.empresa = e;
        this.usuarioActivo = null;
    }

    public boolean iniciarSesion(String n, String c) {
        List<Usuario> usuarios = this.empresa.obtenerUsuarios();
        for (Usuario u: usuarios){
            if(u.comprobarUsuario(n,c)){
                this.usuarioActivo = u;
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() {
        this.usuarioActivo = null;
    }

    public boolean sesionActiva() {
        return !(this.usuarioActivo == null);
    }

    public boolean permitidoAdministrarUsuarios() {
        return (this.sesionActiva() && this.usuarioActivo.permitidoAdministrarUsuarios());
    }


    public boolean permitidoAdministrarCatalogo() {
        return (this.sesionActiva() && this.usuarioActivo.permitidoAdministrarCatalogo());
    }

    boolean permitidoHacerPedidos() {
        return (this.sesionActiva() && this.usuarioActivo.permitidoHacerPedidos());
    }

    public boolean agregarOrdenCompra(OrdenCompra oc) {
        return this.usuarioActivo.agregarOrdenCompra(oc);
    }

    public List<OrdenCompra> obtenerOrdenesCompra() {
        return this.usuarioActivo.obtenerOrdenesCompra();
    }
   
}
