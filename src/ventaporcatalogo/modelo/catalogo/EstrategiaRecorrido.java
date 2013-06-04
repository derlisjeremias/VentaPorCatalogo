package ventaporcatalogo.modelo.catalogo;

import java.util.List;

/**
 *
 * @author Jere
 */
public abstract class EstrategiaRecorrido {

    public abstract boolean existeCategoria(Categoria buscador, Categoria buscado);
    
    public abstract boolean existeCategoriaConNombre(Categoria padre, String nombre);

    public abstract boolean eliminarCategoria(Categoria padre, Categoria objetivo);

    public abstract List<Categoria> obtenerCategorias(Categoria c);
    
    public abstract Categoria obtenerCategoriaConNombre(Categoria buscador, String nombreBuscado);
}
