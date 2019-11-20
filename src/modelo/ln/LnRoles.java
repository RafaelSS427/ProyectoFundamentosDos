/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ln;
import modelo.bd.Conexion;
import modelo.entidades.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LnRoles {
    private Conexion conexion;//Se crea un objeto de nuestra clase conexion
    
    public LnRoles(){
        this.conexion = new Conexion("empresa");//se establece la conexon
    }
    
    //Se ingresa un rol nuevo
    public boolean ingresarRol(Roles nuevo){
        boolean resultado= false;
        String query= "INSERT INTO roles(nombre_rol, pagoHora_rol) VALUES (?,?)";
        PreparedStatement stmt;
        
        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, nuevo.getNombre_rol());
            stmt.setInt(2, nuevo.getPagoHora_rol());
            
            resultado = !stmt.execute();
        } catch (SQLException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
        return resultado;
    }
    
    //Consulta de roles existentes, pensados para agregarlos tanto a una tabla como a un comboBox
    public ArrayList<Roles> consultaRoles(){
        String query= "SELECT * FROM roles";
        ResultSet rs= conexion.getQuery(query);
        ArrayList<Roles> roles= new ArrayList<>();
        
        try {
            Roles r;
            while(rs.next()){
                r = new Roles();
                r.setId_rol(Integer.parseInt(rs.getString("id_rol")));
                r.setNombre_rol(rs.getString("nombre_rol"));
                r.setPagoHora_rol(Integer.parseInt(rs.getString("pagoHora_rol")));
                roles.add(r);
            }
            
        } catch (SQLException e) {
            System.out.println("No se realizó la consulta, error: " + e.getMessage());
        }
        
        return roles;
    }
    
    //Se elimina un rol
    public boolean eliminarRol(Roles r){
        boolean result= false;
        String query= "DELETE FROM roles WHERE id_rol=?";
        PreparedStatement stmt;
        
        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setInt(1, r.getId_rol());
            result = !stmt.execute();
        } catch (SQLException e) {
            System.out.println("no se pudo eliminar, error: " + e.getMessage());
        }
        
        return result;
    }
    
    //actualiza un rol
    public boolean modificarRol(Roles rol) {

        boolean res = false;
        String query = "UPDATE roles SET\n"
                + "		nombre_rol=?,\n"
                + "		pagoHora_rol=?\n"
                + "	WHERE id_rol=?";
        
        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            preparedStmt.setString(1, rol.getNombre_rol());
            preparedStmt.setInt(2, rol.getPagoHora_rol());
            preparedStmt.setInt(3, rol.getId_rol());

            res = !preparedStmt.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return res;
    }
}
