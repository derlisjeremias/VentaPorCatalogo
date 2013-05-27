package ventaporcatalogo.modelo.ordencompra;

import ventaporcatalogo.modelo.Producto;

/**
 *
 * @author Jere
 */
public class EstadoEditable implements EstadoOrdenCompra {

    private OrdenCompra oc;

    public EstadoEditable(OrdenCompra oc) {
        this.oc = oc;
    }

    @Override
    public void setCodigo(String codigo) {
        oc.setCodigo(codigo, this);
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        oc.setCodigoUsuario(codigoUsuario, this);
    }

    @Override
    public void setNombreComprador(String nombreComprador) {
        oc.setNombreComprador(nombreComprador, this);
    }

    @Override
    public void setDireccionComprador(String direccionComprador) {
        oc.setDireccionComprador(direccionComprador, this);
    }

    @Override
    public void agregarArticulo(Producto p, int cant) {
        oc.agregarArticulo(p, cant, this);
    }

    @Override
    public void cerrarOrdenCompra() {
        oc.cerrar();
    }

    @Override
    public void abrirOrdenCompra() {
        this.estadoEditable();
    }

    private void estadoEditable() {
        System.out.println("Orden codigo " + oc.getCodigo() + " en estado editable");
    }

    @Override
    public void archivarOrdenCompra() {
        oc.archivar();
    }

    @Override
    public String toString() {
        return "[editable]";
    }
}
