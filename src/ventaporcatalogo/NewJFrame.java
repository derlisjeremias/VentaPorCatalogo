/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventaporcatalogo;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ventaporcatalogo.modelo.Administrador;
import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.Usuario;
import ventaporcatalogo.modelo.Vendedor;
import ventaporcatalogo.modelo.catalogo.Categoria;
import ventaporcatalogo.modelo.catalogo.ItemCatalogo;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
public class NewJFrame extends javax.swing.JFrame {

    private Empresa modeloApp;
    private TablaUsuarios conectorTablaUsuarios;
    private TablaCategorias conectorTablaCategorias;
    private TablaProductos conectorTablaProductos;
    private ArbolCatalogo conectorArbolCatalogo;
    ///////////////////////////////////////////////////////////////////////////
    private int filaSeleccionadaDeTablaAdministracionUsuarios = -1;
    private boolean editandoUsuario = false;
    private String codigoOrigianlUsuario;
    //////////////////////////////////////////////////////////////////////////
    private int filaSeleccionadaDeTablaAdministracionCategorias = -1;
    private boolean editandoCategoria = false;
    private String nombreOriginalCategoria;
    ////////////////////////////////////////////////////////////////////////
    private int filaSeleccionadaDeTablaAdministracionProductos = -1;
    private boolean editandoProducto = false;
    private String codigoOriginalProducto;
    ////////////////////////////////////////////////////////////////////////
    private int filaSeleccionadaDeTablaProductosOrigen = -1;
    private int filaSeleccionadaDeTablaCategoriasOrigen = -1;
    private int filaSeleccionadaDeTablaCategoriasDestino = -1;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame(Empresa e) {
        this.modeloApp = e;
        this.conectorTablaCategorias = new TablaCategorias(this.modeloApp);
        this.conectorTablaProductos = new TablaProductos(this.modeloApp);
        this.conectorTablaUsuarios = new TablaUsuarios(this.modeloApp);
        this.conectorArbolCatalogo = new ArbolCatalogo(this.modeloApp);

        initComponents();

        this.bg.add(this.jRadioButton4);
        this.bg.add(this.jRadioButton5);
        this.bg.add(this.jRadioButton6);

        this.cargarUsuarios();
        this.inicializarAdministrarUsuarios();

        this.cargarCategorias();
        this.inicializarAdministrarCategorias();

        this.cargarProductos();
        this.inicializarAdministrarProductos();

    }

    private void cargarUsuarios() {
        this.conectorTablaUsuarios.addTableModelListener(tablaAdministracionUsuarios);
        this.conectorTablaUsuarios.cargarUsuarios();
    }

    private void inicializarAdministrarUsuarios() {
        this.deshabilitarPanelAgregarUsuario();
        this.deshabilitarPanelEditarUsuario();
    }

    private void deshabilitarPanelAgregarUsuario() {
        this.cambiarEstadoPanel(this.panelAgregarUsuario, false);
    }

    private void habilitarPanelAgregarUsuario() {
        this.cambiarEstadoPanel(this.panelAgregarUsuario, true);
    }

    private void deshabilitarPanelEditarUsuario() {
        this.cambiarEstadoPanel(this.panelEditarUsuario, false);
    }

    private void habilitarPanelEditarUsuario() {
        this.cambiarEstadoPanel(this.panelEditarUsuario, true);
    }

    private void cambiarEstadoPanel(JPanel p, boolean e) {
        Component[] lista_c = p.getComponents();
        p.setEnabled(e);
        for (Component c : lista_c) {
            c.setEnabled(e);
        }
    }

    private void inicializarAdministrarCategorias() {
        this.deshabilitarPanelAgregarCategoria();
        this.deshabilitarPanelEditarCategoria();
    }

    private void deshabilitarPanelAgregarCategoria() {
        this.cambiarEstadoPanel(panelAgregarCategoria, false);
    }

    private void habilitarPanelAgregarCategoria() {
        this.cambiarEstadoPanel(panelAgregarCategoria, true);
    }

    private void deshabilitarPanelEditarCategoria() {
        this.cambiarEstadoPanel(panelEditarCategoria, false);
    }

    private void habilitarPanelEditarCategoria() {
        this.cambiarEstadoPanel(panelEditarCategoria, true);
    }

    private void cargarCategorias() {
        this.conectorTablaCategorias.addTableModelListener(this.tablaCategoriasDestino);
        this.conectorTablaCategorias.addTableModelListener(this.tablaCategoriasOrigen);
        this.conectorTablaCategorias.addTableModelListener(this.tablaAdministracionCategorias);
        this.conectorTablaCategorias.cargarCategorias();
    }

    private void inicializarAdministrarProductos() {
        this.deshabilitarPanelAgregarProducto();
        this.deshabilitarPanelEditarProducto();
    }

    private void deshabilitarPanelAgregarProducto() {
        this.cambiarEstadoPanel(panelAgregarProducto, false);
    }

    private void deshabilitarPanelEditarProducto() {
        this.cambiarEstadoPanel(panelEditarProducto, false);
    }

    private void habilitarPanelAgregarProducto() {
        this.cambiarEstadoPanel(panelAgregarProducto, true);
    }

    private void habilitarPanelEditarProducto() {
        this.cambiarEstadoPanel(panelEditarProducto, true);
    }

    private void cargarProductos() {
        this.conectorTablaProductos.addTableModelListener(this.tablaAdministracionProductos);
        this.conectorTablaProductos.addTableModelListener(this.tablaProductosOrigen);
        this.conectorTablaProductos.cargarProductos();

    }

    private void restablecerCamposAgregarUsuario() {
        this.campoCodigoAgregarUsuario.setText("Codigo");
        this.campoNombreAgregarUsuario.setText("Nombre");
        this.campoClaveAgregarUsuario.setText("Clave");
        this.comboboxCargoAgregarUsuario.setSelectedIndex(0);
    }

    private void restablecerCamposEditarUsuario() {
        this.campoCodigoEditarUsuario.setText("Codigo");
        this.campoNombreEditarUsuario.setText("Nombre");
        this.campoClaveEditarUsuario.setText("Clave");
        this.comboboxCargoEditarUsuario.setSelectedIndex(0);
    }

