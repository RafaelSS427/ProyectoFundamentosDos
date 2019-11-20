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
public class Roles {
    private int id_rol;
    private String nombre_rol;
    private int pagoHora_rol;
    
    //Los constructores se utilizarán según lo que se necesite, pueden crear otro
    //si lo ven necesario

    public Roles(int id_rol, String nombre_rol, int pagoHora_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.pagoHora_rol = pagoHora_rol;
    }

    public Roles(String nombre_rol, int pagoHora_rol) {
        this.nombre_rol = nombre_rol;
        this.pagoHora_rol = pagoHora_rol;
    }

    //Constructor para eliminar o actualizar rol
    public Roles(int id_rol) {
        this.id_rol = id_rol;
    }
    
    public Roles(){
        
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public int getPagoHora_rol() {
        return pagoHora_rol;
    }

    public void setPagoHora_rol(int pagoHora_rol) {
        this.pagoHora_rol = pagoHora_rol;
    }
    
}
