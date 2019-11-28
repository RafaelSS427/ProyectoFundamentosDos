/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ln;

import modelo.bd.Conexion;

/**
 *
 * @author HP
 */
public class LnReportes {
    private Conexion conexion;

    public LnReportes() {
        this.conexion = new Conexion("empresa");
    }
    
    
}