    private void restablecerCamposAgregarCategoria() {
        this.campoNombreAgregarCategoria.setText("Nombre");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel24 = new PanelImagenProducto();
        jLabel22 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTree3 = new javax.swing.JTree();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jPanel25 = new PanelImagenProducto();
        jLabel23 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel26 = new PanelImagenProducto();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pestaniaAdministrar = new javax.swing.JTabbedPane();
        pestaniaAdministrarCatalogo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCategoriasDestino = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaProductosOrigen = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCategoriasOrigen = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        botonInsertarAdministrarCatalogo = new javax.swing.JButton();
        campoOrigenAdministrarCatalogo = new javax.swing.JTextField();
        campoDestinoAdministrarCatalogo = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        arbolAdministrarCatalogo = new javax.swing.JTree();
        jLabel6 = new javax.swing.JLabel();
        botonMoverAdministrarCatalogo = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdministracionProductos = new javax.swing.JTable();
        panelAgregarProducto = new javax.swing.JPanel();
        campoCodigoAgregarProducto = new javax.swing.JTextField();
        campoDescripcionAgregarProducto = new javax.swing.JTextField();
        campoStockAgregarProducto = new javax.swing.JTextField();
        botonAgregarProducto = new javax.swing.JButton();
        botonCancelarAgregarProducto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboboxImagenAgregarProducto = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        panelEditarProducto = new javax.swing.JPanel();
        campoCodigoEditarProducto = new javax.swing.JTextField();
        campoDescripcionEditarProducto = new javax.swing.JTextField();
        campoStockEditarProducto = new javax.swing.JTextField();
        botonActualizarProducto = new javax.swing.JButton();
        botonCancelarActualizarProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        comboboxImagenEditarProducto = new javax.swing.JComboBox();
        botonEliminarProducto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        panelImagenAdministrarProductos = new PanelImagenProducto();
        jLabel21 = new javax.swing.JLabel();
        botonPanelAgregarProducto = new javax.swing.JButton();
        botonPanelEditarProducto = new javax.swing.JButton();
        pestaniaAdministrarCategorias = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaAdministracionCategorias = new javax.swing.JTable();
        botonEliminarCategoria = new javax.swing.JButton();
        botonPanelAgregarCategoria = new javax.swing.JButton();
        panelAgregarCategoria = new javax.swing.JPanel();
        campoNombreAgregarCategoria = new javax.swing.JTextField();
        botonAgregarCategoria = new javax.swing.JButton();
        botonCancelarAgregarCategoria = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        panelEditarCategoria = new javax.swing.JPanel();
        campoNombreEditarCategoria = new javax.swing.JTextField();
        botonActualizarCategoria = new javax.swing.JButton();
        botonCancelarActualizarCategoria = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonPanelEditarCategoria = new javax.swing.JButton();
        pestaniaAdministrarUsuarios = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaAdministracionUsuarios = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        panelAgregarUsuario = new javax.swing.JPanel();
        campoCodigoAgregarUsuario = new javax.swing.JTextField();
        campoNombreAgregarUsuario = new javax.swing.JTextField();
        comboboxCargoAgregarUsuario = new javax.swing.JComboBox();
        botonAgregarUsuario = new javax.swing.JButton();
        botonCancelarAgregarUsuario = new javax.swing.JButton();
        campoClaveAgregarUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        panelEditarUsuario = new javax.swing.JPanel();
        campoCodigoEditarUsuario = new javax.swing.JTextField();
        campoNombreEditarUsuario = new javax.swing.JTextField();
        comboboxCargoEditarUsuario = new javax.swing.JComboBox();
        botonActualizarUsuario = new javax.swing.JButton();
        botonCancelarActualizarUsuario = new javax.swing.JButton();
        campoClaveEditarUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        botonEliminarUsuario = new javax.swing.JButton();
        botonPanelAgregarUsuario = new javax.swing.JButton();
        botonPanelEditarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Venta de productos por catálogo");
        setIconImages(null);
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField1.setText("Nombre de usuario");

        jTextField2.setText("Clave de acceso");

        jButton1.setText("Acceder");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );

        panelPrincipal.addTab("Sesión de usuario", jPanel1);

        jLabel12.setText("Catálogo");

        jTree1.setModel(this.conectorArbolCatalogo);
        jScrollPane1.setViewportView(jTree1);

        jButton2.setText("Agregar producto seleccionado  ►");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField6.setText("Dirección");

        jTextField5.setText("Nombre");

