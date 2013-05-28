package ventaporcatalogo.modelo.catalogo;

import ventaporcatalogo.modelo.catalogo.Categoria;

/**
 *
 * @author Jere
 */
public abstract class EstrategiaRecorrido {

    public abstract boolean existeCategoria(Categoria buscador, Categoria buscado);

    public abstract boolean eliminarCategoria(Categoria padre, Categoria objetivo);
}
