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
public class IngresoEmpleados {
    private String codigo;
    private int ingresoLaborando;

    public IngresoEmpleados(String codigo) {
        this.codigo = codigo;
    }
    
    public IngresoEmpleados(){
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIngresoLaborando() {
        return ingresoLaborando;
    }

    public void setIngresoLaborando(int ingresoLaborando) {
        this.ingresoLaborando = ingresoLaborando;
    }
    
    
    
}
