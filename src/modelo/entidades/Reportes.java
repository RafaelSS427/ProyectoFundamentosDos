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
public class Reportes {

    private int id_rep;
    private String cod_emp2;
    private int horaEntrada;
    private int horaSalida;
    private String fecha;
    private int laborando;
    private int totalHorasLab;

    public Reportes(int id_rep, String cod_emp2, int horaEntrada, int horaSalida, String fecha, int laborando, int totalHorasLab) {
        this.id_rep = id_rep;
        this.cod_emp2 = cod_emp2;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
        this.laborando = laborando;
        this.totalHorasLab = totalHorasLab;
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
    
    
}
