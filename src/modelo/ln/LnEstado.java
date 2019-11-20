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

public class LnEstado {

    private Conexion conexion;//Se crea un objeto de nustra clase Conexion

    public LnEstado() {
        this.conexion = new Conexion("empresa");//Se establece la conexion
    }

    //Ingresa un estado
    public boolean ingresarEstado(Estado e) {
        boolean result = false;
        String query = "INSERT INTO estado(nombre_estado)VALUES(?)";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, e.getNombre_estado());
            result = !stmt.execute();

        } catch (SQLException ex) {
            System.out.println("No se ingresó el estado, error: " + ex.getMessage());
        }

        return result;
    }

    //Se consulta un estado, este método es importanta, ya que estos estados están pensados
    //para agregarlos en un comboBox
    public ArrayList<Estado> consultaEstados() {
        ArrayList<Estado> estados = new ArrayList<>();
        String query = "SELECT * FROM estado";
        ResultSet rs = conexion.getQuery(query);
        try {
            Estado e;
            while (rs.next()) {
                e = new Estado();

                e.setId_estado(Integer.parseInt(rs.getString("id_estado")));
                e.setNombre_estado(rs.getString("nombre_estado"));

                estados.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return estados;
    }
    System.out.print("Hola");

}
