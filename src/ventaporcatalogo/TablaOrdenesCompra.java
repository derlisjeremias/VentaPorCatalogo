package ventaporcatalogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ventaporcatalogo.modelo.Empresa;
import ventaporcatalogo.modelo.ordencompra.OrdenCompra;

/**
 *
 * @author Jere
 */
public class TablaOrdenesCompra implements TableModel {

    private Empresa modelApp;
    private String[] nombresColumnas;
    private Object[][] filasColumnasTabla;
    private List<TableModelListener> dependientes;
    private TableModelEvent evento;

    public TablaOrdenesCompra(Empresa e) {
        this.modelApp = e;
        this.nombresColumnas = new String[]{"Codigo orden", "Comprador", "Dir comprador", "Cod usuario", "Cant articulos", "Estado"};
        this.filasColumnasTabla = new Object[0][6];
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
        OrdenCompra oc = (OrdenCompra) aValue;
        this.filasColumnasTabla[rowIndex][0] = oc.getCodigo();
        this.filasColumnasTabla[rowIndex][1] = oc.getNombreComprador();
        this.filasColumnasTabla[rowIndex][2] = oc.getDireccionComprador();
        this.filasColumnasTabla[rowIndex][3] = oc.getCodigoUsuario();
        this.filasColumnasTabla[rowIndex][4] = oc.obtenerArticulos().size();
        this.filasColumnasTabla[rowIndex][5] = oc.getTipoEstado();
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
        Object[][] nuevaTabla = new Object[0][2];
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void agregarFila() {
        Object[][] nuevaTabla = new Object[this.getRowCount() + 1][6];
        int i = 0;
        while (i < this.getRowCount()) {
            nuevaTabla[i][0] = this.filasColumnasTabla[i][0];
            nuevaTabla[i][1] = this.filasColumnasTabla[i][1];
            nuevaTabla[i][2] = this.filasColumnasTabla[i][2];
            nuevaTabla[i][3] = this.filasColumnasTabla[i][3];
            nuevaTabla[i][4] = this.filasColumnasTabla[i][4];
            nuevaTabla[i][5] = this.filasColumnasTabla[i][5];
            i++;
        }
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void cargarOrdenesCompra() {
        this.limpiarTabla();
        List<OrdenCompra> ordenesCompra;
        ordenesCompra = this.modelApp.obtenerOrdenesCompraUsuario();
        
        for (OrdenCompra oc : ordenesCompra) {
            this.agregarFila();
            this.setValueAt(oc, this.getRowCount() - 1, 0);
        }
    }
}
