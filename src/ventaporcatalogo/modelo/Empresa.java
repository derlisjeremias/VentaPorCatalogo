package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.*;
import persistencia.Persistencia;

/**
 *
 * @author Jere
 */
public class Empresa {

    private List<Producto> productos;
    private List<OrdenCompra> ordenesCompra;
    private List<Usuario> usuarios;
    private Catalogo catalogo;
    private ControlAcceso seguridad;
    private Persistencia persistencia;

    public Empresa() {
        this.productos = new ArrayList();
        this.ordenesCompra = new ArrayList();
        this.usuarios = new ArrayList();
        this.catalogo = new Catalogo();
        this.seguridad = new ControlAcceso(this);
        this.persistencia = new Persistencia();
    }

    public void agregarUsuario(Usuario u) {
        // if (this.permitidoAdministrarUsuarios() && !this.esUsuario(u)) {
        this.usuarios.add(u);
        u.setEmpresa(this);
        //}
    }

    public void eliminarUsuario(Usuario u) {
        if (this.permitidoAdministrarUsuarios() && this.esUsuario(u)) {
            this.usuarios.remove(u);
        }
    }

    public boolean esUsuario(Usuario u) {
        return (u.esUsuarioDe(this) && this.usuarios.contains(u));
    }

    public boolean iniciarSesion(String n, String c) {
        return this.seguridad.iniciarSesion(n, c);
    }

    public void cerrarSesion() {
        this.seguridad.cerrarSesion();
    }

    public boolean sesionActiva() {
        return this.seguridad.sesionActiva();
    }

    public boolean permitidoAdministrarUsuarios() {
        return this.seguridad.permitidoAdministrarUsuarios();
    }

    public List<Usuario> obtenerUsuarios() {
        return this.usuarios;
    }

    public void agregarCategoriaEnRaiz(Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            this.catalogo.agregarCategoriaEnRaiz(c);
        }
    }

    public void agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            this.catalogo.agregarCategoriaEnCategoria(c_hija, c_padre);
        }
    }

    public void agregarProductoEnCategoria(Producto p, Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(p)) {
                this.catalogo.agregarProductoEnCategoria(c, p);
            }
        }
    }

    public void agregarProducto(Producto p) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (!this.existeProducto(p)) {
                this.productos.add(p);
            }
        }
    }

    private boolean existeProducto(Producto p) {
        return this.productos.contains(p);
    }

    public void eliminarCategoria(Categoria c) {
        this.catalogo.eliminarCategoria(c);
    }

    public void moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            this.catalogo.moverCategoriaHaciaCategoria(origen, destino, objetivo);
        }
    }

    public void moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            this.catalogo.moverProductoHaciaCategoria(origen, destino, objetivo);
        }
    }

    public Categoria obtenerCategoriaInicio() {
        return this.catalogo.obtenerCategoriaInicio();
    }

    public List<Producto> obtenerProductos() {
        return this.productos;
    }

    public void agregarOrdenCompra(OrdenCompra oc) {
        if (this.seguridad.permitidoHacerPedidos()) {
            this.seguridad.agregarOrdenCompra(oc);
        }
    }

    public List<OrdenCompra> obtenerOrdenesCompraUsuario() {
        if (this.seguridad.permitidoHacerPedidos()) {
            return this.seguridad.obtenerOrdenesCompra();
        }            
        return listaVacia();
    }
    
    private ArrayList listaVacia(){
        return new ArrayList();
    }
    
}
