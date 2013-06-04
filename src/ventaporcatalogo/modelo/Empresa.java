package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.catalogo.Catalogo;
import ventaporcatalogo.modelo.catalogo.Producto;
import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;
import ventaporcatalogo.persistencia.*;

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
    private ManejoPersistencia persistencia;

    public Empresa() {
        this.productos = new ArrayList();
        this.ordenesCompra = new ArrayList();
        this.usuarios = new ArrayList();
        this.catalogo = new Catalogo();
        this.seguridad = new ControlAcceso(this);
        this.persistencia = new ManejoPersistencia();
        this.catalogo = new Catalogo();
        this.seguridad.inicioCargaSistema();
        this.recuperarDatos();
    }

    private void recuperarDatos() {
        this.cargarProductos();
        this.cargarOrdenesCompra();
        this.cargarUsuarios();
    }

    private void cargarProductos() {
        CtrlPersisProducto cpp = this.persistencia.getCpProducto();
        this.productos = cpp.encontrarEntidadesProducto();
        for (Producto p : this.productos) {
            p.getPadre().setRecorrido(this.obtenerCategoriaRaiz().getRecorrido());
        }
    }

    private void cargarOrdenesCompra() {
        CtrlPersisOrdenCompra cpoc = this.persistencia.getCpOrdenCompra();
        this.ordenesCompra = cpoc.encontrarEntidadesOrdenCompra();
    }

    private void cargarUsuarios() {
        CtrlPersisUsuario cpu = this.persistencia.getCpUsuario();
        List<Usuario> listaU = cpu.encontrarEntidadesUsuario();
        for (Usuario u : listaU) {
            u.setEmpresa(this);
        }
        this.usuarios = listaU;
    }

    public void finCargaSistema() {
        this.seguridad.finCargaSistema();
    }
