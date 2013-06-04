package ventaporcatalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Administrador extends Cargo implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    public Administrador() {
        this.nombre = "Administrador";
    }

    @Override
    public boolean permitidoAdministrarUsuarios() {
        return true;
    }

    @Override
    public boolean permitidoAdministrarCatalogo() {
        return true;
    }

    @Override
    public boolean permitidoHacerPedidos() {
        return false;
    }
}
