package ventaporcatalogo.modelo.ordencompra;

import ventaporcatalogo.modelo.Producto;

/**
 *
 * @author Jere
 */
public class EstadoArchivado implements EstadoOrdenCompra {

    private OrdenCompra oc;

    public EstadoArchivado(OrdenCompra oc) {
        this.oc = oc;
    }

    @Override
    public void setCodigo(String codigo) {
        this.estadoArchivado();
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        this.estadoArchivado();
    }

    @Override
    public void setNombreComprador(String nombreComprador) {
        this.estadoArchivado();
    }

    @Override
    public void setDireccionComprador(String direccionComprador) {
        this.estadoArchivado();
    }

    @Override
    public void agregarArticulo(Producto p, int cant) {
        this.estadoArchivado();
    }

    @Override
    public void cerrarOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public void abrirOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public void archivarOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public String toString() {
        return "[archivado]";
    }

    private void estadoArchivado() {
        System.out.println("Orden codigo " + oc.getCodigo() + " en estado archivado");
    }
}
