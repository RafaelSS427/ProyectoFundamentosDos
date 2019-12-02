/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

/**
 *
 * @author HP
 */
public class RegistroInnerJoin {

    private int id_rep;
    private String cod_emp2;
    private int horaEntrada;
    private int horaSalida;
    private String fecha;
    private int laborando;
    private int totalHorasLab;
    private int salario;
    private String nombre_emp;
    private String estad_emp;
    private String rol;

    public RegistroInnerJoin(String cod_emp2, String fecha) {
        this.cod_emp2 = cod_emp2;
        this.fecha = fecha;
    }

    public RegistroInnerJoin(String cod_emp2) {
        this.cod_emp2 = cod_emp2;
    }
    
    public RegistroInnerJoin(){
        
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public String getCod_emp2() {
        return cod_emp2;
    }

    public void setCod_emp2(String cod_emp2) {
        this.cod_emp2 = cod_emp2;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getLaborando() {
        return laborando;
    }

    public void setLaborando(int laborando) {
        this.laborando = laborando;
    }

    public int getTotalHorasLab() {
        return totalHorasLab;
    }

    public void setTotalHorasLab(int totalHorasLab) {
        this.totalHorasLab = totalHorasLab;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getEstad_emp() {
        return estad_emp;
    }

    public void setEstad_emp(String estad_emp) {
        this.estad_emp = estad_emp;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
