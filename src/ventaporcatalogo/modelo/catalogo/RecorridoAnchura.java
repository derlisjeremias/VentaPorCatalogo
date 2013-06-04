package ventaporcatalogo.modelo.catalogo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Jere
 */
public class RecorridoAnchura extends EstrategiaRecorrido {

    @Override
    public boolean existeCategoria(Categoria buscador, Categoria buscado) {
        if (buscador.getId().equals(buscado.getId())) {
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
//        System.out.println("Objetivo " + objetivo.getNombre());
//        boolean rtta;
//        Queue<ItemCatalogo> q = new LinkedList();
//        if  {
//            for (ItemCatalogo ic : padre.getItems()) {
//                q.add(ic);
//            }
//        }
//        while (!q.isEmpty()) {
//            ItemCatalogo aux = q.poll();
//            if (aux.esCategoria()) {
//                Categoria c = (Categoria) aux;
//                if (c.equals(objetivo)) {
//                    rtta = padre.eliminarCategoriaDeItems(objetivo);
//                } else {
//                    rtta = aux.eliminarCategoria(objetivo);
//                }
//            }
//        }
//        
//        return false;





System.out.println("Padre "+padre.getNombre()+" Objetivo " + objetivo.getNombre());


        boolean rtta = false;
        if (padre.equals(objetivo)) {
            rtta = padre.getPadre().eliminarCategoriaDeItems(objetivo);
        } else {
            Queue<ItemCatalogo> q = new LinkedList();
            for (ItemCatalogo ic : padre.getItems()) {
                q.add(ic);
            }
            while (!q.isEmpty()) {
                ItemCatalogo aux = q.poll();
                if (aux.esCategoria()) {
                    Categoria c = (Categoria) aux;
                    rtta = c.eliminarCategoria(objetivo);
                }
            }
        }
        return rtta;












    }

    @Override
    public List<Categoria> obtenerCategorias(Categoria categoria) {
        List<Categoria> lista = new ArrayList();
        Queue<ItemCatalogo> q = new LinkedList();
        if (!lista.contains(categoria)) {
            lista.add(categoria);
        }
        for (ItemCatalogo ic : categoria.getItems()) {
            q.add(ic);
        }
        while (!q.isEmpty()) {
            ItemCatalogo aux = q.poll();
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
            Queue<ItemCatalogo> q = new LinkedList();
            for (ItemCatalogo ic : padre.getItems()) {
                q.add(ic);
            }
            while (!q.isEmpty()) {
                ItemCatalogo aux = q.poll();
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
            Queue<ItemCatalogo> q = new LinkedList();
            for (ItemCatalogo ic : buscador.getItems()) {
                q.add(ic);
            }
            Categoria objetivo;
            while (!q.isEmpty()) {
                ItemCatalogo aux = q.poll();
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