/////////////////////////////////////////////////////// USUARIO ////////////////////////////////////////////////////

    public void crearAgregarUsuarioAdministrador(String codigo, String nombre, String clave) {
        if (this.seguridad.permitidoAdministrarUsuarios() && !this.existeUsuarioConCodigo(codigo)) {
            Administrador cargo = new Administrador();
            Usuario u = this.crearAgregarUsuario(codigo, nombre, clave, new Administrador());
        }
    }

    public void crearAgregarUsuarioVendedor(String codigo, String nombre, String clave) {
        if (this.seguridad.permitidoAdministrarUsuarios() && !this.existeUsuarioConCodigo(codigo)) {
            Vendedor cargo = new Vendedor();
            Usuario u = this.crearAgregarUsuario(codigo, nombre, clave, cargo);
        }
    }

    private Usuario crearAgregarUsuario(String codigo, String nombre, String clave, Cargo cargo) {
        Usuario u = new Usuario();
        u.setCodigo(codigo);
        u.setNombre(nombre);
        u.setClaveAcceso(clave);
        u.setCargo(cargo);
        this.agregarUsuario(u);
        return u;
    }

    public void agregarUsuario(Usuario u) {
        if (this.seguridad.permitidoAdministrarUsuarios() && !this.esUsuario(u)) {
            this.usuarios.add(u);
            u.setEmpresa(this);
            this.persistenciaUsuario(u);
        }
    }

    public Usuario obtenerUsuarioConCodigo(String c) {
        if (this.seguridad.sesionActiva()) {
            for (Usuario u : this.usuarios) {
                if (u.getCodigo().equals(c)) {
                    return u;
                }
            }
        }
        return null;
    }

    public void actualizarUsuario(Usuario u) {
        if (this.seguridad.permitidoAdministrarUsuarios()) {
            if (this.esUsuario(u) && !this.esAdministradorPrincipal(u)) {

                this.modificacionUsuario(u);
            }
        }
    }

    private boolean esAdministradorPrincipal(Usuario u) {
        return u.getCodigo().equals("1");
    }

    public void eliminarUsuario(Usuario u) {
        if (this.seguridad.permitidoAdministrarUsuarios() && this.existeUsuarioConCodigo(u.getCodigo())) {
            this.usuarios.remove(u);
            this.destruccionUsuario(u);
        }
    }

    public boolean existeUsuarioConCodigo(String codigo) {
        for (Usuario u : this.usuarios) {
            if (u.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
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

    public List<Usuario> obtenerUsuarios() {
        if (this.seguridad.sesionActiva()) {
            return this.usuarios;
        }
        return null;
    }

    protected List<Usuario> obtenerUsuarios(ControlAcceso ca) {
        if (this.seguridad.equals(ca)) {
            return this.usuarios;
        }
        return null;
    }
/////////////////////////////////////////////////////// ITEM CATALOGO ////////////////////////////////////////////////////   

    public ItemCatalogo obtenerItemCatalogo(String dato) {
        ItemCatalogo item = null;
        if (this.existeCategoriaConNombre(dato)) {
            item = this.obtenerCategoriaConNombre(dato);
        } else {
            if (this.existeProductoConCodigo(dato)) {
                item = this.obtenerProductoConCodigo(dato);
            }
        }
        return item;
    }
/////////////////////////////////////////////////////// CATEGORIA ////////////////////////////////////////////////////

    public void crearAgregarCategoria(String nombre) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            Categoria c = new Categoria(nombre);
            this.agregarCategoriaEnRaiz(c);
        }
    }

    public void actualizarCategoria(Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (!this.esCategoriaPrincipal(c)) {
                this.modificacionCategoria(c);
            }
        }
    }

    public boolean esCategoriaPrincipal(Categoria c) {
        return c.getNombre().equals("Catálogo");
    }

    public Categoria obtenerCategoriaRaiz() {
        if (this.seguridad.sesionActiva()) {
            return this.catalogo.obtenerCategoriaRaiz();
        }
        return null;
    }

    public List<Categoria> obtenerCategorias() {
        if (this.sesionActiva()) {
            return this.catalogo.obtenerCategorias();
        }
        return null;
    }

    public Categoria obtenerCategoriaConNombre(String nombre) {
        if (this.seguridad.sesionActiva()) {
            return this.catalogo.obtenerCategoriaConNombre(nombre);
        }
        return null;
    }

    public void agregarCategoriaEnRaiz(Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.agregarCategoriaEnRaiz(c)) {
                this.persistenciaCategoria(c);
                this.modificacionCategoria(c);
//                Categoria ca = this.obtenerCategoriaRaiz();
//                for (ItemCatalogo ic : ca.getItems()) {
//                    System.out.println("Item " + ca.getClass());
//                }
//                System.out.println("Cat antes " + c.toString());
//                this.persistenciaCategoria(c);
//                System.out.println("Cat despues " + c.toString());
//                this.modificacionCategoria(c);
//                System.out.println("Cat ultimo " + c.toString());
            }
        }
    }

    public boolean agregarCategoriaEnCategoria(Categoria c_hija, Categoria c_padre) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.agregarCategoriaEnCategoria(c_hija, c_padre)) {
                this.modificacionCategoria(c_hija);
                return true;
            }
        }
        return false;
    }

    public void moverCategoriaHaciaCategoria(Categoria origen, Categoria destino, Categoria objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.catalogo.moverCategoriaHaciaCategoria(origen, destino, objetivo)) {
                this.modificacionCategoria(objetivo);
            }
        }
    }

    public void moverProductoHaciaCategoria(Categoria origen, Categoria destino, Producto objetivo) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(objetivo)) {
                if (this.catalogo.moverProductoHaciaCategoria(origen, destino, objetivo)) {
                    this.modificacionProducto(objetivo);
                }
            }
        }
    }

    public void agregarProductoEnCategoria(Producto p, Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(p)) {
                if (this.catalogo.agregarProductoEnCategoria(p, c)) {
                    this.modificacionProducto(p);
                }
            }
        }
    }

    public void eliminarCategoria(Categoria c) {
        if (this.seguridad.permitidoAdministrarCatalogo() && this.catalogo.eliminarCategoria(c)) {
            this.destruccionCategoria(c);
        }
    }

    public boolean existeCategoriaConNombre(String nombre) {
        if (this.sesionActiva()) {
            return this.catalogo.existeCategoriaConNombre(nombre);
        }
        return false;
    }

/////////////////////////////////////////////////////// PRODUCTO ////////////////////////////////////////////////////
    public void crearAgregarProducto(String codigo, String descripcion, int stock, String pathImagen) {
        if (this.seguridad.permitidoAdministrarCatalogo() && !this.existeProductoConCodigo(codigo)) {
            Producto p = new Producto();
            p.setCodigo(codigo);
            p.setDescripcion(descripcion);
            p.setPathImagen(pathImagen);
            p.setStock(stock);
            this.agregarProducto(p);
        }
    }

    public boolean existeProductoConCodigo(String codigo) {
        for (Producto p : this.productos) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeProducto(Producto p) {
        return this.productos.contains(p);
    }

    public Producto obtenerProductoConCodigo(String codigo) {
        if (this.seguridad.sesionActiva()) {
            for (Producto p : this.productos) {
                if (p.getCodigo().equals(codigo)) {
                    return p;
                }
            }
        }
        return null;
    }

    public List<Producto> obtenerProductos() {
        if (this.seguridad.sesionActiva()) {
            return this.productos;
        }
        return null;
    }

    public void agregarProducto(Producto p) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (!this.existeProducto(p)) {
                this.productos.add(p);
                p.setPadre(this.obtenerCategoriaRaiz());
                this.persistenciaProducto(p);
                this.modificacionProducto(p);
            }
        }
    }

    public void actualizarProducto(Producto p) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(p)) {
                this.modificacionProducto(p);
            }
        }
    }

    public void eliminarProducto(Producto p) {
        if (this.seguridad.permitidoAdministrarCatalogo()) {
            if (this.existeProducto(p)) {
                this.productos.remove(p);
                this.destruccionProducto(p);
            }
        }
    }
