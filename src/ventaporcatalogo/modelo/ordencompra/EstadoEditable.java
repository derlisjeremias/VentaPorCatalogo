package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("EE")
public class EstadoEditable extends EstadoOrdenCompra implements Serializable {

    public EstadoEditable() {
    }

    public EstadoEditable(OrdenCompra oc) {
        this.ordenCompra = oc;
    }

    @Override
    public void abrirOrdenCompra() {
        System.out.println("Orden codigo " + ordenCompra.getCodigo() + " en estado editable");
    }

    @Override
    public void cerrarOrdenCompra() {
        this.ordenCompra.cerrar();
    }

    @Override
    public void archivarOrdenCompra() {
        this.ordenCompra.archivar();
    }

    @Override
    public String toString() {
        return "[editable]";
    }

    @Override
    protected boolean permiteModicifacion() {
        return true;
    }
}
