package ventaporcatalogo.modelo.catalogo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
@Table(name = "ITEM_PRODUCTO")
public class Producto extends ItemCatalogo implements Serializable {


    private String codigo;
    private String descripcion;
    private int stock;
    private String pathImagen;

    public Producto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    @Override
    public boolean esCategoria() {
        return false;
    }

    @Override
    public boolean esProducto() {
        return true;
    }

    @Override
    public String toString() {
        return " Producto:(cod " + this.codigo + ")(descri " + this.descripcion + ")(stock " + this.stock + ")";
    }

    @Override
    public boolean existeCategoria(Categoria c) {
        return false;
    }

    @Override
    public boolean eliminarCategoria(Categoria c) {
        return false;
    }

    @Override
    public boolean existeCategoriaConNombre(String nombre) {
        return false;
    }
}
