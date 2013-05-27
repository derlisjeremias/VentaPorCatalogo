package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Cargo {

    private List<OrdenCompra> ordenesCompra;

    public Vendedor() {
        this.nombre = "Vendedor";
        this.ordenesCompra = new ArrayList();
    }

    @Override
    public boolean permitidoAdministrarUsuarios() {
        return false;
    }

    @Override
    public boolean permitidoAdministrarCatalogo() {
        return false;
    }

    @Override
    public boolean permitidoHacerPedidos() {
        return true;
    }

    @Override
    public void agregarOrdenCompra(OrdenCompra oc) {
        this.ordenesCompra.add(oc);
    }

    @Override
    public List<OrdenCompra> obtenerOrdenesCompra() {
        return this.ordenesCompra;
    }
}
