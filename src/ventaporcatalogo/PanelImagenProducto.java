package ventaporcatalogo;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
public class PanelImagenProducto extends JPanel {

    private Image imagenProducto;

    public PanelImagenProducto() {
        this.imagenProducto = null;
    }

    public void ponerImagenProducto(Producto p) {
        this.imagenProducto = new ImageIcon(getClass().getResource(p.getPathImagen())).getImage();
        this.repaint();
    }

    public void sacarImagenProducto() {
        this.imagenProducto = null;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (this.imagenProducto == null) {
            this.setOpaque(true);
        } else {
            g.drawImage(this.imagenProducto, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        }
        super.paint(g);
    }
}
