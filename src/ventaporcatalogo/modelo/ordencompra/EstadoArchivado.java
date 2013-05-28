package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
@Entity
public class EstadoArchivado implements EstadoOrdenCompra, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "estado")
    private OrdenCompra oc;

    public EstadoArchivado() {
    }

    public EstadoArchivado(OrdenCompra oc) {
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
        this.estadoArchivado();
    }

    @Override
    public void setCodigoUsuario(String codigoUsuario) {
        this.estadoArchivado();
    }

    @Override
    public void setNombreComprador(String nombreComprador) {
        this.estadoArchivado();
    }

    @Override
    public void setDireccionComprador(String direccionComprador) {
        this.estadoArchivado();
    }

    @Override
    public void agregarArticulo(Producto p, int cant) {
        this.estadoArchivado();
    }

    @Override
    public void cerrarOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public void abrirOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public void archivarOrdenCompra() {
        this.estadoArchivado();
    }

    @Override
    public String toString() {
        return "[archivado]";
    }

    private void estadoArchivado() {
        System.out.println("Orden codigo " + oc.getCodigo() + " en estado archivado");
    }
}
