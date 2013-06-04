package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("EA")
public class EstadoArchivado extends EstadoOrdenCompra implements Serializable {

    public EstadoArchivado() {
    }

    public EstadoArchivado(OrdenCompra oc) {
        this.ordenCompra = oc;
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
        System.out.println("Orden codigo " + this.ordenCompra.getCodigo() + " en estado archivado");
    }

    @Override
    protected boolean permiteModicifacion() {
        return false;
    }
}
