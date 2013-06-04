
package ventaporcatalogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.catalogo.Categoria;

/**
 *
 * @author Jere
 */
public class TablaCategorias implements TableModel {

    private Empresa modelApp;
    private String[] nombresColumnas;
    private Object[][] filasColumnasTabla;
    private List<TableModelListener> dependientes;
    private TableModelEvent evento;

    TablaCategorias(Empresa e) {
        this.modelApp = e;
        this.nombresColumnas = new String[]{"Nombre"};
        this.filasColumnasTabla = new Object[0][1];
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
        Categoria c = (Categoria) aValue;
        this.filasColumnasTabla[rowIndex][0] = c.getNombre();
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
        Object[][] nuevaTabla = new Object[0][1];
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void agregarFila() {
        Object[][] nuevaTabla = new Object[this.getRowCount() + 1][1];
        int i = 0;
        while (i < this.getRowCount()) {
            nuevaTabla[i][0] = this.filasColumnasTabla[i][0];
            i++;
        }
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void cargarCategorias() {
        this.limpiarTabla();
        List<Categoria> categorias;
        categorias = this.modelApp.obtenerCategorias();
        for (Categoria c : categorias) {
            this.agregarFila();
            this.setValueAt(c, this.getRowCount() - 1, 0);
        }
    }
}
