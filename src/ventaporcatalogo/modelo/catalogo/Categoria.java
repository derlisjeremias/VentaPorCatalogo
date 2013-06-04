package ventaporcatalogo.modelo.catalogo;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Table(name = "ITEM_CATEGORIA")
public class Categoria extends ItemCatalogo implements Serializable {

    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CATEGORIA_ITEMS", joinColumns = {
        @JoinColumn(name = "CATEGORIA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ITEM_ID")})
    private List<ItemCatalogo> items;
    @Transient
    private EstrategiaRecorrido recorrido;

    public Categoria() {
    }

    public Categoria(String n) {
        this.nombre = n;
        this.recorrido = null;
        this.items = new ArrayList();
    }

    public List<ItemCatalogo> getItems() {
        return this.items;
    }

    public void setItems(List<ItemCatalogo> items) {
        this.items = items;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstrategiaRecorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(EstrategiaRecorrido recorrido) {
        this.recorrido = recorrido;
    }

    @Override
    public boolean esCategoria() {
        return true;
    }

    @Override
    public boolean esProducto() {
        return false;
    }

////////////////    @Override
////////////////    public String toString() {
////////////////        if (this.nombre.equals("Catálogo")) {
////////////////            return this.nombre;
////////////////        }
////////////////        return "Categoría: " + this.nombre;
////////////////    }
    public boolean insercionDeCategoriaPermitida(Categoria c_hija) {
        // Se trata insertar en raiz antes de persistir
        Long uno = new Long(1);
        if (this.getId().equals(uno) && c_hija.getId() == null) {
            return true;
        }
        //Se trata inserta una categoria en si misma o sin persistir
        if (this.getId() == c_hija.getId() || c_hija.getId() == null) {
            return false;
        }
        //Se verifica que no se trate de insertar una categoria en su propia rama
        Categoria temp = this;
        System.out.println("Id destino " + temp.getId() + " id origen " + c_hija.getId());
        while (!temp.getId().equals(uno)) {
            if (temp.getId() == c_hija.getId()) {
                return false;
            }
            temp = temp.getPadre();
            System.out.println("Id rama " + temp.getId() + " id origen " + c_hija.getId());
        }
        return true;
    }

    public boolean agregarCategoria(Categoria c) {
        if (!this.items.contains(c) && this.insercionDeCategoriaPermitida(c)) {
            this.items.add(c);
            c.setPadre(this);
            return true;
        }
        return false;
    }

    public boolean agregarItemCatalogo(ItemCatalogo ic) {
        if (!this.items.contains(ic)) {
            this.items.add(ic);
            ic.setPadre(this);
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

    @Override
    public boolean existeCategoriaConNombre(String nombre) {
        return this.recorrido.existeCategoriaConNombre(this, nombre);
    }

    public boolean agregarProducto(Producto p) {
        if (!this.items.contains(p)) {
            this.items.add(p);
            p.setPadre(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarCategoria(Categoria c) {
        return this.recorrido.eliminarCategoria(this, c);
    }

    public boolean eliminarCategoriaDeItems(Categoria c) {
        return this.eliminarItemCatalogo(c);
    }

    public boolean eliminarProductoDeItems(Producto p) {
        return this.eliminarItemCatalogo(p);
    }

    public boolean eliminarItemCatalogo(ItemCatalogo ic) {
        if (this.items.contains(ic)) {
            this.items.remove(ic);
            return true;
        }
        return false;
    }

    public Categoria obtenerCategoriaConNombre(String nombre) {
        return this.recorrido.obtenerCategoriaConNombre(this, nombre);
    }

    public List<Categoria> obtenerCategorias() {
        if (this.recorrido == null) {
            this.recorrido = this.getPadre().getRecorrido();
        }
        return this.recorrido.obtenerCategorias(this);
    }

    @Override
    public String toString() {
        String padre_;
        if (this.getPadre() != null) {
            padre_ = this.getPadre().getNombre();
        } else {
            padre_ = "null";
        }
        return "id " + this.getId() + " nombre " + this.getNombre() + " padre " + padre_;
    }
}