        jLabel11.setText("Datos del comprador");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 88, Short.MAX_VALUE))
                    .addComponent(jTextField5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextField3.setText("Código de orden de compra");

        jTextField4.setText("Código de usuario");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jRadioButton4.setText("Editable");

        jRadioButton5.setText("Cerrada");

        jRadioButton6.setText("Archivada");

        jLabel13.setText("Estado de orden de compra");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton4)
                        .addGap(27, 27, 27)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jRadioButton6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("Guardar orden de compra");

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setText("Imagen");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addContainerGap())
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Nueva orden de compra", jPanel18);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Comprador"
            }
        ));
        jScrollPane10.setViewportView(jTable8);

        jLabel15.setText("Ordenes guardadas");

        jTree3.setModel(this.conectorArbolCatalogo);
        jScrollPane11.setViewportView(jTree3);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Cantidad"
            }
        ));
        jScrollPane12.setViewportView(jTable9);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jRadioButton1.setText("Editable");

        jRadioButton2.setText("Cerrada");

        jRadioButton3.setText("Archivada");

        jLabel16.setText("Estado de orden de compra");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap())
        );

        jButton26.setText("Guardar orden de compra");

        jButton27.setText("Agregar producto ►");

        jButton28.setText("Editar orden ►");

        jLabel17.setText("Catálogo");

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setText("Datos del comprador");

        jTextField25.setText("Nombre");

        jTextField26.setText("Dirección");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25)
                    .addComponent(jTextField26, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField27.setText("Código de usuario");

        jTextField28.setText("Código de orden de compra");

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setText("Imagen");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton31.setText("Eliminar orden");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jScrollPane11)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(48, 48, 48)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField27)
                                        .addComponent(jTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton26)
                                .addGap(19, 19, 19)))))
                .addGap(24, 24, 24))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton28))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jButton26)
                                .addGap(24, 24, 24))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Editar orden de compra", jPanel19);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Comprador"
            }
        ));
        jScrollPane13.setViewportView(jTable10);

        jLabel19.setText("Ordenes de compra archivadas");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Cantidad"
            }
        ));
        jScrollPane14.setViewportView(jTable11);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Datos del comprador");

        jTextField29.setText("Nombre");

        jTextField30.setText("Dirección");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField29)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField30))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField31.setText("Código de usuario");

        jTextField32.setText("Código de orden de compra");

        jButton29.setText("Cargar orden de compra seleccionada ►");

        jButton30.setText("Aceptar");

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setText("Imagen");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane14)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(jButton30)
                                .addGap(215, 215, 215)))))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(7, 7, 7)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton29)
                    .addComponent(jButton30))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Archivo de ordenes de compra", jPanel20);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        panelPrincipal.addTab("Ordenes de compra", jPanel17);

        tablaCategoriasDestino.setModel(this.conectorTablaCategorias);
        tablaCategoriasDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaCategoriasDestinoMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tablaCategoriasDestino);

        jLabel3.setText("Categorias");

        tablaProductosOrigen.setModel(this.conectorTablaProductos);
        tablaProductosOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProductosOrigenMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tablaProductosOrigen);

        jLabel4.setText("Productos");

        tablaCategoriasOrigen.setModel(this.conectorTablaCategorias);
        tablaCategoriasOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaCategoriasOrigenMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tablaCategoriasOrigen);

        jLabel5.setText("Categorias");

        botonInsertarAdministrarCatalogo.setText("Insertar en ►");
        botonInsertarAdministrarCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarAdministrarCatalogoActionPerformed(evt);
            }
        });

        campoOrigenAdministrarCatalogo.setEditable(false);
        campoOrigenAdministrarCatalogo.setText("Origen");

        campoDestinoAdministrarCatalogo.setEditable(false);
        campoDestinoAdministrarCatalogo.setText("Destino");

        arbolAdministrarCatalogo.setModel(this.conectorArbolCatalogo);
        jScrollPane7.setViewportView(arbolAdministrarCatalogo);

        jLabel6.setText("Catálogo");

        botonMoverAdministrarCatalogo.setText("Mover a ►");
        botonMoverAdministrarCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMoverAdministrarCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestaniaAdministrarCatalogoLayout = new javax.swing.GroupLayout(pestaniaAdministrarCatalogo);
        pestaniaAdministrarCatalogo.setLayout(pestaniaAdministrarCatalogoLayout);
        pestaniaAdministrarCatalogoLayout.setHorizontalGroup(
            pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(campoOrigenAdministrarCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(campoDestinoAdministrarCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))))
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonInsertarAdministrarCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(botonMoverAdministrarCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pestaniaAdministrarCatalogoLayout.setVerticalGroup(
            pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestaniaAdministrarCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoDestinoAdministrarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoOrigenAdministrarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonInsertarAdministrarCatalogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(botonMoverAdministrarCatalogo))
                    .addGroup(pestaniaAdministrarCatalogoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)))
                .addContainerGap())
        );

        pestaniaAdministrar.addTab("Administrar catálogo", pestaniaAdministrarCatalogo);

        tablaAdministracionProductos.setModel(this.conectorTablaProductos);
        tablaAdministracionProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdministracionProductosMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAdministracionProductos);

        panelAgregarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        campoCodigoAgregarProducto.setText("Código");

        campoDescripcionAgregarProducto.setText("Descripción");

        campoStockAgregarProducto.setText("Stock");

        botonAgregarProducto.setText("Agregar");
        botonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProductoActionPerformed(evt);
            }
        });

        botonCancelarAgregarProducto.setText("Cancelar");
        botonCancelarAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarAgregarProductoActionPerformed(evt);
            }
        });

        jLabel1.setText("Agregar producto");

        comboboxImagenAgregarProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ningúna", "Crema", "Jabón", "Loción" }));

        jLabel27.setText("Imagen");

        javax.swing.GroupLayout panelAgregarProductoLayout = new javax.swing.GroupLayout(panelAgregarProducto);
        panelAgregarProducto.setLayout(panelAgregarProductoLayout);
        panelAgregarProductoLayout.setHorizontalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoStockAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoDescripcionAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoCodigoAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                        .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                                .addComponent(botonAgregarProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonCancelarAgregarProducto))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarProductoLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxImagenAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAgregarProductoLayout.setVerticalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCodigoAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDescripcionAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoStockAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxImagenAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAgregarProducto)
                    .addComponent(botonCancelarAgregarProducto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEditarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        campoCodigoEditarProducto.setText("Código");

        campoDescripcionEditarProducto.setText("Descripción");

        campoStockEditarProducto.setText("Stock");

        botonActualizarProducto.setText("Actualizar");
        botonActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarProductoActionPerformed(evt);
            }
        });

        botonCancelarActualizarProducto.setText("Cancelar");
        botonCancelarActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActualizarProductoActionPerformed(evt);
            }
        });

        jLabel2.setText("Editar producto");

        jLabel28.setText("Imagen");

        comboboxImagenEditarProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ningúna", "Crema", "Jabón", "Loción" }));

        javax.swing.GroupLayout panelEditarProductoLayout = new javax.swing.GroupLayout(panelEditarProducto);
        panelEditarProducto.setLayout(panelEditarProductoLayout);
        panelEditarProductoLayout.setHorizontalGroup(
            panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoStockEditarProducto)
                    .addComponent(campoDescripcionEditarProducto)
                    .addComponent(campoCodigoEditarProducto)
                    .addGroup(panelEditarProductoLayout.createSequentialGroup()
                        .addGroup(panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelEditarProductoLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxImagenEditarProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelEditarProductoLayout.createSequentialGroup()
                                .addComponent(botonActualizarProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonCancelarActualizarProducto))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEditarProductoLayout.setVerticalGroup(
            panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarProductoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCodigoEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDescripcionEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoStockEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(comboboxImagenEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEditarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonActualizarProducto)
                    .addComponent(botonCancelarActualizarProducto))
                .addContainerGap())
        );

        botonEliminarProducto.setText("Eliminar producto");
        botonEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProductoActionPerformed(evt);
            }
        });

        jLabel14.setText("Productos");

        panelImagenAdministrarProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setText("Imagen");

        javax.swing.GroupLayout panelImagenAdministrarProductosLayout = new javax.swing.GroupLayout(panelImagenAdministrarProductos);
        panelImagenAdministrarProductos.setLayout(panelImagenAdministrarProductosLayout);
        panelImagenAdministrarProductosLayout.setHorizontalGroup(
            panelImagenAdministrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenAdministrarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImagenAdministrarProductosLayout.setVerticalGroup(
            panelImagenAdministrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenAdministrarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonPanelAgregarProducto.setText("Agregar producto");
        botonPanelAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelAgregarProductoActionPerformed(evt);
            }
        });

        botonPanelEditarProducto.setText("Editar producto");
        botonPanelEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelEditarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botonEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelImagenAdministrarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(panelAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonPanelAgregarProducto))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botonPanelEditarProducto)
                            .addComponent(panelEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(panelImagenAdministrarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonPanelAgregarProducto)
                            .addComponent(botonPanelEditarProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelEditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminarProducto)
                .addContainerGap())
        );

        pestaniaAdministrar.addTab("Administrar productos", jPanel8);

        tablaAdministracionCategorias.setModel(this.conectorTablaCategorias);
        tablaAdministracionCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdministracionCategoriasMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(tablaAdministracionCategorias);

        botonEliminarCategoria.setText("Eliminar categoría");
        botonEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarCategoriaActionPerformed(evt);
            }
        });

        botonPanelAgregarCategoria.setText("Agregar categoría");
        botonPanelAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelAgregarCategoriaActionPerformed(evt);
            }
        });

        panelAgregarCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        campoNombreAgregarCategoria.setText("Nombre");

        botonAgregarCategoria.setText("Agregar");
        botonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarCategoriaActionPerformed(evt);
            }
        });

        botonCancelarAgregarCategoria.setText("Cancelar");
        botonCancelarAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarAgregarCategoriaActionPerformed(evt);
            }
        });

        jLabel26.setText("Agregar categoría");

        javax.swing.GroupLayout panelAgregarCategoriaLayout = new javax.swing.GroupLayout(panelAgregarCategoria);
        panelAgregarCategoria.setLayout(panelAgregarCategoriaLayout);
        panelAgregarCategoriaLayout.setHorizontalGroup(
            panelAgregarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNombreAgregarCategoria)
                    .addGroup(panelAgregarCategoriaLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelAgregarCategoriaLayout.createSequentialGroup()
                        .addComponent(botonAgregarCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(botonCancelarAgregarCategoria)))
                .addContainerGap())
        );
        panelAgregarCategoriaLayout.setVerticalGroup(
            panelAgregarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(36, 36, 36)
                .addComponent(campoNombreAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelAgregarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregarCategoria)
                    .addComponent(botonCancelarAgregarCategoria))
                .addContainerGap())
        );

        panelEditarCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelEditarCategoria.setPreferredSize(new java.awt.Dimension(385, 190));

        campoNombreEditarCategoria.setText("Nombre");

        botonActualizarCategoria.setText("Actualizar");
        botonActualizarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarCategoriaActionPerformed(evt);
            }
        });

        botonCancelarActualizarCategoria.setText("Cancelar");
        botonCancelarActualizarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActualizarCategoriaActionPerformed(evt);
            }
        });

        jLabel25.setText("Actualizar categoria");

        javax.swing.GroupLayout panelEditarCategoriaLayout = new javax.swing.GroupLayout(panelEditarCategoria);
        panelEditarCategoria.setLayout(panelEditarCategoriaLayout);
        panelEditarCategoriaLayout.setHorizontalGroup(
            panelEditarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNombreEditarCategoria)
                    .addGroup(panelEditarCategoriaLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditarCategoriaLayout.createSequentialGroup()
                        .addComponent(botonActualizarCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(botonCancelarActualizarCategoria)))
                .addContainerGap())
        );
        panelEditarCategoriaLayout.setVerticalGroup(
            panelEditarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(33, 33, 33)
                .addComponent(campoNombreEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(panelEditarCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelarActualizarCategoria)
                    .addComponent(botonActualizarCategoria))
                .addContainerGap())
        );

        jLabel7.setText("Categorias");

        botonPanelEditarCategoria.setText("Editar categoría");
        botonPanelEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelEditarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestaniaAdministrarCategoriasLayout = new javax.swing.GroupLayout(pestaniaAdministrarCategorias);
        pestaniaAdministrarCategorias.setLayout(pestaniaAdministrarCategoriasLayout);
        pestaniaAdministrarCategoriasLayout.setHorizontalGroup(
            pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                        .addGroup(pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(96, 96, 96)
                        .addGroup(pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(panelAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonPanelAgregarCategoria))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                        .addGroup(pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                                .addGap(486, 486, 486)
                                .addGroup(pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(panelEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonPanelEditarCategoria))))
                        .addContainerGap(69, Short.MAX_VALUE))))
        );
        pestaniaAdministrarCategoriasLayout.setVerticalGroup(
            pestaniaAdministrarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminarCategoria)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pestaniaAdministrarCategoriasLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(botonPanelAgregarCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonPanelEditarCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        pestaniaAdministrar.addTab("Administrar categorias", pestaniaAdministrarCategorias);

        tablaAdministracionUsuarios.setModel(this.conectorTablaUsuarios);
        tablaAdministracionUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdministracionUsuariosMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tablaAdministracionUsuarios);

        jLabel8.setText("Usuarios");

        panelAgregarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        campoCodigoAgregarUsuario.setText("Código");

        campoNombreAgregarUsuario.setText("Nombre");

        comboboxCargoAgregarUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vendedor", "Administrador" }));

        botonAgregarUsuario.setText("Agregar");
        botonAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarUsuarioActionPerformed(evt);
            }
        });

        botonCancelarAgregarUsuario.setText("Cancelar");
        botonCancelarAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarAgregarUsuarioActionPerformed(evt);
            }
        });

        campoClaveAgregarUsuario.setText("Clave");

        jLabel9.setText("Agregar usuario");

        javax.swing.GroupLayout panelAgregarUsuarioLayout = new javax.swing.GroupLayout(panelAgregarUsuario);
        panelAgregarUsuario.setLayout(panelAgregarUsuarioLayout);
        panelAgregarUsuarioLayout.setHorizontalGroup(
            panelAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarUsuarioLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(botonAgregarUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(botonCancelarAgregarUsuario)
                .addGap(84, 84, 84))
            .addGroup(panelAgregarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoCodigoAgregarUsuario)
                    .addComponent(campoNombreAgregarUsuario)
                    .addComponent(campoClaveAgregarUsuario)
                    .addComponent(comboboxCargoAgregarUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAgregarUsuarioLayout.setVerticalGroup(
            panelAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCodigoAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNombreAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoClaveAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxCargoAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(panelAgregarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregarUsuario)
                    .addComponent(botonCancelarAgregarUsuario))
                .addContainerGap())
        );

        panelEditarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        campoCodigoEditarUsuario.setText("Código");

        campoNombreEditarUsuario.setText("Nombre");

        comboboxCargoEditarUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vendedor", "Administrador" }));

        botonActualizarUsuario.setText("Actualizar");
        botonActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarUsuarioActionPerformed(evt);
            }
        });

        botonCancelarActualizarUsuario.setText("Cancelar");
        botonCancelarActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActualizarUsuarioActionPerformed(evt);
            }
        });

        campoClaveEditarUsuario.setText("Clave");
        campoClaveEditarUsuario.setToolTipText("Si queda en blanco no actualiza clave");

        jLabel10.setText("Actualizar usuario");

        javax.swing.GroupLayout panelEditarUsuarioLayout = new javax.swing.GroupLayout(panelEditarUsuario);
        panelEditarUsuario.setLayout(panelEditarUsuarioLayout);
        panelEditarUsuarioLayout.setHorizontalGroup(
            panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoCodigoEditarUsuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditarUsuarioLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(botonActualizarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(botonCancelarActualizarUsuario)
                        .addGap(63, 63, 63))
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoClaveEditarUsuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoNombreEditarUsuario))
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(comboboxCargoEditarUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelEditarUsuarioLayout.setVerticalGroup(
            panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCodigoEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(campoNombreEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoClaveEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxCargoEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonActualizarUsuario)
                    .addComponent(botonCancelarActualizarUsuario))
                .addContainerGap())
        );

        botonEliminarUsuario.setText("Eliminar usuario");
        botonEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarUsuarioActionPerformed(evt);
            }
        });

        botonPanelAgregarUsuario.setText("Agregar usuario");
        botonPanelAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelAgregarUsuarioActionPerformed(evt);
            }
        });

        botonPanelEditarUsuario.setText("Editar usuario");
        botonPanelEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelEditarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestaniaAdministrarUsuariosLayout = new javax.swing.GroupLayout(pestaniaAdministrarUsuarios);
        pestaniaAdministrarUsuarios.setLayout(pestaniaAdministrarUsuariosLayout);
        pestaniaAdministrarUsuariosLayout.setHorizontalGroup(
            pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                .addGroup(pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                        .addGroup(pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(botonEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(panelAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonPanelAgregarUsuario)
                                .addComponent(botonPanelEditarUsuario))
                            .addComponent(panelEditarUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pestaniaAdministrarUsuariosLayout.setVerticalGroup(
            pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pestaniaAdministrarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pestaniaAdministrarUsuariosLayout.createSequentialGroup()
                        .addComponent(botonPanelAgregarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonPanelEditarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminarUsuario)
                .addContainerGap())
        );

        pestaniaAdministrar.addTab("Administrar usuarios", pestaniaAdministrarUsuarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pestaniaAdministrar)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pestaniaAdministrar, javax.swing.GroupLayout.PREFERRED_SIZE, 614, Short.MAX_VALUE)
        );

        panelPrincipal.addTab("Administración", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonPanelAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelAgregarUsuarioActionPerformed
        if (!this.panelAgregarUsuario.isEnabled()) {
            this.botonPanelEditarUsuario.setEnabled(false);
            this.botonEliminarUsuario.setEnabled(false);
            this.habilitarPanelAgregarUsuario();
        }
    }//GEN-LAST:event_botonPanelAgregarUsuarioActionPerformed

    private void botonPanelEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelEditarUsuarioActionPerformed
        if (!this.panelEditarUsuario.isEnabled() && !this.editandoUsuario && this.filaSeleccionadaDeTablaAdministracionUsuarios != -1) {
            this.codigoOrigianlUsuario = this.campoCodigoEditarUsuario.getText();
            this.editandoUsuario = true;
            this.botonPanelAgregarUsuario.setEnabled(false);
            this.botonEliminarUsuario.setEnabled(false);
            this.habilitarPanelEditarUsuario();
        }
    }//GEN-LAST:event_botonPanelEditarUsuarioActionPerformed

    private void botonAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarUsuarioActionPerformed
        String codigo = this.campoCodigoAgregarUsuario.getText();
        if (!this.modeloApp.existeUsuarioConCodigo(codigo)) {
            String claveConfirmada = JOptionPane.showInputDialog(this, "Confirmar clave:", "Confirmar clave", JOptionPane.INFORMATION_MESSAGE);

            String nombre = this.campoNombreAgregarUsuario.getText();
            String clave = this.campoClaveAgregarUsuario.getText();
            String cargo = this.comboboxCargoAgregarUsuario.getModel().getSelectedItem().toString();

            if (claveConfirmada != null && !claveConfirmada.isEmpty()) {
                if (claveConfirmada.equals(clave)) {
                    if (cargo.equals("Administrador")) {
                        this.modeloApp.crearAgregarUsuarioAdministrador(codigo, nombre, clave);
                    }
                    if (cargo.equals("Vendedor")) {
                        this.modeloApp.crearAgregarUsuarioVendedor(codigo, nombre, clave);
                    }
                    this.conectorTablaUsuarios.cargarUsuarios();
                    this.restablecerCamposAgregarUsuario();
                    this.botonPanelEditarUsuario.setEnabled(true);
                    this.botonEliminarUsuario.setEnabled(true);
                    this.deshabilitarPanelAgregarUsuario();
                    JOptionPane.showMessageDialog(this, "Usuario agregado\ncorrectamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Las claves no coindiden", "Claves distintas", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe usuario\ncon código " + codigo, "Redundancia de código", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonAgregarUsuarioActionPerformed

    private void botonCancelarAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarAgregarUsuarioActionPerformed
        this.restablecerCamposAgregarUsuario();
        this.botonPanelEditarUsuario.setEnabled(true);
        this.botonEliminarUsuario.setEnabled(true);
        this.deshabilitarPanelAgregarUsuario();
    }//GEN-LAST:event_botonCancelarAgregarUsuarioActionPerformed

    private boolean actualizarUsuario(Usuario u, String codigo, String nombre, String clave, String cargo) {
        if (u.getCodigo().equals("1")) {
            JOptionPane.showMessageDialog(this, "El código y cargo del usuario\nespecial 1(UNO) no se modificarán", "Administrador principal", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (!u.getCodigo().equals(codigo)) {
                if (!this.modeloApp.existeUsuarioConCodigo(codigo)) {
                    u.setCodigo(codigo);
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe usuario con código " + codigo, "Redundancia de código", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }

            if (!u.getCargo().toString().equals(cargo)) {
                if (cargo.equals("Vendedor")) {
                    u.setCargo(new Vendedor());
                }
                if (cargo.equals("Administrador")) {
                    u.setCargo(new Administrador());
                }
            }

        }

        if (!u.getNombre().equals(nombre)) {
            u.setNombre(nombre);
        }
        if (!clave.isEmpty()) {
            String claveConfirmada = JOptionPane.showInputDialog(this, "Confirmar clave:", "Confirmar clave", JOptionPane.INFORMATION_MESSAGE);
            if (clave.equals(claveConfirmada)) {
                u.setClaveAcceso(clave);
            } else {
                JOptionPane.showMessageDialog(this, "Las claves no coindiden", "Claves distintas", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void botonActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarUsuarioActionPerformed

        Usuario u = this.modeloApp.obtenerUsuarioConCodigo(this.codigoOrigianlUsuario);

        String codigo = this.campoCodigoEditarUsuario.getText();
        String nombre = this.campoNombreEditarUsuario.getText();
        String clave = this.campoClaveEditarUsuario.getText();
        String cargo = this.comboboxCargoEditarUsuario.getModel().getSelectedItem().toString();

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Realmente desea actualizar \n datos del usuario " + this.codigoOrigianlUsuario + "?", "Confirme actualización", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            boolean actualizar = this.actualizarUsuario(u, codigo, nombre, clave, cargo);
            if (actualizar) {
                this.modeloApp.actualizarUsuario(u);
                this.editandoUsuario = false;
                this.conectorTablaUsuarios.cargarUsuarios();
                this.restablecerCamposEditarUsuario();
                this.botonPanelAgregarUsuario.setEnabled(true);
                this.botonEliminarUsuario.setEnabled(true);
                this.deshabilitarPanelEditarUsuario();
                JOptionPane.showMessageDialog(this, "El usuario fue actualizado correctamente", "Usuario actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El usuario no fue actualizado", "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonActualizarUsuarioActionPerformed

    private void botonCancelarActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActualizarUsuarioActionPerformed
        this.editandoUsuario = false;
        this.tablaAdministracionUsuarios.clearSelection();
        this.restablecerCamposEditarUsuario();
        this.botonPanelAgregarUsuario.setEnabled(true);
        this.botonEliminarUsuario.setEnabled(true);
        this.deshabilitarPanelEditarUsuario();
    }//GEN-LAST:event_botonCancelarActualizarUsuarioActionPerformed

    private void botonPanelEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelEditarCategoriaActionPerformed
        if (!this.panelEditarCategoria.isEnabled() && !this.editandoCategoria && this.filaSeleccionadaDeTablaAdministracionCategorias != -1) {
            this.nombreOriginalCategoria = this.campoNombreEditarCategoria.getText();
            this.editandoCategoria = true;
            this.botonPanelAgregarCategoria.setEnabled(false);
            this.botonEliminarCategoria.setEnabled(false);
            this.habilitarPanelEditarCategoria();
        }
    }//GEN-LAST:event_botonPanelEditarCategoriaActionPerformed

    private void botonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarCategoriaActionPerformed
        String nombre = this.campoNombreAgregarCategoria.getText();
        if (!this.modeloApp.existeCategoriaConNombre(nombre)) {
            this.modeloApp.crearAgregarCategoria(nombre);
            this.botonPanelEditarCategoria.setEnabled(true);
            this.botonEliminarCategoria.setEnabled(true);
            this.deshabilitarPanelAgregarCategoria();
            this.restablecerCamposAgregarCategoria();
            this.conectorTablaCategorias.cargarCategorias();
            JOptionPane.showMessageDialog(this, "Categoría agregada\ncorrectamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe una categoría\ncon nombre " + nombre, "Redundancia de nombre", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonAgregarCategoriaActionPerformed

    private void botonCancelarAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarAgregarCategoriaActionPerformed
        this.botonPanelEditarCategoria.setEnabled(true);
        this.botonEliminarCategoria.setEnabled(true);
        this.deshabilitarPanelAgregarCategoria();
    }//GEN-LAST:event_botonCancelarAgregarCategoriaActionPerformed
    private boolean actualizarCategoria(Categoria c, String nombre) {
        if (this.nombreOriginalCategoria.equals("Catálogo")) {
            JOptionPane.showMessageDialog(this, "Catálogo es una categoría especial\npor tanto no será modificada", "Categoría principal", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (this.modeloApp.existeCategoriaConNombre(nombre)) {
            JOptionPane.showMessageDialog(this, "Ya existe una categoría\ncon nombre " + nombre, "Redundancia de nombre", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        c.setNombre(nombre);
        return true;
    }

    private void botonActualizarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarCategoriaActionPerformed
        Categoria c = this.modeloApp.obtenerCategoriaConNombre(this.nombreOriginalCategoria);
        String nombre = this.campoNombreEditarCategoria.getText();
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Realmente desea actualizar\nel nombre de la categoría " + this.nombreOriginalCategoria + "?", "Confirme actualización", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean actualizar = this.actualizarCategoria(c, nombre);
            if (actualizar) {
                this.modeloApp.actualizarCategoria(c);
                this.editandoCategoria = false;
                this.conectorTablaCategorias.cargarCategorias();
                this.campoNombreEditarCategoria.setText("Nombre");
                this.botonPanelAgregarCategoria.setEnabled(true);
                this.botonEliminarCategoria.setEnabled(true);
                this.deshabilitarPanelEditarCategoria();
                JOptionPane.showMessageDialog(this, "La categoría fue actualizada correctamente", "Categoría actualizada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La categoría no fue actualizada", "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonActualizarCategoriaActionPerformed

    private void botonCancelarActualizarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActualizarCategoriaActionPerformed
        this.editandoCategoria = false;
        this.tablaAdministracionCategorias.clearSelection();
        this.campoNombreEditarCategoria.setText("Nombre");
        this.botonPanelAgregarCategoria.setEnabled(true);
        this.botonEliminarCategoria.setEnabled(true);
        this.deshabilitarPanelEditarCategoria();
    }//GEN-LAST:event_botonCancelarActualizarCategoriaActionPerformed

    private void botonPanelAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelAgregarProductoActionPerformed
        if (!this.panelAgregarProducto.isEnabled()) {
            this.botonPanelEditarProducto.setEnabled(false);
            this.botonEliminarProducto.setEnabled(false);
            this.habilitarPanelAgregarProducto();
        }
    }//GEN-LAST:event_botonPanelAgregarProductoActionPerformed

    private void botonPanelEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelEditarProductoActionPerformed
        if (!this.panelEditarProducto.isEnabled() && !this.editandoProducto && this.filaSeleccionadaDeTablaAdministracionProductos != -1) {
            this.codigoOriginalProducto = this.campoCodigoEditarProducto.getText();
            this.editandoProducto = true;
            this.botonPanelAgregarProducto.setEnabled(false);
            this.botonEliminarProducto.setEnabled(false);
            this.habilitarPanelEditarProducto();
        }
    }//GEN-LAST:event_botonPanelEditarProductoActionPerformed

    private boolean validarEntero(String cadena) {
        return cadena.matches("[0-9]*");
    }

    private void restablecerCamposAgregarProducto() {
        this.campoCodigoAgregarProducto.setText("Código");
        this.campoDescripcionAgregarProducto.setText("Descripción");
        this.campoStockAgregarProducto.setText("Stock");
        this.comboboxImagenAgregarProducto.setSelectedIndex(0);
    }

    private void restablecerCamposEditarProducto() {
        this.campoCodigoEditarProducto.setText("Código");
        this.campoDescripcionEditarProducto.setText("Descripción");
        this.campoStockEditarProducto.setText("Stock");
        this.comboboxImagenEditarProducto.setSelectedIndex(0);
    }

    private String obtenerPathImagenProducto(String nombreImagen) {
        switch (nombreImagen) {
            case "Ningúna":
                return "/imagenes/blanco.jpg";
            case "Crema":
                return "/imagenes/crema.jpg";
            case "Jabón":
                return "/imagenes/jabon.jpg";
            case "Loción":
                return "/imagenes/locion.jpg";
            default:
                return "/imagenes/blanco.jpg";
        }
    }

    private int obtenerIndiceComboBoxImagenProducto(String pathImagen) {
        switch (pathImagen) {
            case "/imagenes/blanco.jpg":
                return 0;
            case "/imagenes/crema.jpg":
                return 1;
            case "/imagenes/jabon.jpg":
                return 2;
            case "/imagenes/locion.jpg":
                return 3;
            default:
                return 0;
        }
    }

    private void botonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProductoActionPerformed
        String codigo = this.campoCodigoAgregarProducto.getText();
        if (!this.modeloApp.existeProductoConCodigo(codigo)) {
            String stockString = this.campoStockAgregarProducto.getText();
            if (this.validarEntero(stockString)) {
                String descripcion = this.campoDescripcionAgregarProducto.getText();
                int stock = Integer.parseInt(stockString);
                String nombreImagen = this.comboboxImagenAgregarProducto.getSelectedItem().toString();
                String imagen = this.obtenerPathImagenProducto(nombreImagen);
                this.modeloApp.crearAgregarProducto(codigo, descripcion, stock, imagen);

                this.conectorTablaProductos.cargarProductos();

                this.restablecerCamposAgregarProducto();
                this.botonPanelEditarProducto.setEnabled(true);
                this.botonEliminarProducto.setEnabled(true);
                this.deshabilitarPanelAgregarProducto();
                ((PanelImagenProducto) this.panelImagenAdministrarProductos).sacarImagenProducto();
                JOptionPane.showMessageDialog(this, "Producto agregado\ncorrectamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El campo Stock debe ser un entero ", "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe producto\ncon código " + codigo, "Redundancia de código", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonAgregarProductoActionPerformed

    private void botonCancelarAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarAgregarProductoActionPerformed
        this.restablecerCamposAgregarProducto();
        this.botonPanelEditarProducto.setEnabled(true);
        this.botonEliminarProducto.setEnabled(true);
        this.deshabilitarPanelAgregarProducto();
    }//GEN-LAST:event_botonCancelarAgregarProductoActionPerformed

    private boolean actualizarProducto(Producto p, String codigo, String stock, String descripcion, String nombreImagen) {
        if (!p.getCodigo().equals(codigo)) {
            if (!this.modeloApp.existeProductoConCodigo(codigo)) {
                p.setCodigo(codigo);
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe producto con código " + codigo, "Redundancia de código", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }

        if (this.validarEntero(stock)) {
            String stockOriginal = String.valueOf(p.getStock());
            if (!stock.equals(stockOriginal)) {
                p.setStock(Integer.parseInt(stock));
            }
        } else {
            JOptionPane.showMessageDialog(this, "El campo Stock debe ser un entero ", "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (!p.getDescripcion().equals(descripcion)) {
            p.setDescripcion(descripcion);
        }

        System.out.println("nombre imagen" + nombreImagen);
        String pathImagen = this.obtenerPathImagenProducto(nombreImagen);
        System.out.println("path imagen" + pathImagen);
        if (!p.getPathImagen().equals(pathImagen)) {
            p.setPathImagen(pathImagen);
        }
        return true;
    }

    private void botonActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarProductoActionPerformed
        Producto p = this.modeloApp.obtenerProductoConCodigo(this.codigoOriginalProducto);

        String codigo = this.campoCodigoEditarProducto.getText();
        String descripcion = this.campoDescripcionEditarProducto.getText();
        String stock = this.campoStockEditarProducto.getText();
        String nombreImagen = this.comboboxImagenEditarProducto.getSelectedItem().toString();

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Realmente desea actualizar\ndatos del producto " + this.codigoOriginalProducto + "?", "Confirme actualización", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            boolean actualizar = this.actualizarProducto(p, codigo, stock, descripcion, nombreImagen);
            if (actualizar) {
                this.modeloApp.actualizarProducto(p);
                this.editandoProducto = false;
                this.conectorTablaProductos.cargarProductos();
                this.restablecerCamposEditarProducto();
                this.botonPanelAgregarProducto.setEnabled(true);
                this.botonEliminarProducto.setEnabled(true);
                this.deshabilitarPanelEditarProducto();
                ((PanelImagenProducto) this.panelImagenAdministrarProductos).sacarImagenProducto();
                JOptionPane.showMessageDialog(this, "El producto fue actualizado correctamente", "Producto actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El producto no fue actualizado", "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonActualizarProductoActionPerformed

    private void botonCancelarActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActualizarProductoActionPerformed
        this.botonPanelAgregarProducto.setEnabled(true);
        this.botonEliminarProducto.setEnabled(true);
        this.deshabilitarPanelEditarProducto();
    }//GEN-LAST:event_botonCancelarActualizarProductoActionPerformed

    private void botonEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarUsuarioActionPerformed
        if (this.tablaAdministracionUsuarios.getSelectedRow() > -1) {
            String codigo = (String) this.conectorTablaUsuarios.getValueAt(this.tablaAdministracionUsuarios.getSelectedRow(), 0);
            int respuesta = JOptionPane.showConfirmDialog(this.botonEliminarUsuario, "¿Realmente desea eliminar \n al usuario " + codigo + "?", "Confirme eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                Usuario u = this.modeloApp.obtenerUsuarioConCodigo(codigo);
                this.modeloApp.eliminarUsuario(u);
                this.conectorTablaUsuarios.cargarUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un\nusuario de la tabla", "Usuario no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonEliminarUsuarioActionPerformed

    private void tablaAdministracionUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdministracionUsuariosMousePressed
        if (!this.editandoUsuario) {
            if (this.filaSeleccionadaDeTablaAdministracionUsuarios == this.tablaAdministracionUsuarios.getSelectedRow()) {
                this.deshabilitarPanelEditarUsuario();
                this.tablaAdministracionUsuarios.clearSelection();
                this.restablecerCamposEditarUsuario();
                this.filaSeleccionadaDeTablaAdministracionUsuarios = -1;
            } else {
                this.filaSeleccionadaDeTablaAdministracionUsuarios = this.tablaAdministracionUsuarios.getSelectedRow();
                String codigo = (String) this.conectorTablaUsuarios.getValueAt(this.tablaAdministracionUsuarios.getSelectedRow(), 0);
                String nombre = (String) this.conectorTablaUsuarios.getValueAt(this.tablaAdministracionUsuarios.getSelectedRow(), 1);
                String cargo = (String) this.conectorTablaUsuarios.getValueAt(this.tablaAdministracionUsuarios.getSelectedRow(), 2);
                this.campoCodigoEditarUsuario.setText(codigo);
                this.campoNombreEditarUsuario.setText(nombre);
                this.campoClaveEditarUsuario.setText("");
                if (cargo.equals("Vendedor")) {
                    this.comboboxCargoEditarUsuario.setSelectedIndex(0);
                }
                if (cargo.equals("Administrador")) {
                    this.comboboxCargoEditarUsuario.setSelectedIndex(1);
                }
            }
        }
    }//GEN-LAST:event_tablaAdministracionUsuariosMousePressed

    private void botonPanelAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelAgregarCategoriaActionPerformed
        if (!this.panelAgregarCategoria.isEnabled()) {
            this.botonPanelEditarCategoria.setEnabled(false);
            this.botonEliminarCategoria.setEnabled(false);
            this.habilitarPanelAgregarCategoria();
        }
    }//GEN-LAST:event_botonPanelAgregarCategoriaActionPerformed

    private void botonEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarCategoriaActionPerformed
        if (this.tablaAdministracionCategorias.getSelectedRow() > -1) {
            String nombre = (String) this.conectorTablaCategorias.getValueAt(this.tablaAdministracionCategorias.getSelectedRow(), 0);
            Categoria c = this.modeloApp.obtenerCategoriaConNombre(nombre);
            if (c == null) {
                JOptionPane.showMessageDialog(this, "No existe la categoria con nombre " + nombre, "Verifique datos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int respuesta = JOptionPane.showConfirmDialog(this.botonEliminarCategoria, "¿Realmente desea eliminar\nla categoria " + nombre + "?", "Confirme eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    this.modeloApp.eliminarCategoria(c);
                    this.conectorTablaCategorias.cargarCategorias();
                    this.arbolAdministrarCatalogo.repaint();
                    this.jTree3.repaint();
//                    this.conectorArbolCatalogo.cargarItems();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una\ncategoria de la tabla", "Categoria no seleccionada", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonEliminarCategoriaActionPerformed

    private void tablaAdministracionCategoriasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdministracionCategoriasMousePressed
        if (!this.editandoCategoria) {
            if (this.filaSeleccionadaDeTablaAdministracionCategorias == this.tablaAdministracionCategorias.getSelectedRow()) {
                this.deshabilitarPanelEditarCategoria();
                this.tablaAdministracionCategorias.clearSelection();
                this.campoNombreEditarCategoria.setText("Nombre");
                this.filaSeleccionadaDeTablaAdministracionCategorias = -1;

            } else {
                this.filaSeleccionadaDeTablaAdministracionCategorias = this.tablaAdministracionCategorias.getSelectedRow();
                String nombre = (String) this.conectorTablaCategorias.getValueAt(this.tablaAdministracionCategorias.getSelectedRow(), 0);
                this.campoNombreEditarCategoria.setText(nombre);
            }
        }
    }//GEN-LAST:event_tablaAdministracionCategoriasMousePressed

    private void botonEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProductoActionPerformed
        if (this.tablaAdministracionProductos.getSelectedRow() > -1) {
            String codigo = (String) this.conectorTablaProductos.getValueAt(this.tablaAdministracionProductos.getSelectedRow(), 0);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Realmente desea eliminar \n el producto " + codigo + "?", "Confirme eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                Producto p = this.modeloApp.obtenerProductoConCodigo(codigo);
                this.modeloApp.eliminarProducto(p);
                this.conectorTablaProductos.cargarProductos();
                ((PanelImagenProducto) this.panelImagenAdministrarProductos).sacarImagenProducto();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un\nproducto de la tabla", "Producto no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botonEliminarProductoActionPerformed

    private void tablaAdministracionProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdministracionProductosMousePressed
        if (!this.editandoProducto) {
            if (this.filaSeleccionadaDeTablaAdministracionProductos == this.tablaAdministracionProductos.getSelectedRow()) {
                this.deshabilitarPanelEditarProducto();
                this.tablaAdministracionProductos.clearSelection();
                this.restablecerCamposEditarProducto();
                this.filaSeleccionadaDeTablaAdministracionProductos = -1;
                ((PanelImagenProducto) this.panelImagenAdministrarProductos).sacarImagenProducto();
            } else {
                this.filaSeleccionadaDeTablaAdministracionProductos = this.tablaAdministracionProductos.getSelectedRow();
                String codigo = (String) this.conectorTablaProductos.getValueAt(this.tablaAdministracionProductos.getSelectedRow(), 0);
                String descripcion = (String) this.conectorTablaProductos.getValueAt(this.tablaAdministracionProductos.getSelectedRow(), 1);
                Integer stock = (Integer) this.conectorTablaProductos.getValueAt(this.tablaAdministracionProductos.getSelectedRow(), 2);
                Producto p = this.modeloApp.obtenerProductoConCodigo(codigo);
                this.campoCodigoEditarProducto.setText(codigo);
                this.campoDescripcionEditarProducto.setText(descripcion);
                this.campoStockEditarProducto.setText(stock.toString());
                int indiceImagen = this.obtenerIndiceComboBoxImagenProducto(p.getPathImagen());
                this.comboboxImagenEditarProducto.setSelectedIndex(indiceImagen);
                ((PanelImagenProducto) this.panelImagenAdministrarProductos).ponerImagenProducto(p);
            }
        }
    }//GEN-LAST:event_tablaAdministracionProductosMousePressed

    private void tablaProductosOrigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosOrigenMousePressed
        if (this.tablaCategoriasOrigen.getSelectedRow() != -1) {
            this.tablaCategoriasOrigen.clearSelection();
        }
        if (this.filaSeleccionadaDeTablaProductosOrigen == this.tablaProductosOrigen.getSelectedRow()) {
            this.tablaProductosOrigen.clearSelection();
            this.campoOrigenAdministrarCatalogo.setText("Origen");
            this.filaSeleccionadaDeTablaProductosOrigen = -1;
        } else {
            this.filaSeleccionadaDeTablaProductosOrigen = this.tablaProductosOrigen.getSelectedRow();
            String producto = this.tablaProductosOrigen.getModel().getValueAt(this.tablaProductosOrigen.getSelectedRow(), 0).toString();
            this.campoOrigenAdministrarCatalogo.setText(producto);
        }
    }//GEN-LAST:event_tablaProductosOrigenMousePressed

    private void tablaCategoriasOrigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCategoriasOrigenMousePressed
        if (this.tablaProductosOrigen.getSelectedRow() != -1) {
            this.tablaProductosOrigen.clearSelection();
            this.campoOrigenAdministrarCatalogo.setText("Origen");
        }
        if (this.filaSeleccionadaDeTablaCategoriasOrigen == this.tablaCategoriasOrigen.getSelectedRow()) {
            this.tablaCategoriasOrigen.clearSelection();
            this.campoOrigenAdministrarCatalogo.setText("Origen");
            this.filaSeleccionadaDeTablaCategoriasOrigen = -1;
        } else {
            this.filaSeleccionadaDeTablaCategoriasOrigen = this.tablaCategoriasOrigen.getSelectedRow();
            String producto = this.tablaCategoriasOrigen.getModel().getValueAt(this.tablaCategoriasOrigen.getSelectedRow(), 0).toString();
            this.campoOrigenAdministrarCatalogo.setText(producto);
        }
    }//GEN-LAST:event_tablaCategoriasOrigenMousePressed

    private void tablaCategoriasDestinoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCategoriasDestinoMousePressed
        if (this.filaSeleccionadaDeTablaCategoriasDestino == this.tablaCategoriasDestino.getSelectedRow()) {
            this.tablaCategoriasDestino.clearSelection();
            this.campoDestinoAdministrarCatalogo.setText("Origen");
            this.filaSeleccionadaDeTablaCategoriasDestino = -1;
        } else {
            this.filaSeleccionadaDeTablaCategoriasDestino = this.tablaCategoriasDestino.getSelectedRow();
            String producto = this.tablaCategoriasDestino.getModel().getValueAt(this.tablaCategoriasDestino.getSelectedRow(), 0).toString();
            this.campoDestinoAdministrarCatalogo.setText(producto);
        }
    }//GEN-LAST:event_tablaCategoriasDestinoMousePressed

    private void botonInsertarAdministrarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertarAdministrarCatalogoActionPerformed
        if (this.campoOrigenAdministrarCatalogo.getText().equals("Origen") || this.campoDestinoAdministrarCatalogo.getText().equals("Destino")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un [Origen] y un\n[Destino] para completar la inserción", "Seleccione los datos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (this.campoOrigenAdministrarCatalogo.getText().equals(this.campoDestinoAdministrarCatalogo.getText())) {
                JOptionPane.showMessageDialog(this, "Campos [Origen] y [Destino] no pueden ser iguales", "Modifique seleción", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String dato = this.campoOrigenAdministrarCatalogo.getText();
                String nombre = this.campoDestinoAdministrarCatalogo.getText();
                ItemCatalogo item = this.modeloApp.obtenerItemCatalogo(dato);
                Categoria categoria = this.modeloApp.obtenerCategoriaConNombre(nombre);
                if (item != null && categoria != null) {
                    if (item.esProducto()) {
                        Producto p = (Producto) item;
                        this.modeloApp.agregarProductoEnCategoria(p, categoria);
                    }
                    if (item.esCategoria()) {
                        Categoria c = (Categoria) item;
                        if (this.modeloApp.agregarCategoriaEnCategoria(c, categoria)) {
                        } else {
                            JOptionPane.showMessageDialog(this, "Verifique:\n-La categoría origen no puede ser el catálogo\n-Una categoria no puede ser padre de si misma\n-Una categoría no puede estar dentro de su propia rama", "Modifique seleción", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No existe dato [Origen] o [Destino]", "Datos no encontrados", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_botonInsertarAdministrarCatalogoActionPerformed

    private void botonMoverAdministrarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMoverAdministrarCatalogoActionPerformed
        if (this.campoOrigenAdministrarCatalogo.getText().equals("Origen") || this.campoDestinoAdministrarCatalogo.getText().equals("Destino")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un [Origen] y un\n[Destino] para poder mover", "Seleccione los datos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (this.campoOrigenAdministrarCatalogo.getText().equals(this.campoDestinoAdministrarCatalogo.getText())) {
                JOptionPane.showMessageDialog(this, "Campos [Origen] y [Destino] no pueden ser iguales", "Modifique seleción", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String dato = this.campoOrigenAdministrarCatalogo.getText();
                String nombre = this.campoDestinoAdministrarCatalogo.getText();
                ItemCatalogo item = this.modeloApp.obtenerItemCatalogo(dato);
                Categoria categoria = this.modeloApp.obtenerCategoriaConNombre(nombre);
                if (item != null && categoria != null) {
                    if (item.esProducto()) {
                        Producto p = (Producto) item;
                        this.modeloApp.moverProductoHaciaCategoria(p.getPadre(), categoria, p);
                    }
                    if (item.esCategoria()) {
                        Categoria c = (Categoria) item;
                        if (this.modeloApp.agregarCategoriaEnCategoria(c, categoria)) {
                        } else {
                            JOptionPane.showMessageDialog(this, "Verifique:\n-La categoría origen no puede ser el catálogo\n-Una categoria no puede ser padre de si misma\n-Una categoría no puede estar dentro de su propia rama", "Modifique seleción", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No existe dato [Origen] o [Destino]", "Datos no encontrados", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_botonMoverAdministrarCatalogoActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJFrame().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolAdministrarCatalogo;
    private javax.swing.ButtonGroup bg;
    private javax.swing.JButton botonActualizarCategoria;
    private javax.swing.JButton botonActualizarProducto;
    private javax.swing.JButton botonActualizarUsuario;
    private javax.swing.JButton botonAgregarCategoria;
    private javax.swing.JButton botonAgregarProducto;
    private javax.swing.JButton botonAgregarUsuario;
    private javax.swing.JButton botonCancelarActualizarCategoria;
    private javax.swing.JButton botonCancelarActualizarProducto;
    private javax.swing.JButton botonCancelarActualizarUsuario;
    private javax.swing.JButton botonCancelarAgregarCategoria;
    private javax.swing.JButton botonCancelarAgregarProducto;
    private javax.swing.JButton botonCancelarAgregarUsuario;
    private javax.swing.JButton botonEliminarCategoria;
    private javax.swing.JButton botonEliminarProducto;
    private javax.swing.JButton botonEliminarUsuario;
    private javax.swing.JButton botonInsertarAdministrarCatalogo;
    private javax.swing.JButton botonMoverAdministrarCatalogo;
    private javax.swing.JButton botonPanelAgregarCategoria;
    private javax.swing.JButton botonPanelAgregarProducto;
    private javax.swing.JButton botonPanelAgregarUsuario;
    private javax.swing.JButton botonPanelEditarCategoria;
    private javax.swing.JButton botonPanelEditarProducto;
    private javax.swing.JButton botonPanelEditarUsuario;
    private javax.swing.JTextField campoClaveAgregarUsuario;
    private javax.swing.JTextField campoClaveEditarUsuario;
    private javax.swing.JTextField campoCodigoAgregarProducto;
    private javax.swing.JTextField campoCodigoAgregarUsuario;
    private javax.swing.JTextField campoCodigoEditarProducto;
    private javax.swing.JTextField campoCodigoEditarUsuario;
    private javax.swing.JTextField campoDescripcionAgregarProducto;
    private javax.swing.JTextField campoDescripcionEditarProducto;
    private javax.swing.JTextField campoDestinoAdministrarCatalogo;
    private javax.swing.JTextField campoNombreAgregarCategoria;
    private javax.swing.JTextField campoNombreAgregarUsuario;
    private javax.swing.JTextField campoNombreEditarCategoria;
    private javax.swing.JTextField campoNombreEditarUsuario;
    private javax.swing.JTextField campoOrigenAdministrarCatalogo;
    private javax.swing.JTextField campoStockAgregarProducto;
    private javax.swing.JTextField campoStockEditarProducto;
    private javax.swing.JComboBox comboboxCargoAgregarUsuario;
    private javax.swing.JComboBox comboboxCargoEditarUsuario;
    private javax.swing.JComboBox comboboxImagenAgregarProducto;
    private javax.swing.JComboBox comboboxImagenEditarProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree3;
    private javax.swing.JPanel panelAgregarCategoria;
    private javax.swing.JPanel panelAgregarProducto;
    private javax.swing.JPanel panelAgregarUsuario;
    private javax.swing.JPanel panelEditarCategoria;
    private javax.swing.JPanel panelEditarProducto;
    private javax.swing.JPanel panelEditarUsuario;
    private javax.swing.JPanel panelImagenAdministrarProductos;
    private javax.swing.JTabbedPane panelPrincipal;
    private javax.swing.JTabbedPane pestaniaAdministrar;
    private javax.swing.JPanel pestaniaAdministrarCatalogo;
    private javax.swing.JPanel pestaniaAdministrarCategorias;
    private javax.swing.JPanel pestaniaAdministrarUsuarios;
    private javax.swing.JTable tablaAdministracionCategorias;
    private javax.swing.JTable tablaAdministracionProductos;
    private javax.swing.JTable tablaAdministracionUsuarios;
    private javax.swing.JTable tablaCategoriasDestino;
    private javax.swing.JTable tablaCategoriasOrigen;
    private javax.swing.JTable tablaProductosOrigen;
    // End of variables declaration//GEN-END:variables
}
