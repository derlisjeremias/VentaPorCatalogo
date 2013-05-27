package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.recorridocatalogo.EstrategiaRecorrido;
import java.util.*;
import ventaporcatalogo.modelo.recorridocatalogo.RecorridoAnchura;

/**
 *
 * @author Jere
 */
public class Categoria extends ItemCatalogo {

    private List<ItemCatalogo> items;
    private EstrategiaRecorrido recorrido;

    public Categoria(String n) {
        this.nombre = n;
        this.items = new ArrayList();
        this.recorrido = new RecorridoAnchura();
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

    public void agregarCategoria(Categoria c) {
        if (!this.existeCategoria(c)) {
            this.agregarItemCatalogo(c);
        }
    }

    public void agregarItemCatalogo(ItemCatalogo ic) {
        if (!this.tieneItem(ic)) {
            this.items.add(ic);
        }
    }

    public boolean tieneItem(ItemCatalogo ic) {
        return this.items.contains(ic);
    }

    @Override
    public boolean existeCategoria(Categoria c) {
        return this.recorrido.existeCategoria(this, c);
    }

    public void agregarProducto(Producto p) {
        this.agregarItemCatalogo(p);
    }

    @Override
    public void eliminarCategoria(Categoria c) {
        this.recorrido.eliminarCategoria(this, c);
    }

    public void eliminarCategoriaDeItems(Categoria c) {
        this.items.remove(c);
    }

    public void eliminarProducto(Producto p) {
        this.items.remove(p);
    }
}
