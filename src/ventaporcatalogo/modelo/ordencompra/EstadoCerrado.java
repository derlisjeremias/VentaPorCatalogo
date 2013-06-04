package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("EC")
public class EstadoCerrado extends EstadoOrdenCompra implements Serializable {

    public EstadoCerrado() {
    }

    public EstadoCerrado(OrdenCompra oc) {
        this.ordenCompra = oc;
    }

    @Override
    public void cerrarOrdenCompra() {
        System.out.println("Orden codigo " + ordenCompra.getCodigo() + " en estado cerrado");
    }

    @Override
    public void abrirOrdenCompra() {
        ordenCompra.abrir();
    }

    @Override
    public void archivarOrdenCompra() {
        ordenCompra.archivar();
    }

    @Override
    public String toString() {
        return "[cerrado]";
    }

    @Override
    protected boolean permiteModicifacion() {
        return false;
    }
}
