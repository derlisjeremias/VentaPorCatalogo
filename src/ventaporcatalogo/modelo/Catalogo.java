package ventaporcatalogo.modelo;

/**
 *
 * @author Jere
 */
public class Catalogo {

    private Categoria categorias;

    public Catalogo() {
        this.categorias = new Categoria("Catalogo");
    }

    public String getNombre() {
        return this.categorias.getNombre();
    }

    public void agregarCategoriaEnRaiz(Categoria c) {
        this.categorias.agregarCategoria(c);
    }

    public void agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.existeCategoria(c_padre)) {
            c_padre.agregarCategoria(c_hija);
        }
    }

    public boolean existeCategoria(Categoria c) {
        return this.categorias.existeCategoria(c);
    }

    void agregarProductoEnCategoria(Categoria c, Producto p) {
        if (this.existeCategoria(c)) {
            c.agregarProducto(p);
        }
    }

    public void eliminarCategoria(Categoria c) {
        this.categorias.eliminarCategoria(c);
    }

    public void moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino) && this.existeCategoria(objetivo)) {
            if (origen.tieneItem(objetivo)) {
                destino.agregarCategoria(objetivo);
                origen.eliminarCategoriaDeItems(objetivo);
            }
        }
    }

    public void moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino)){
            if (origen.tieneItem(objetivo)) {
            destino.agregarItemCatalogo(objetivo);
            origen.eliminarProducto(objetivo);
            }
            
        }
    }

    Categoria obtenerCategoriaInicio() {
        return this.categorias;
    }
}
