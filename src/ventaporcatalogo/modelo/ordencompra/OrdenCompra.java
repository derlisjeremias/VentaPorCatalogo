package ventaporcatalogo.modelo.ordencompra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ventaporcatalogo.modelo.Articulo;
import ventaporcatalogo.modelo.Producto;
import ventaporcatalogo.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class OrdenCompra {

    private String codigo;
    private String codigoUsuario;
    private String nombreComprador;
    private String direccionComprador;
    private List<Articulo> articulos;
    private EstadoOrdenCompra estado;
    private Usuario usuario;

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
        String codigo = Long.toString(hora);
        return codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.estado.setCodigo(codigo);
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.estado.setCodigoUsuario(codigoUsuario);
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.estado.setNombreComprador(nombreComprador);
    }

    public String getDireccionComprador() {
        return direccionComprador;
    }

    public void setDireccionComprador(String direccionComprador) {
        this.estado.setDireccionComprador(direccionComprador);
    }

    protected void setCodigo(String c, EstadoEditable e) {
    }

    protected void setCodigoUsuario(String cu, EstadoOrdenCompra e) {
        if (e.equals(this.estado)) {
            this.codigoUsuario = cu;
        }
    }

    protected void setNombreComprador(String nc, EstadoOrdenCompra e) {
        if (e.equals(this.estado)) {
            this.nombreComprador = nc;
        }
    }

    protected void setDireccionComprador(String dc, EstadoOrdenCompra e) {
        if (e.equals(this.estado)) {
            this.direccionComprador = dc;
        }
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
        Articulo aux = null;
        for (Articulo a : this.articulos) {
            if (a.getCodigo().equals(cod)) {
                aux = a;
                break;
            }
        }
        return aux;
    }

    public void agregarArticulo(Producto p, int cant) {
        this.estado.agregarArticulo(p, cant);
    }

    protected void agregarArticulo(Producto p, int cant, EstadoOrdenCompra e) {
        if (e.equals(this.estado)) {
            if (!this.existeArticulo(p.getCodigo())) {
                Articulo a = new Articulo(p, cant);
                this.articulos.add(a);
            } else {
                Articulo a = this.obtenerArticuloConCodigo(p.getCodigo());
                int total = a.getCantidad();
                total += cant;
                a.setCantidad(total);
            }
        }
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
