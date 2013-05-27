package ventaporcatalogo.modelo.recorridocatalogo;

import java.util.LinkedList;
import java.util.Queue;
import ventaporcatalogo.modelo.Categoria;
import ventaporcatalogo.modelo.ItemCatalogo;

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
    public void eliminarCategoria(Categoria padre, Categoria objetivo) {
        Queue<ItemCatalogo> q = new LinkedList();
        for (ItemCatalogo ic : padre.getItems()) {
            q.add(ic);
        }
        while (!q.isEmpty()) {
            ItemCatalogo aux = q.poll();
            if (aux.equals(objetivo)) {
                padre.eliminarCategoriaDeItems(objetivo);
            } else {
                aux.eliminarCategoria(objetivo);
            }
        }
    }
}
