package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.catalogo.Catalogo;
import ventaporcatalogo.modelo.catalogo.Producto;
import ventaporcatalogo.modelo.catalogo.Categoria;
import java.io.Serializable;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import ventaporcatalogo.persistencia.*;

/**
 *
 * @author Jere
 */
@Entity
public class Empresa implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Producto> productos;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrdenCompra> ordenesCompra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<Usuario> usuarios;
    @OneToOne(cascade = CascadeType.ALL)
    private Catalogo catalogo;
    @OneToOne
    private ControlAcceso seguridad;
    @OneToOne
    private ManejoPersistencia persistencia;

    public Empresa() {
        this.productos = new ArrayList();
        this.ordenesCompra = new ArrayList();
        this.usuarios = new ArrayList();
        this.catalogo = new Catalogo();
        this.seguridad = new ControlAcceso(this);
        this.persistencia = new ManejoPersistencia();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void agregarUsuario(Usuario u) {
        // if (this.permitidoAdministrarUsuarios() && !this.esUsuario(u)) {
        this.usuarios.add(u);
        u.setEmpresa(this);
        this.persistrirUsuario(u);

        //}
    }

    public void eliminarUsuario(Usuario u) {
        if (this.permitidoAdministrarUsuarios() && this.esUsuario(u)) {
            this.usuarios.remove(u);
            this.destruirUsuario(u);
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
            if (this.catalogo.agregarCategoriaEnRaiz(c)) {
                this.persistirCategoria(c);
            }
        }
    }

    public void agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.agregarCategoriaEnCategoria(c_hija, c_padre)) {
                this.modificarCategoria(c_padre);
                this.persistirCategoria(c_hija);
            }
        }
    }

    public void agregarProductoEnCategoria(Producto p, Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(p)) {
                if (this.catalogo.agregarProductoEnCategoria(c, p)) {
                    this.modificarCategoria(c);
                    this.persistirProducto(p);
                }
            }
        }
    }

    public void agregarProducto(Producto p) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (!this.existeProducto(p)) {
                this.productos.add(p);
                this.persistirProducto(p);
            }
        }
    }

    private boolean existeProducto(Producto p) {
        return this.productos.contains(p);
    }

    public void eliminarCategoria(Categoria c) {
        if (this.catalogo.eliminarCategoria(c)) {
            this.destruirCategoria(c);
        }
    }

    public void moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.moverCategoriaHaciaCategoria(origen, destino, objetivo)) {
                this.modificarCategoria(origen);
                this.modificarCategoria(destino);
                this.modificarCategoria(objetivo);
            }
        }
    }

    public void moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.moverProductoHaciaCategoria(origen, destino, objetivo)) {
                this.modificarCategoria(origen);
                this.modificarCategoria(destino);
                this.modificarProducto(objetivo);
            }
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
            if (this.seguridad.agregarOrdenCompra(oc)) {
                this.persistirOrdenCompra(oc);
            }
        }
    }

    public List<OrdenCompra> obtenerOrdenesCompraUsuario() {
        if (this.seguridad.permitidoHacerPedidos()) {
            return this.seguridad.obtenerOrdenesCompra();
        }
        return listaVacia();
    }

    private ArrayList listaVacia() {
        return new ArrayList();
    }

    private void persistrirUsuario(Usuario u) {
        CtrlPersisUsuario cp = this.persistencia.getCpUsuario();
        cp.crear(u);
    }

    private void destruirUsuario(Usuario u) {
        try {
            CtrlPersisUsuario cp = this.persistencia.getCpUsuario();
            cp.destruir(u.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistirCategoria(Categoria c) {
        CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
        cp.crear(c);
    }

    private void modificarCategoria(Categoria c) {
        try {
            CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
            cp.editar(c);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruirCategoria(Categoria c) {
        try {
            CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
            cp.destruir(c.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistirProducto(Producto p) {
        CtrlPersisProducto cp = this.persistencia.getCpProducto();
        cp.crear(p);
    }

    private void modificarProducto(Producto p) {
        try {
            CtrlPersisProducto cp = this.persistencia.getCpProducto();
            cp.editar(p);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistirOrdenCompra(OrdenCompra oc) {
        CtrlPersisOrdenCompra cp = this.persistencia.getCpOrdenCompra();
        cp.crear(oc);
    }
}
