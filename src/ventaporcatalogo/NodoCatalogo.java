/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import javax.swing.tree.TreeNode;

/**
 *
 * @author Jere
 */
public interface NodoCatalogo extends TreeNode {

    public boolean esNodoCategoria();

    public boolean esNodoProducto();
}
