package modelo;

public class Educacion {
    private int idEducacion;
    private int idFuncionario;  // Relacionado con el idFuncionario de la tabla Funcionario
    private String universidad;
    private String nivelEstudio;
    private String tituloEstudio;

    // Constructor sin argumentos
    public Educacion() {}

    // Constructor con parámetros
    public Educacion(int idFuncionario, String universidad, String nivelEstudio, String tituloEstudio) {
        this.idFuncionario = idFuncionario;
        this.universidad = universidad;
        this.nivelEstudio = nivelEstudio;
        this.tituloEstudio = tituloEstudio;
    }

    // Getters y Setters
    public int getIdEducacion() {
        return idEducacion;
    }

    public void setIdEducacion(int idEducacion) {
        this.idEducacion = idEducacion;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getTituloEstudio() {
        return tituloEstudio;
    }

    public void setTituloEstudio(String tituloEstudio) {
        this.tituloEstudio = tituloEstudio;
    }
}