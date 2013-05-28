package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
@Entity
public class EstadoEditable implements EstadoOrdenCompra, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "estado")
    private OrdenCompra oc;

    public EstadoEditable() {
    }

    public EstadoEditable(OrdenCompra oc) {
        this.oc = oc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
