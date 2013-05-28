package ventaporcatalogo.modelo.catalogo;

import java.util.LinkedList;
import java.util.Queue;
import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;

/**
 *
 * @author Jere
 */
public class RecorridoAnchura extends EstrategiaRecorrido {

    @Override
    public boolean existeCategoria(Categoria buscador, Categoria buscado) {
        if (buscador.equals(buscado)) {
            return true;
        } else {
            Queue<ItemCatalogo> q = new LinkedList();
            for (ItemCatalogo ic : buscador.getItems()) {
                q.add(ic);
            }
            while (!q.isEmpty()) {
                ItemCatalogo aux = q.poll();
                if (aux.existeCategoria(buscado)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean eliminarCategoria(Categoria padre, Categoria objetivo) {
        Queue<ItemCatalogo> q = new LinkedList();
        for (ItemCatalogo ic : padre.getItems()) {
            q.add(ic);
        }
        while (!q.isEmpty()) {
            ItemCatalogo aux = q.poll();
            if (aux.equals(objetivo)) {
                return padre.eliminarCategoriaDeItems(objetivo);
            } else {
                return aux.eliminarCategoria(objetivo);
            }
        }
        return false;
    }
}
