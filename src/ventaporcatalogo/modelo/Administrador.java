package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Cargo {

    public Administrador() {
        this.nombre = "Administrador";
    }

    @Override
    public boolean permitidoAdministrarUsuarios() {
        return true;
    }

    @Override
    public boolean permitidoAdministrarCatalogo() {
        return true;
    }

    @Override
    public boolean permitidoHacerPedidos() {
        return false;
    }

    @Override
    public void agregarOrdenCompra(OrdenCompra oc) {
        //
    }

    @Override
    public List<OrdenCompra> obtenerOrdenesCompra() {
        return new ArrayList();
    }
}
