package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO_ESTADO")
public abstract class EstadoOrdenCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy="estado")
    protected OrdenCompra ordenCompra;

    public EstadoOrdenCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public abstract void cerrarOrdenCompra();

    public abstract void abrirOrdenCompra();

    public abstract void archivarOrdenCompra();

    protected abstract boolean permiteModicifacion();
}
