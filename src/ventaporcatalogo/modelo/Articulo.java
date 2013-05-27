package ventaporcatalogo.modelo;

/**
 *
 * @author Jere
 */
public class Articulo {

    private String codigo;
    private String descripcion;
    private int cantidad;

    public Articulo(Producto p, int cant) {
        this.cantidad = cant;
        this.codigo = p.getCodigo();
        this.descripcion = p.getDescripcion();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
