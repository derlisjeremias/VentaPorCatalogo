package ventaporcatalogo.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Vendedor extends Cargo implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    public Vendedor() {
        this.nombre = "Vendedor";
    }

    @Override
    public boolean permitidoAdministrarUsuarios() {
        return false;
    }

    @Override
    public boolean permitidoAdministrarCatalogo() {
        return false;
    }

    @Override
    public boolean permitidoHacerPedidos() {
        return true;
    }

}
