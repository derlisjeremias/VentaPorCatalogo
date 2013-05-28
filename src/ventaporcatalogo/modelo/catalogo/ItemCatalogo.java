package ventaporcatalogo.modelo.catalogo;

import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ItemCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract boolean esCategoria();

    public abstract boolean esProducto();

    public abstract boolean existeCategoria(Categoria c);

    public abstract boolean eliminarCategoria(Categoria c);

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
