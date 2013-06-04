package ventaporcatalogo;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jere
 */
public class EntornoGrafico {

    public static void seleccionarWindows() {
        /*
         * LookAndFeel disponibles
         * Metal
         * Nimbus
         * CDE/Motif
         * Windows
         * Windows Classic
         * 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntornoGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
