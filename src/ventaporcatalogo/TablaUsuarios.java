package ventaporcatalogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class TablaUsuarios implements TableModel {

    private Empresa modelApp;
    private String[] nombresColumnas;
    private Object[][] filasColumnasTabla;
    private List<TableModelListener> dependientes;
    private TableModelEvent evento;

    TablaUsuarios(Empresa e) {
        this.modelApp = e;
        this.nombresColumnas = new String[]{"Codigo", "Nombre", "Cargo"};
        this.filasColumnasTabla = new Object[0][3];
        this.dependientes = new ArrayList();
    }

    @Override
    public int getRowCount() {
        return this.filasColumnasTabla.length;
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.nombresColumnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.nombresColumnas[columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.filasColumnasTabla[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usuario u = (Usuario) aValue;
        this.filasColumnasTabla[rowIndex][0] = u.getCodigo();
        this.filasColumnasTabla[rowIndex][1] = u.getNombre();
        this.filasColumnasTabla[rowIndex][2] = u.getCargo().toString();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        this.dependientes.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        this.dependientes.remove(l);
    }

    private void tablaModificada() {
        for (TableModelListener tl : this.dependientes) {
            tl.tableChanged(evento);
        }
    }

    public void limpiarTabla() {
        Object[][] nuevaTabla = new Object[0][3];
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void agregarFila() {
        Object[][] nuevaTabla = new Object[this.getRowCount() + 1][3];
        int i = 0;
        while (i < this.getRowCount()) {
            nuevaTabla[i][0] = this.filasColumnasTabla[i][0];
            nuevaTabla[i][1] = this.filasColumnasTabla[i][1];
            nuevaTabla[i][2] = this.filasColumnasTabla[i][2];
            i++;
        }
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void cargarUsuarios() {
        this.limpiarTabla();
        List<Usuario> usuarios;
        usuarios = this.modelApp.obtenerUsuarios();
        for (Usuario u : usuarios) {
            this.agregarFila();
            this.setValueAt(u, this.getRowCount() - 1, 0);
        }
    }
}
