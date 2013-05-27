package ventaporcatalogo.modelo;

/**
 *
 * @author Jere
 */
public abstract class ItemCatalogo {

    protected String nombre;

    public abstract boolean esCategoria();

    public abstract boolean esProducto();
    
    public abstract boolean existeCategoria(Categoria c);
    
    public abstract void eliminarCategoria(Categoria c);

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
