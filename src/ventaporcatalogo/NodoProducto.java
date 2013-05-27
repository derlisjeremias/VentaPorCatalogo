/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import ventaporcatalogo.modelo.Producto;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Jere
 */
public class NodoProducto implements NodoCatalogo {

    private Producto producto;

    public NodoProducto() {
        producto = null;
    }

    public NodoProducto(Producto p) {
        producto = p;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean esNodoCategoria() {
        return false;
    }

    @Override
    public boolean esNodoProducto() {
        return true;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return this.getParent();
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration children() {
        return null;
    }

    @Override
    public String toString() {
        return this.producto.toString();
    }
}
