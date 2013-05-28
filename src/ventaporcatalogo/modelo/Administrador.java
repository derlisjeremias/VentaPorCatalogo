package ventaporcatalogo.modelo;

import java.io.Serializable;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Administrador extends Cargo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Administrador() {
        this.nombre = "Administrador";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public boolean agregarOrdenCompra(OrdenCompra oc) {
        return false;
    }

    @Override
    public List<OrdenCompra> obtenerOrdenesCompra() {
        return new ArrayList();
    }
}
