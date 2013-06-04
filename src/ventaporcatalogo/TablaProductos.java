package ventaporcatalogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.catalogo.Producto;

/**
 *
 * @author Jere
 */
public class TablaProductos implements TableModel {

    private Empresa modelApp;
    private String[] nombresColumnas;
    private Object[][] filasColumnasTabla;
    private List<TableModelListener> dependientes;
    private TableModelEvent evento;

    public TablaProductos(Empresa e) {
        this.modelApp = e;
        this.nombresColumnas = new String[]{"Codigo", "Descripcion", "Stock"};
        this.filasColumnasTabla = new Object[0][3];
        this.dependientes = new ArrayList();
        this.evento = new TableModelEvent(this);
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
        Producto p = (Producto) aValue;
        this.filasColumnasTabla[rowIndex][0] = p.getCodigo();
        this.filasColumnasTabla[rowIndex][1] = p.getDescripcion();
        this.filasColumnasTabla[rowIndex][2] = p.getStock();
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

    public void cargarProductos() {
        this.limpiarTabla();
        List<Producto> productos;
        productos = this.modelApp.obtenerProductos();
        for (Producto p : productos) {
            this.agregarFila();
            this.setValueAt(p, this.getRowCount() - 1, 0);
        }
    }
}
