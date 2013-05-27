package ventaporcatalogo.modelo;

import java.awt.Image;

/**
 *
 * @author Jere
 */
public class Producto extends ItemCatalogo {

    private String codigo;
    private String descripcion;
    private int stock;
    private Image imagen;

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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
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
    public void eliminarCategoria(Categoria c) {
        // Es producto, no posee categorias
    }
}
