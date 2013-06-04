package ventaporcatalogo.modelo;

import java.io.Serializable;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    private Long id;
    private String codigo;
    private String nombre;
    private String claveAcceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<OrdenCompra> ordenesCompra;
    @OneToOne(cascade = CascadeType.ALL)
    private Cargo cargo;
    @Transient
    private Empresa empresa;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
//        cargo.setUsuario(this);
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public List<OrdenCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }

    public boolean comprobarClave(String c) {
        return this.claveAcceso.equals(c);
    }

    public boolean esUsuarioDe(Empresa e) {
        if (this.empresa == null) {
            return false;
        }
        return this.empresa.equals(e);
    }

    public boolean comprobarUsuario(String n, String c) {
        return (this.nombre.equals(n) && this.comprobarClave(c));
    }

    public boolean permitidoAdministrarUsuarios() {
        return this.cargo.permitidoAdministrarUsuarios();
    }

    public boolean permitidoAdministrarCatalogo() {
        return this.cargo.permitidoAdministrarCatalogo();
    }

    public boolean permitidoHacerPedidos() {
        return this.cargo.permitidoHacerPedidos();
    }

    public boolean agregarOrdenCompra(OrdenCompra oc) {
        if (this.codigo.equals(oc.getCodigoUsuario()) && !this.existeOrdenCompra(oc)) {
            this.ordenesCompra.add(oc);
            return true;
        }
        return false;
    }

    public List<OrdenCompra> obtenerOrdenesCompra() {
        return this.ordenesCompra;
    }

    public boolean agregarArticuloParaOrdenCompra(Articulo articulo, OrdenCompra ordenCompra) {
        if (this.ordenesCompra.contains(ordenCompra)) {
            return ordenCompra.agregarArticulo(articulo);
        }
        return false;
    }

    public boolean eliminarArticuloParaOrdenCompra(Articulo articulo, OrdenCompra ordenCompra) {
        if (this.ordenesCompra.contains(ordenCompra)) {
            return ordenCompra.eliminarArticulo(articulo);
        }
        return false;
    }

    public void crearOrdenCompra(String nombreComprador, String direccionComprador) {
        OrdenCompra oc = new OrdenCompra(this);
        oc.setNombreComprador(nombreComprador);
        oc.setDireccionComprador(direccionComprador);
        this.empresa.actualizarUsuario(this);
        this.empresa.agregarOrdenCompra(oc);
        this.agregarOrdenCompra(oc);
    }

    public boolean existeOrdenCompra(OrdenCompra oc) {
        return this.ordenesCompra.contains(oc);
    }
}