/////////////////////////////////////////////////////// ORDEN DE COMPRA  ////////////////////////////////////////////////////

    public void crearOrdenCompraParaUsuarioActivo(String nombreComprador, String direccionComprador) {
        if (this.seguridad.permitidoHacerPedidos()) {
            this.seguridad.crearOrdenCompraParaUsuarioActivo(nombreComprador, direccionComprador);
        }
    }

    public void agregarOrdenCompraParaUsuarioActivo(OrdenCompra oc) {
        if (this.seguridad.permitidoHacerPedidos()) {
            if (this.seguridad.agregarOrdenCompraParaUsuarioActivo(oc) && !this.existeOrdenCompra(oc)) {
                this.agregarOrdenCompra(oc);
            }
        }
    }

    protected void agregarOrdenCompra(OrdenCompra oc) {
        this.ordenesCompra.add(oc);
        this.persistenciaOrdenCompra(oc);
    }

    public boolean existeOrdenCompra(OrdenCompra oc) {
        return this.ordenesCompra.contains(oc);
    }
//       public void eliminarOrdenCompra(OrdenCompra oc) {
//        if (this.seguridad.permitidoHacerPedidos()) {
//            if (this.seguridad.agregarOrdenCompraParaUsuarioActivo(oc)) {
//                this.persistenciaOrdenCompra(oc);
//            }
//        }
//    }

    public List<OrdenCompra> obtenerOrdenesCompraDeUsuarioActivo() {
        if (this.seguridad.permitidoHacerPedidos()) {
            return this.seguridad.obtenerOrdenesCompraDeUsuarioActivo();
        }
        return null;
    }

    public void agregarArticuloParaOrdenCompraUsuarioActivo(Articulo articulo, OrdenCompra ordenCompra) {
        if (this.seguridad.permitidoHacerPedidos()) {
            if (this.seguridad.agregarArticuloParaOrdenCompraUsuarioActivo(articulo, ordenCompra)) {
                this.modificacionOrdenCompra(ordenCompra);
                this.persistenciaArticulo(articulo);
            }
        }
    }

    public void eliminarArticuloParaOrdenCompraUsuarioActivo(Articulo articulo, OrdenCompra ordenCompra) {
        if (this.seguridad.permitidoHacerPedidos()) {
            if (this.seguridad.eliminarArticuloParaOrdenCompraUsuarioActivo(articulo, ordenCompra)) {
                this.modificacionOrdenCompra(ordenCompra);
                this.destruccionArticulo(articulo);
            }
        }
    }

/////////////////////////////////////////////////////// PERSISTENCIA ////////////////////////////////////////////////////
    private void persistenciaUsuario(Usuario u) {
        CtrlPersisUsuario cp = this.persistencia.getCpUsuario();
        cp.crear(u);
    }

    private void modificacionUsuario(Usuario u) {
        try {
            CtrlPersisUsuario cp = this.persistencia.getCpUsuario();
            cp.editar(u);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruccionUsuario(Usuario u) {
        try {
            CtrlPersisUsuario cp = this.persistencia.getCpUsuario();
            cp.destruir(u.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistenciaCategoria(Categoria c) {
        CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
        cp.crear(c);
    }

    private void modificacionCategoria(Categoria c) {
        try {
            CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
            cp.editar(c);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruccionCategoria(Categoria c) {
        try {
            CtrlPersisCategoria cp = this.persistencia.getCpCategoria();
            cp.destruir(c.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistenciaProducto(Producto p) {
        CtrlPersisProducto cp = this.persistencia.getCpProducto();
        cp.crear(p);
    }

    private void modificacionProducto(Producto p) {
        try {
            CtrlPersisProducto cp = this.persistencia.getCpProducto();
            cp.editar(p);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruccionProducto(Producto p) {
        try {
            CtrlPersisProducto cp = this.persistencia.getCpProducto();
            cp.destruir(p.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistenciaOrdenCompra(OrdenCompra oc) {
        CtrlPersisOrdenCompra cp = this.persistencia.getCpOrdenCompra();
        cp.crear(oc);
    }

    private void modificacionOrdenCompra(OrdenCompra oc) {
        try {
            CtrlPersisOrdenCompra cp = this.persistencia.getCpOrdenCompra();
            cp.editar(oc);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruccionOrdenCompra(OrdenCompra oc) {
        try {
            CtrlPersisOrdenCompra cp = this.persistencia.getCpOrdenCompra();
            cp.destruir(oc.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persistenciaArticulo(Articulo a) {
        CtrlPersisArticulo cp = this.persistencia.getCpArticulo();
        cp.crear(a);
    }

    private void modificacionArticulo(Articulo a) {
        try {
            CtrlPersisArticulo cp = this.persistencia.getCpArticulo();
            cp.editar(a);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruccionArticulo(Articulo a) {
        try {
            CtrlPersisArticulo cp = this.persistencia.getCpArticulo();
            cp.destruir(a.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
