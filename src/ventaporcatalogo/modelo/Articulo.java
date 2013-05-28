package ventaporcatalogo.modelo;

import ventaporcatalogo.modelo.catalogo.Producto;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jere
 */
@Entity
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codigo;
    private String descripcion;
    private int cantidad;

    public Articulo() {
    }

    public Articulo(Producto p, int cant) {
        this.cantidad = cant;
        this.codigo = p.getCodigo();
        this.descripcion = p.getDescripcion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
