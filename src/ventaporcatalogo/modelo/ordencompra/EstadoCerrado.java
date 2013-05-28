package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
@Entity
public class EstadoCerrado implements EstadoOrdenCompra, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "estado")
    private OrdenCompra oc;

    public EstadoCerrado() {
    }

    public EstadoCerrado(OrdenCompra oc) {
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
        this.estadoCerrado();
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        this.estadoCerrado();
    }

    @Override
    public void setNombreComprador(String nombreComprador) {
        this.estadoCerrado();
    }

    @Override
    public void setDireccionComprador(String direccionComprador) {
        this.estadoCerrado();
    }

    @Override
    public void agregarArticulo(Producto p, int cant) {
        this.estadoCerrado();
    }

    @Override
    public void cerrarOrdenCompra() {
        this.estadoCerrado();
    }

    @Override
    public void abrirOrdenCompra() {
        oc.abrir();
    }

    @Override
    public void archivarOrdenCompra() {
        oc.archivar();
    }

    @Override
    public String toString() {
        return "[cerrado]";
    }

    private void estadoCerrado() {
        System.out.println("Orden codigo " + oc.getCodigo() + " en estado cerrado");
    }
}
