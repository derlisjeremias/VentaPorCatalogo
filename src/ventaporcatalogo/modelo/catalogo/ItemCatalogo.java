package ventaporcatalogo.modelo.catalogo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jere
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ItemCatalogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID", updatable = false, nullable = true)
    private Long id;
    @ManyToOne
    @JoinTable(name = "ITEMS_CATEGORIA",
            joinColumns =
            @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns =
            @JoinColumn(name = "CATEGORIA_PADRE_ID"))
    protected Categoria padre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getPadre() {
        return padre;
    }

    public void setPadre(Categoria padre) {
        this.padre = padre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemCatalogo other = (ItemCatalogo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public abstract boolean esCategoria();

    public abstract boolean esProducto();

    public abstract boolean existeCategoria(Categoria c);

    public abstract boolean eliminarCategoria(Categoria c);

    public abstract boolean existeCategoriaConNombre(String nombre);
}
