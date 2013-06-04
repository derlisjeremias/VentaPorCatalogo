package ventaporcatalogo.modelo.catalogo;

import java.util.List;
import ventaporcatalogo.persistencia.CtrlPersisCategoria;
import ventaporcatalogo.persistencia.ManejoPersistencia;

/**
 *
 * @author Jere
 */
public class Catalogo {

    private Categoria categoriaRaiz;

    public Catalogo() {
        this.recuperarRaiz();
    }

    private void recuperarRaiz() {
        ManejoPersistencia persistencia = new ManejoPersistencia();
        CtrlPersisCategoria cpc = persistencia.getCpCategoria();
        Long id = new Long(1);
        this.categoriaRaiz = cpc.encontrarCategoria(id);

        if (this.categoriaRaiz == null) {
            Categoria c = new Categoria("Catálogo");
            cpc.crear(c);
            this.categoriaRaiz = cpc.encontrarCategoria(id);
        }
        this.categoriaRaiz.setRecorrido(new RecorridoAnchura());
    }

    public String getNombre() {
        return this.categoriaRaiz.getNombre();
    }

    public boolean agregarCategoriaEnRaiz(Categoria c) {
        return this.categoriaRaiz.agregarCategoria(c);
    }

    public boolean agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.existeCategoria(c_padre)) {
            return c_padre.agregarCategoria(c_hija);
        }
        return false;
    }

    public boolean existeCategoria(Categoria c) {
        return this.categoriaRaiz.existeCategoria(c);
    }

    public boolean existeCategoriaConNombre(String nombre) {
        return this.categoriaRaiz.existeCategoriaConNombre(nombre);
    }

    public boolean agregarProductoEnCategoria(Producto p, Categoria c) {
        if (this.existeCategoria(c)) {
            return c.agregarProducto(p);
        }
        return false;
    }

    public boolean eliminarCategoria(Categoria c) {
        return this.categoriaRaiz.eliminarCategoria(c);
    }

    public boolean moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino) && this.existeCategoria(objetivo)) {
            if (origen.tieneItem(objetivo) && !destino.tieneItem(objetivo)) {
                return destino.agregarCategoria(objetivo) && origen.eliminarCategoriaDeItems(objetivo);
            }
        }
        return false;
    }

    public boolean moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.existeCategoria(origen) && this.existeCategoria(destino)) {
            return destino.agregarProducto(objetivo) && origen.eliminarProductoDeItems(objetivo);
        }
        return false;
    }

    public Categoria obtenerCategoriaRaiz() {
        return this.categoriaRaiz;
    }

    public Categoria obtenerCategoriaConNombre(String nombre) {
        return this.categoriaRaiz.obtenerCategoriaConNombre(nombre);
    }

    public List<Categoria> obtenerCategorias() {
        return this.categoriaRaiz.obtenerCategorias();
    }
}
