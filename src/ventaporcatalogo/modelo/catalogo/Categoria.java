package ventaporcatalogo.modelo.catalogo;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
public class Categoria extends ItemCatalogo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCatalogo> items;
    @OneToOne
    private EstrategiaRecorrido recorrido;

    public Categoria() {
    }

    public Categoria(String n) {
        this.nombre = n;
        this.items = new ArrayList();
        this.recorrido = new RecorridoAnchura();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemCatalogo> getItems() {
        return this.items;
    }

    public void setItems(List<ItemCatalogo> items) {
        this.items = items;
    }

    @Override
    public boolean esCategoria() {
        return true;
    }

    @Override
    public boolean esProducto() {
        return false;
    }

    @Override
    public String toString() {
        return "Categoría: " + this.nombre;
    }

    public boolean agregarCategoria(Categoria c) {
        if (!this.existeCategoria(c)) {
            this.agregarItemCatalogo(c);
            return true;
        }
        return false;
    }

    public boolean agregarItemCatalogo(ItemCatalogo ic) {
        if (!this.tieneItem(ic)) {
            this.items.add(ic);
            return true;
        }
        return false;
    }

    public boolean tieneItem(ItemCatalogo ic) {
        return this.items.contains(ic);
    }

    @Override
    public boolean existeCategoria(Categoria c) {
        return this.recorrido.existeCategoria(this, c);
    }

    public boolean agregarProducto(Producto p) {
        return this.agregarItemCatalogo(p);
    }

    @Override
    public boolean eliminarCategoria(Categoria c) {
        return this.recorrido.eliminarCategoria(this, c);
    }

    public boolean eliminarCategoriaDeItems(Categoria c) {
        this.items.remove(c);
        return true;
    }

    public boolean eliminarProducto(Producto p) {
        this.items.remove(p);
        return true;
    }
}
