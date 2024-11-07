package vista;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Educacion; // Asegúrate de que esta clase existe y tiene los métodos adecuados

public class EducacionTableModel extends AbstractTableModel {
    private List<Educacion> educacionList;
    private String[] columnNames = {"ID", "Universidad", "Nivel de Estudio", "Título de Estudio"}; // Las columnas según los datos disponibles

    public EducacionTableModel(List<Educacion> educacionList) {
        this.educacionList = educacionList;
    }

    @Override
    public int getRowCount() {
        return educacionList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Educacion educacion = educacionList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return educacion.getIdEducacion(); // Retorna el ID de la educación
            case 1:
                return educacion.getUniversidad(); // Retorna el nombre de la universidad
            case 2:
                return educacion.getNivelEstudio(); // Retorna el nivel de estudio
            case 3:
                return educacion.getTituloEstudio(); // Retorna el título de estudio
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
