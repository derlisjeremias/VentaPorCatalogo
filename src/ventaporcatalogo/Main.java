package ventaporcatalogo;

import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class Main {

    public static void main(String[] args) {
        EntornoGrafico.seleccionarWindows();
        Empresa e = new Empresa();

        Usuario u = e.obtenerUsuarioConCodigo("1");
        if (u == null) {
            e.crearAgregarUsuarioAdministrador("1", "admin", "admin");
        }



        NewJFrame frame = new NewJFrame(e);
        frame.setVisible(true);


        e.finCargaSistema();
        e.iniciarSesion("admin", "admin");
        System.out.println("Sesion iniciada " + e.sesionActiva());

    }
}
