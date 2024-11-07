package modelo;

import java.util.Date;

public class GrupoFamiliar {
    private int idGrupoFamiliar;
    private int idFuncionario;  // Relacionado con el idFuncionario de la tabla Funcionario
    private String nombres;
    private String apellidos;
    private String rol;
    private String sexo;
    private Date fechaNacimiento;

    // Constructor sin argumentos
    public GrupoFamiliar() {}

    // Constructor con parámetros
    public GrupoFamiliar(int idFuncionario, String nombres, String apellidos, String rol, 
                         String sexo, Date fechaNacimiento) {
        this.idFuncionario = idFuncionario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public int getIdGrupoFamiliar() {
        return idGrupoFamiliar;
    }

    public void setIdGrupoFamiliar(int idGrupoFamiliar) {
        this.idGrupoFamiliar = idGrupoFamiliar;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}