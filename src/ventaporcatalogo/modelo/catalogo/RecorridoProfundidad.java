package ventaporcatalogo.modelo.catalogo;

import java.util.Stack;
import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;

/**
 *
 * @author Jere
 */
public class RecorridoProfundidad extends EstrategiaRecorrido {

    @Override
    public boolean existeCategoria(Categoria buscador, Categoria buscado) {
        if (buscador.equals(buscado)) {
            return true;
        } else {
            Stack<ItemCatalogo> s = new Stack();
            for (ItemCatalogo ic : buscador.getItems()) {
                s.push(ic);
            }
            while (!s.empty()) {
                ItemCatalogo aux = s.pop();
                if (aux.existeCategoria(buscado)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean eliminarCategoria(Categoria padre, Categoria objetivo) {
        Stack<ItemCatalogo> s = new Stack();
        for (ItemCatalogo ic : padre.getItems()) {
            s.push(ic);
        }
        while (!s.empty()) {
            ItemCatalogo aux = s.pop();
            if (aux.equals(objetivo)) {
                return padre.eliminarCategoriaDeItems(objetivo);
            } else {
                return aux.eliminarCategoria(objetivo);
            }
        }
        return false;
    }
}
