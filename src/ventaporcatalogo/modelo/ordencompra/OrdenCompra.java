package ventaporcatalogo.modelo.ordencompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import ventaporcatalogo.modelo.*;

/**
 *
 * @author Jere
 */
@Entity
public class OrdenCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codigo;
    private String codigoUsuario;
    private String nombreComprador;
    private String direccionComprador;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Articulo> articulos;
    @OneToOne
    private EstadoOrdenCompra estado;
    @ManyToOne
    @JoinTable(name = "ORDENCOMPRA_USUARIO",
            joinColumns =
            @JoinColumn(name = "ORDENCOMPRA_ID"),
            inverseJoinColumns =
            @JoinColumn(name = "USUARIO_ID"))
    private Usuario usuario;

    public OrdenCompra() {
    }

    public OrdenCompra(Usuario u) {
        this.codigo = this.generarCodigo();
        this.codigoUsuario = u.getCodigo();
        this.nombreComprador = new String();
        this.direccionComprador = new String();
        this.articulos = new ArrayList();
        this.estado = new EstadoEditable(this);
        this.usuario = u;
    }

    private String generarCodigo() {
        Date fecha = new Date();
        long hora = fecha.getTime();
        String c = Long.toString(hora);
        return c;
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
        if (this.estado.permiteModicifacion()) {
            this.codigo = codigo;
        }
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        if (this.estado.permiteModicifacion()) {
            this.codigoUsuario = codigoUsuario;
        }
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        if (this.estado.permiteModicifacion()) {
            this.nombreComprador = nombreComprador;
        }
    }

    public String getDireccionComprador() {
        return direccionComprador;
    }

    public void setDireccionComprador(String direccionComprador) {
        if (this.estado.permiteModicifacion()) {
            this.direccionComprador = direccionComprador;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipoEstado() {
        return this.estado.toString();
    }

    public List<Articulo> obtenerArticulos() {
        return articulos;
    }

    private boolean existeArticulo(String cod) {
        for (Articulo a : this.articulos) {
            if (a.getCodigo().equals(cod)) {
                return true;
            }
        }
        return false;
    }

    private Articulo obtenerArticuloConCodigo(String cod) {
        for (Articulo a : this.articulos) {
            if (a.getCodigo().equals(cod)) {
                return a;
            }
        }
        return null;
    }

    public boolean agregarArticulo(Articulo a) {
        if (this.estado.permiteModicifacion()) {
            if (!this.existeArticulo(a.getCodigo())) {
                this.articulos.add(a);
                return true;
            } else {
                Articulo articEncontrado = this.obtenerArticuloConCodigo(a.getCodigo());
                articEncontrado.setCantidad(a.getCantidad() + articEncontrado.getCantidad());
                return true;
            }
        }
        return false;
    }

    public boolean eliminarArticulo(Articulo a) {
        if (this.estado.permiteModicifacion()) {
            if (this.existeArticulo(a.getCodigo())) {
                this.articulos.remove(a);
                return true;
            }
        }
        return false;
    }

    public void cerrarOrdenCompra() {
        this.estado.cerrarOrdenCompra();
    }

    protected void cerrar() {
        this.estado = new EstadoCerrado(this);
    }

    public void abrirOrdenCompra() {
        this.estado.abrirOrdenCompra();
    }

    protected void abrir() {
        this.estado = new EstadoEditable(this);
    }

    public void archivarOrdenCompra() {
        this.estado.archivarOrdenCompra();
    }

    protected void archivar() {
        this.estado = new EstadoArchivado(this);
    }
}
