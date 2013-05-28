package ventaporcatalogo.modelo;

import java.io.Serializable;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "cargo")
    private Usuario usuario;
    protected String nombre;
    protected String claveAcceso;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract boolean permitidoAdministrarUsuarios();

    public abstract boolean permitidoAdministrarCatalogo();

    public abstract boolean permitidoHacerPedidos();

    public abstract boolean agregarOrdenCompra(OrdenCompra oc);

    public abstract List<OrdenCompra> obtenerOrdenesCompra();

    public boolean comprobarClave(String c) {
        return this.claveAcceso.equals(c);
    }

    public void asignarClaveAcceso(String c) {
        this.claveAcceso = c;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
