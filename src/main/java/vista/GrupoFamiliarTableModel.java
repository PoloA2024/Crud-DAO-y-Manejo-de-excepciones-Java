package vista;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.GrupoFamiliar; // Asegúrate de que esta clase existe y tiene los métodos adecuados

public class GrupoFamiliarTableModel extends AbstractTableModel {
    private List<GrupoFamiliar> grupoFamiliarList;
    private String[] columnNames = {"ID", "Nombres", "Apellidos", "Rol", "Sexo", "Fecha de Nacimiento"}; // Ajusta según los campos

    public GrupoFamiliarTableModel(List<GrupoFamiliar> grupoFamiliarList) {
        this.grupoFamiliarList = grupoFamiliarList;
    }

    @Override
    public int getRowCount() {
        return grupoFamiliarList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GrupoFamiliar grupoFamiliar = grupoFamiliarList.get(rowIndex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato de la fecha

        switch (columnIndex) {
            case 0:
                return grupoFamiliar.getIdGrupoFamiliar(); // ID del grupo familiar
            case 1:
                return grupoFamiliar.getNombres(); // Nombres del miembro del grupo
            case 2:
                return grupoFamiliar.getApellidos(); // Apellidos del miembro del grupo
            case 3:
                return grupoFamiliar.getRol(); // Rol del miembro en el grupo
            case 4:
                return grupoFamiliar.getSexo(); // Sexo del miembro
            case 5:
                return dateFormat.format(grupoFamiliar.getFechaNacimiento()); // Fecha de nacimiento formateada
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
