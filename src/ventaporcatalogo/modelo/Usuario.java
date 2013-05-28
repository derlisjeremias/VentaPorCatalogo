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
    @GeneratedValue
    private Long id;
    private String codigo;
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL)
    private Cargo cargo;
    @ManyToOne
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
        cargo.setUsuario(this);
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void asignarClaveAcceso(String c) {
        this.cargo.asignarClaveAcceso(c);
    }

    public boolean comprobarClave(String c) {
        return this.cargo.comprobarClave(c);
    }

    public boolean esUsuarioDe(Empresa e) {
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
        if (this.codigo.equals(oc.getCodigoUsuario())) {
           return this.cargo.agregarOrdenCompra(oc);
        }
        return false;
    }

    public List<OrdenCompra> obtenerOrdenesCompra() {
        return this.cargo.obtenerOrdenesCompra();
    }
}
