/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uanl.fcfm.lmad.papw.model;

/**
 *
 * @author Carlos
 */
public class Empleado {
    
    private int id;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private Departamento departamento;
    private byte sexo;

    public Empleado(int id, String nombre, String apePaterno, String apeMaterno, byte sexo) {
        this.id = id;
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.sexo = sexo;
    }
    
    public Empleado(String nombre, String apePaterno, String apeMaterno, byte sexo) {
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public byte getSexo() {
        return sexo;
    }

    public void setSexo(byte sexo) {
        this.sexo = sexo;
    }
}