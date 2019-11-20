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

public class LnUsuario {

    private Conexion conexion;//Se crea un objeto de la clase Conexion

    public LnUsuario() {
        this.conexion = new Conexion("empresa");//Se establese la conexion
    }

    //Inserta nuevo usuario
    public boolean insertar(Usuario nuevo) {
        boolean res = false;
        String query = "INSERT INTO administradores(User, Pass) VALUE (?,?)";
        PreparedStatement preparedStam;

        try {
            preparedStam = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            preparedStam.setString(1, nuevo.getUser());
            preparedStam.setString(2, nuevo.getPass());

            res = !preparedStam.execute();

        } catch (SQLException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
        return res;
    }

    //Realiza una consulta
    public ArrayList<Usuario> consulta() {
        ResultSet result = conexion.getQuery("SELECT * FROM administradores");
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Usuario u;
            while (result.next()) {
                u = new Usuario();
                u.setUser(result.getString("User"));
                u.setPass(result.getString("Pass"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("No se realizó la consulta: " + e.getMessage());
        }

        return usuarios;
    }

    //Verifica si existe un usuario o no, metodo utilizado en el modulo del login
    public boolean verificarUsuario(Usuario user) {
        boolean existe = false;

        String query = "SELECT * FROM administradores WHERE User=? AND Pass=?";
        //ResultSet result = conexion.getQuery("SELECT * FROM administradores WHERE User=? AND Pass=?");
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                
                existe = true;
                /*resultado = rs.getRow();
                if (resultado == 1) {
                    existe = true;
                }*/
            } else {
                existe = false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return existe;
    }
}
