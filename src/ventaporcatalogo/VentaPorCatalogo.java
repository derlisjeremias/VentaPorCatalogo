/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import ventaporcatalogo.modelo.catalogo.Producto;
import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import ventaporcatalogo.modelo.*;

/**
 *
 * @author Jere
 */
public class VentaPorCatalogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto p1 = new Producto();
        p1.setCodigo("123");
        p1.setDescripcion("p1");
        p1.setStock(15);
        Producto p2 = new Producto();
        p2.setCodigo("223");
        p2.setDescripcion("p2");
        p2.setStock(25);

        Producto p3 = new Producto();
        p3.setCodigo("323");
        p3.setDescripcion("p3");
        p3.setStock(35);

        Producto p4 = new Producto();
        p4.setCodigo("423");
        p4.setDescripcion("p4");
        p4.setStock(45);
        Producto p5 = new Producto();
        p5.setCodigo("523");
        p5.setDescripcion("p5");
        p5.setStock(55);
        Producto p6 = new Producto();
        p6.setCodigo("623");
        p6.setDescripcion("p6");
        p6.setStock(65);
        Producto p7 = new Producto();
        p7.setCodigo("723");
        p7.setDescripcion("p7");
        p7.setStock(75);


        Categoria c1 = new Categoria("C1");
        Categoria c2 = new Categoria("C2");
        Categoria c3 = new Categoria("C3");

        Empresa e = new Empresa();


        Usuario u1 = new Usuario();
        u1.setCargo(new Vendedor());
        u1.setCodigo("123");
        u1.setNombre("Uno");
        u1.asignarClaveAcceso("uno");

        Usuario u2 = new Usuario();
        u2.setCargo(new Vendedor());
        u2.setCodigo("223");
        u2.setNombre("Dos");
        u2.asignarClaveAcceso("dos");

        Usuario u3 = new Usuario();
        u3.setCargo(new Vendedor());
        u3.setCodigo("323");
        u3.setNombre("Tres");
        u3.asignarClaveAcceso("tres");

        Usuario u4 = new Usuario();
        u4.setCargo(new Vendedor());
        u4.setCodigo("423");
        u4.setNombre("Cuatro");
        u4.asignarClaveAcceso("cuatro");

        Usuario u5 = new Usuario();
        u5.setCargo(new Vendedor());
        u5.setCodigo("523");
        u5.setNombre("Cinco");
        u5.asignarClaveAcceso("cinco");

        //OrdenCompra oc1 = new OrdenCompra();

        Usuario u = new Usuario();
        u.setCargo(new Administrador());
        u.setCodigo("1");
        u.setNombre("admin");
        u.asignarClaveAcceso("admin");

        e.agregarUsuario(u);
        e.iniciarSesion("admin", "admin");

        e.agregarProducto(p1);
        e.agregarProducto(p2);
        e.agregarProducto(p3);
        e.agregarProducto(p4);
        e.agregarProducto(p5);
        e.agregarProducto(p6);
        e.agregarProducto(p7);

        e.agregarCategoriaEnRaiz(c1);
        e.agregarCategoriaEnRaiz(c2);

        e.agregarProductoEnCategoria(p1, c1);
        e.agregarProductoEnCategoria(p2, c1);


        e.agregarProductoEnCategoria(p3, c2);
        e.agregarCategoriaEnCategoria(c3, c2);

        e.agregarProductoEnCategoria(p7, c3);
        e.agregarProductoEnCategoria(p6, c3);
        e.agregarProductoEnCategoria(p5, c3);
        e.agregarProductoEnCategoria(p4, c3);

        e.moverCategoriaHaciaCategoria(c2, c1, c3);

        e.agregarUsuario(u1);
        e.agregarUsuario(u2);
        e.agregarUsuario(u3);
        e.agregarUsuario(u4);
        e.agregarUsuario(u5);

        e.cerrarSesion();
        e.iniciarSesion("Uno", "uno");
        OrdenCompra oc1 = new OrdenCompra(u1);
        oc1.agregarArticulo(p1, 3);
        oc1.agregarArticulo(p2, 4);
        oc1.agregarArticulo(p3, 5);
        oc1.agregarArticulo(p3, 5);
        oc1.setNombreComprador("Comp1");
        oc1.setDireccionComprador("Dir1");

        OrdenCompra oc2 = new OrdenCompra(u1);
        oc2.agregarArticulo(p5, 3);
        oc2.agregarArticulo(p4, 2);
        oc2.agregarArticulo(p3, 1);
        oc2.setNombreComprador("Comp2");
        oc2.setDireccionComprador("Dir2");
        
        OrdenCompra oc3 = new OrdenCompra(u1);
        oc3.agregarArticulo(p2, 1);
        oc3.agregarArticulo(p7, 1);
        oc3.agregarArticulo(p6, 7);
        oc3.agregarArticulo(p1, 3);
        oc3.agregarArticulo(p2, 2);
        oc3.setNombreComprador("Comp3");
        oc3.setDireccionComprador("Dir3");
        
        oc1.cerrarOrdenCompra();
        oc3.cerrarOrdenCompra();
        
        e.agregarOrdenCompra(oc1);
        e.agregarOrdenCompra(oc2);
        e.agregarOrdenCompra(oc3);




        FrameVentaPorCatalogo frame = new FrameVentaPorCatalogo(e);
        frame.setVisible(true);

    }
}
