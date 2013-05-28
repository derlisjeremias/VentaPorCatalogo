package ventaporcatalogo.modelo;

import java.io.Serializable;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Vendedor extends Cargo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "vendedor")
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
    public boolean agregarOrdenCompra(OrdenCompra oc) {
        this.ordenesCompra.add(oc);
        return true;
    }

    @Override
    public List<OrdenCompra> obtenerOrdenesCompra() {
        return this.ordenesCompra;
    }
}
