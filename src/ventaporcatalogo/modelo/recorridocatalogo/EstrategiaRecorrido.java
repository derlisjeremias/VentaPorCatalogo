package ventaporcatalogo.modelo.recorridocatalogo;

import ventaporcatalogo.modelo.Categoria;

/**
 *
 * @author Jere
 */
public abstract class EstrategiaRecorrido {

    public abstract boolean existeCategoria(Categoria buscador, Categoria buscado);

    public abstract void eliminarCategoria(Categoria padre, Categoria objetivo);
}
