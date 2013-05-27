package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.List;

/**
 *
 * @author Jere
 */
public abstract class Cargo {

    protected String nombre;
    protected String claveAcceso;

    public abstract boolean permitidoAdministrarUsuarios();

    public abstract boolean permitidoAdministrarCatalogo();

    public abstract boolean permitidoHacerPedidos();

    public abstract void agregarOrdenCompra(OrdenCompra oc);

    public abstract List<OrdenCompra> obtenerOrdenesCompra();

    public boolean comprobarClave(String c) {
        return this.claveAcceso.equals(c);
    }

    void asignarClaveAcceso(String c) {
        this.claveAcceso = c;
    }

    public String toString() {
        return nombre;
    }
}
