package ventaporcatalogo.modelo.ordencompra;

import ventaporcatalogo.modelo.Producto;

/**
 *
 * @author Jere
 */
public interface EstadoOrdenCompra {

    public void setCodigo(String codigo);

    public void setCodigoUsuario(String codigoUsuario);

    public void setNombreComprador(String nombreComprador);

    public void setDireccionComprador(String direccionComprador);

    public void agregarArticulo(Producto p, int cant);

    public void cerrarOrdenCompra();

    public void abrirOrdenCompra();

    public void archivarOrdenCompra();

    public String toString();
}
