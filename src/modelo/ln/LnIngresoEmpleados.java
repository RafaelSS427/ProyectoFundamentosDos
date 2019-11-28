/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ln;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.bd.Conexion;
import modelo.entidades.*;

/**
 *
 * @author HP
 */
public class LnIngresoEmpleados {
    private Conexion conexion;

    public LnIngresoEmpleados() {
        conexion = new Conexion("empresa");
    }
    
    public void insertarCodigo(IngresoEmpleados ing){
        //boolean res = false;
        String query = "INSERT INTO ingresoempleados(codigo)VALUES(?)";
        PreparedStatement stmt;
        
        try {
            stmt = (PreparedStatement) conexion.getConector().prepareStatement(query);
            stmt.setString(1, ing.getCodigo());
            
            stmt.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al ingresar codigo: " + e.getMessage());
        }
        
        //return res;
    }
    
    public ArrayList<IngresoEmpleados> consultaCodigo(){
        ArrayList<IngresoEmpleados> ingreso = new ArrayList<>();
        String query = "SELECT codigo FROM ingresoempleados ORDER BY id_ing DESC";
        
        ResultSet rs = conexion.getQuery(query);
        
        try {
            IngresoEmpleados ing;
            
            while(rs.next()){
                ing = new IngresoEmpleados();
                
                ing.setCodigo(rs.getString("codigo"));
                
                ingreso.add(ing);
            }
            
        } catch (SQLException e) {
        }
        
        return ingreso;
    }
    
    
}
