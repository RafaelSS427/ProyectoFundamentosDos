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
public class Usuario {
    private String user;
    private String pass;
    
    //Los constructores se utilizarán según lo que se necesite, pueden crear otro
    //si lo ven necesario

    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    public Usuario(){
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
