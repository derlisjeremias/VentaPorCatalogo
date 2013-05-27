package ventaporcatalogo.modelo.recorridocatalogo;

import java.util.Stack;
import ventaporcatalogo.modelo.Categoria;
import ventaporcatalogo.modelo.ItemCatalogo;

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
    public void eliminarCategoria(Categoria padre, Categoria objetivo) {
        Stack<ItemCatalogo> s = new Stack();
        for (ItemCatalogo ic : padre.getItems()) {
            s.push(ic);
        }
        while (!s.empty()) {
            ItemCatalogo aux = s.pop();
            if (aux.equals(objetivo)) {
                padre.eliminarCategoriaDeItems(objetivo);
            } else {
                aux.eliminarCategoria(objetivo);
            }
        }

    }
}
