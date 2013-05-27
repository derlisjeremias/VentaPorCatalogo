package ventaporcatalogo.modelo.ordencompra;

import ventaporcatalogo.modelo.Producto;

/**
 *
 * @author Jere
 */
public class EstadoCerrado implements EstadoOrdenCompra {

    private OrdenCompra oc;

    public EstadoCerrado(OrdenCompra oc) {
        this.oc = oc;
    }

    @Override
    public void setCodigo(String codigo) {
        this.estadoCerrado();
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        this.estadoCerrado();
    }

    @Override
    public void setNombreComprador(String nombreComprador) {
        this.estadoCerrado();
    }

    @Override
    public void setDireccionComprador(String direccionComprador) {
        this.estadoCerrado();
    }

    @Override
    public void agregarArticulo(Producto p, int cant) {
        this.estadoCerrado();
    }

    @Override
    public void cerrarOrdenCompra() {
        this.estadoCerrado();
    }

    @Override
    public void abrirOrdenCompra() {
        oc.abrir();
    }

    @Override
    public void archivarOrdenCompra() {
        oc.archivar();
    }

    @Override
    public String toString() {
        return "[cerrado]";
    }

    private void estadoCerrado() {
        System.out.println("Orden codigo " + oc.getCodigo() + " en estado cerrado");
    }
}
