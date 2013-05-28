/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import ventaporcatalogo.modelo.catalogo.Categoria;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.tree.TreeNode;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
public class NodoCategoria implements NodoCatalogo {

    private Categoria categoria;
    private List<NodoCatalogo> hijos;

    public NodoCategoria() {
        categoria = null;
        hijos = new ArrayList();
    }

    public NodoCategoria(Categoria d) {
        categoria = d;
        hijos = new ArrayList();
        for (ItemCatalogo ic : d.getItems()) {
            NodoCatalogo nodo = null;
            if (ic.esCategoria()) {
                Categoria c = (Categoria) ic;
                nodo = new NodoCategoria(c);
            }
            if (ic.esProducto()) {
                Producto p = (Producto) ic;
                nodo = new NodoProducto(p);
            }
            
            hijos.add(nodo);
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<NodoCatalogo> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoCatalogo> hijos) {
        this.hijos = hijos;
    }

    @Override
    public boolean esNodoCategoria() {
        return true;
    }

    @Override
    public boolean esNodoProducto() {
        return false;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return (TreeNode) hijos.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return hijos.size();
    }

    @Override
    public TreeNode getParent() {
        return this.getParent();
    }

    @Override
    public int getIndex(TreeNode node) {
        if (hijos.contains((NodoCatalogo) node)) {
            return hijos.indexOf(node);
        }
        return -1;

    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration children() {
        String s = new String();
        for (NodoCatalogo n : hijos) {
            if (s.isEmpty()) {
                s = n.toString();
            } else {
                s += " " + n.toString();
            }
        }
        StringTokenizer st = new StringTokenizer(s);
       // st.nextToken(", ");
        return st;
    }

    @Override
    public String toString() {
        return this.categoria.toString();
    }
}
