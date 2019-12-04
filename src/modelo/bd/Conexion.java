/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bd;

import java.sql.*;
/**
 *
 * @author HP
 */
public class Conexion {
     private String user;
    private String pass;
    private String db;
    private String url;
    public Connection conector;
    
    public Conexion(String db){
        this.user = "root";
        this.pass = "";
        this.db = db;
        this.url = "jdbc:mysql://127.0.0.1/"+db;
        conector = null;
        conectar();
    }
    
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conector = (Connection) DriverManager.getConnection(url, user, pass);
            if(conector != null){
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException e) {
            System.err.println("Problema al conectar \n" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Problema al conectar \n" + ex.getMessage());
        }
    }
    
    public Connection getConector(){
        return conector;
    }
    
    public boolean setQuery(String query){
        boolean result = true;
        Statement estado = null;
        try {
            estado= (Statement) conector.createStatement();
            estado.execute(query);
            
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public ResultSet getQuery(String query){
        ResultSet resultado = null;
        Statement estado = null;
        
        try {
            estado = (Statement) conector.createStatement();
            resultado = estado.executeQuery(query);
        } catch (Exception e) {
        }
        return resultado;
    }
    
    public static void main(String[] args) {
        new Conexion("empresa");
    }
}
