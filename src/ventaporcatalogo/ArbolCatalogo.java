/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.catalogo.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import ventaporcatalogo.modelo.Empresa;

/**
 *
 * @author Jere
 */
public class ArbolCatalogo implements TreeModel {

    private Empresa modelApp;
    private NodoCategoria raiz;
    private List<TreeModelListener> dependientes;

    public ArbolCatalogo() {
    }

    public ArbolCatalogo(Empresa e) {
        this.modelApp = e;
        Categoria cr = e.obtenerCategoriaRaiz();
        NodoCategoria nr = new NodoCategoria(cr);
        raiz = nr;
        dependientes = new ArrayList();
    }

    public void setRaiz(NodoCategoria raiz) {
        this.raiz = raiz;
    }

    @Override
    public Object getRoot() {
        return raiz;
    }

    @Override
    public Object getChild(Object parent, int index) {
        NodoCatalogo p = (NodoCatalogo) parent;
        return (NodoCatalogo) p.getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        NodoCatalogo p = (NodoCatalogo) parent;
        return p.getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        NodoCatalogo n = (NodoCatalogo) node;
        return n.isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        NodoCatalogo n = (NodoCatalogo) path.getLastPathComponent();
        if (n.esNodoProducto()) {
            NodoProducto np = (NodoProducto) n;
            Producto p = (Producto) newValue;
            np.setProducto(p);
        }
        if (n.esNodoCategoria()) {
            NodoCategoria nc = (NodoCategoria) n;
            Categoria c = (Categoria) newValue;
            c.setRecorrido(this.modelApp.obtenerCategoriaRaiz().getRecorrido());
            nc.setCategoria(c);
        }
        TreeModelEvent e = new TreeModelEvent(n, path);
        this.arbolModificado(e);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        NodoCategoria p = (NodoCategoria) parent;
        for (int count = p.getChildCount(), i = 0; i < count; i++) {
            if (p.getChildAt(i).equals(child)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        dependientes.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        dependientes.remove(l);
    }

    private void arbolModificado(TreeModelEvent e) {
        for (TreeModelListener l : dependientes) {
            l.treeNodesChanged(e);
        }
    }

    void cargarItems() {
        
    }
}
