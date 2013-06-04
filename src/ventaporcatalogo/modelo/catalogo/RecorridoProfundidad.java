package ventaporcatalogo.modelo.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    @Override
    public List<Categoria> obtenerCategorias(Categoria categoria) {
        List<Categoria> lista = new ArrayList();
        Stack<ItemCatalogo> s = new Stack();
        if (!lista.contains(categoria)) {
            lista.add(categoria);
        }
        for (ItemCatalogo ic : categoria.getItems()) {
            s.push(ic);
        }
        while (!s.empty()) {
            ItemCatalogo aux = s.pop();
            if (aux.esCategoria()) {
                Categoria categoriaTemp = (Categoria) aux;
                List<Categoria> listaTemp = categoriaTemp.obtenerCategorias();
                for (Categoria c : listaTemp) {
                    if (!lista.contains(c)) {
                        lista.add(c);
                    }
                }
            }
        }
        return lista;
    }

    @Override
    public boolean existeCategoriaConNombre(Categoria padre, String nombre) {
        if (padre.getNombre().equals(nombre)) {
            return true;
        } else {
            Stack<ItemCatalogo> s = new Stack();
            for (ItemCatalogo ic : padre.getItems()) {
                s.push(ic);
            }
            while (!s.empty()) {
                ItemCatalogo aux = s.pop();
                if (aux.existeCategoriaConNombre(nombre)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public Categoria obtenerCategoriaConNombre(Categoria buscador, String nombreBuscado) {
        if (buscador.getNombre().equals(nombreBuscado)) {
            return buscador;
        } else {
            Stack<ItemCatalogo> s = new Stack();
            for (ItemCatalogo ic : buscador.getItems()) {
                s.push(ic);
            }
            Categoria objetivo;
            while (!s.empty()) {
                ItemCatalogo aux = s.pop();
                if (aux.esCategoria()) {
                    Categoria c = (Categoria) aux;
                    objetivo = c.obtenerCategoriaConNombre(nombreBuscado);
                    if (objetivo != null) {
                        return objetivo;
                    }
                }
            }
        }
        return null;
    }
}
