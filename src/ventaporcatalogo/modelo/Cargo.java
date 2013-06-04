package ventaporcatalogo.modelo;

import java.io.Serializable;
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
//    private Usuario usuario;
    protected String nombre;

    public Cargo() {
    }

    public abstract boolean permitidoAdministrarUsuarios();

    public abstract boolean permitidoAdministrarCatalogo();

    public abstract boolean permitidoHacerPedidos();

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
