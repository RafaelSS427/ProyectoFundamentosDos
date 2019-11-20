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
public class Estado {
    private int id_estado;
    private String nombre_estado;
    
    //Los constructores se utilizarán según lo que se necesite, pueden crear otro
    //si lo ven necesario

    public Estado(int id_estado, String nombre_estado) {
        this.id_estado = id_estado;
        this.nombre_estado = nombre_estado;
    }

    public Estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
    
    public Estado(){
        
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
    
    
}
