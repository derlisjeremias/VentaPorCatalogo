package ventaporcatalogo.modelo.catalogo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
public class Catalogo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Categoria categorias;

    public Catalogo() {
        this.categorias = new Categoria("Catalogo");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.categorias.getNombre();
    }

    public boolean agregarCategoriaEnRaiz(Categoria c) {
        return this.categorias.agregarCategoria(c);
    }

    public boolean agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.existeCategoria(c_padre)) {
            return c_padre.agregarCategoria(c_hija);
        }
        return false;
    }

    public boolean existeCategoria(Categoria c) {
        return this.categorias.existeCategoria(c);
    }

    public boolean agregarProductoEnCategoria(Categoria c, Producto p) {
        if (this.existeCategoria(c)) {
            return c.agregarProducto(p);
        }
        return false;
    }

    public boolean eliminarCategoria(Categoria c) {
        return this.categorias.eliminarCategoria(c);
    }

    public boolean moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino) && this.existeCategoria(objetivo)) {
            if (origen.tieneItem(objetivo)) {
                return destino.agregarCategoria(objetivo) && origen.eliminarCategoriaDeItems(objetivo);
            }
        }
        return false;
    }

    public boolean moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino)) {
            if (origen.tieneItem(objetivo)) {
                return destino.agregarItemCatalogo(objetivo) && origen.eliminarProducto(objetivo);
            }
        }
        return false;
    }

    public Categoria obtenerCategoriaInicio() {
        return this.categorias;
    }
}
