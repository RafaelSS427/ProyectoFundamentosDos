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
public class LnReportes {

    private Conexion conexion;

    public LnReportes() {
        this.conexion = new Conexion("empresa");
    }

    public void nuevoReporte(Reportes rep) {
        //boolean res = false;
        String query = "INSERT INTO reportes(cod_emp2, horaEntrada, fecha, laborando)VALUES(?, ?, ? , 1)";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) conexion.getConector().prepareStatement(query);
            stmt.setString(1, rep.getCod_emp2());
            stmt.setInt(2, rep.getHoraEntrada());
            stmt.setString(3, rep.getFecha());
            //stmt.setInt(4, rep.getLaborando());

            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Error al ingresar codigo: " + e.getMessage());
        }

        //return res;
    }

    public void actualizarReporte(Reportes rep) {
        //boolean res = false;
        String query = "UPDATE reportes SET horaSalida=?, laborando= 0, totalHorasLab=?, salario=? WHERE cod_emp2 = ? AND fecha = ?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setInt(1, rep.getHoraSalida());
            //stmt.setInt(2, rep.getLaborando());
            stmt.setInt(2, rep.getTotalHorasLab());
            stmt.setInt(3, rep.getSalario());
            stmt.setString(4, rep.getCod_emp2());
            stmt.setString(5, rep.getFecha());

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Ocurrio un error al actualizar al empleado: " + ex.getMessage());
        }
        //return res;
    }

    public ArrayList<Reportes> consultaLaborado() {
        String query = "SELECT laborando FROM reportes WHERE ORDER BY id_rep DESC LIMIT 1";
        ResultSet rs = conexion.getQuery(query);
        ArrayList<Reportes> laborando = new ArrayList<>();

        try {
            Reportes rep;

            while (rs.next()) {
                rep = new Reportes();
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));
                laborando.add(rep);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la consulta: " + e.getMessage());
        }

        return laborando;
    }

    public ArrayList<Reportes> consultaComplementaria(Reportes rep) {
        ArrayList<Reportes> reporte = new ArrayList<>();
        String query = "SELECT id_rep, cod_emp2, horaEntrada, fecha, laborando FROM reportes WHERE cod_emp2= ? AND fecha= ?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, rep.getCod_emp2());
            stmt.setString(2, rep.getFecha());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rep.setId_rep(Integer.parseInt(rs.getString("id_rep")));
                rep.setCod_emp2(rs.getString("cod_emp2"));
                rep.setHoraEntrada(Integer.parseInt(rs.getString("horaEntrada")));
                rep.setFecha(rs.getString("fecha"));
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

    public ArrayList<Reportes> consultaHoraSalida(Reportes rep) {
        ArrayList<Reportes> reporte = new ArrayList<>();
        String query = "SELECT id_rep, cod_emp2, horaSalida, fecha, laborando FROM reportes WHERE cod_emp2= ? AND fecha= ?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, rep.getCod_emp2());
            stmt.setString(2, rep.getFecha());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rep.setId_rep(Integer.parseInt(rs.getString("id_rep")));
                rep.setCod_emp2(rs.getString("cod_emp2"));
                rep.setHoraSalida(Integer.parseInt(rs.getString("horaSalida")));
                rep.setFecha(rs.getString("fecha"));
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

    public ArrayList<Reportes> consultaGenerealEmpleado(Reportes rep) {
        ArrayList<Reportes> reporte = new ArrayList<>();
        String query = "SELECT fecha FROM reportes";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, rep.getCod_emp2());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rep.setId_rep(Integer.parseInt(rs.getString("id_rep")));
                rep.setCod_emp2(rs.getString("cod_emp2"));
                rep.setHoraEntrada(Integer.parseInt(rs.getString("horaEntrada")));
                rep.setHoraSalida(Integer.parseInt(rs.getString("horaSalida")));
                rep.setFecha(rs.getString("fecha"));
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));
                rep.setTotalHorasLab(Integer.parseInt(rs.getString("totalHorasLab")));
                rep.setSalario(Integer.parseInt(rs.getString("salario")));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

    //consulta innerJoin que se utilizará para crear el/los reportes 
    public ArrayList<RegistroInnerJoin> consultaInner(RegistroInnerJoin repD) {
        ArrayList<RegistroInnerJoin> reporte = new ArrayList<>();
        String query = "SELECT reportes.id_rep, reportes.cod_emp2, reportes.horaEntrada,"
                + " reportes.horaSalida, reportes.fecha, reportes.laborando, reportes.totalHorasLab,"
                + " reportes.salario, (SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2), (SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp), (SELECT roles.nombre_rol FROM roles "
                + "WHERE roles.id_rol = empleados.rol_emp) FROM reportes INNER JOIN empleados ON reportes.cod_emp2 = empleados.cod_emp "
                + "WHERE reportes.cod_emp2 = ?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, repD.getCod_emp2());

            ResultSet rs = stmt.executeQuery();

            RegistroInnerJoin rep;
            while (rs.next()) {
                rep = new RegistroInnerJoin();

                rep.setId_rep(Integer.parseInt(rs.getString("id_rep")));
                rep.setCod_emp2(rs.getString("cod_emp2"));
                rep.setHoraEntrada(Integer.parseInt(rs.getString("horaEntrada")));
                rep.setHoraSalida(Integer.parseInt(rs.getString("horaSalida")));
                rep.setFecha(rs.getString("fecha"));
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));
                rep.setTotalHorasLab(Integer.parseInt(rs.getString("totalHorasLab")));
                rep.setSalario(Integer.parseInt(rs.getString("salario")));
                rep.setNombre_emp(rs.getString("(SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2)"));
                rep.setEstad_emp(rs.getString("(SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp)"));
                rep.setRol(rs.getString("(SELECT roles.nombre_rol FROM roles WHERE roles.id_rol = empleados.rol_emp)"));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

    public ArrayList<RegistroInnerJoin> consultaInnerDos() {
        ArrayList<RegistroInnerJoin> reporte = new ArrayList<>();
        String query = "SELECT reportes.id_rep, reportes.cod_emp2, reportes.horaEntrada,"
                + " reportes.horaSalida, reportes.fecha, reportes.laborando, reportes.totalHorasLab,"
                + " reportes.salario, (SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2), (SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp), (SELECT roles.nombre_rol FROM roles "
                + "WHERE roles.id_rol = empleados.rol_emp) FROM reportes INNER JOIN empleados ON reportes.cod_emp2 = empleados.cod_emp";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            RegistroInnerJoin rep;

            while (rs.next()) {
                rep = new RegistroInnerJoin();

                rep.setId_rep(Integer.parseInt(rs.getString("id_rep")));
                rep.setCod_emp2(rs.getString("cod_emp2"));
                rep.setHoraEntrada(Integer.parseInt(rs.getString("horaEntrada")));
                rep.setHoraSalida(Integer.parseInt(rs.getString("horaSalida")));
                rep.setFecha(rs.getString("fecha"));
                rep.setLaborando(Integer.parseInt(rs.getString("laborando")));
                rep.setTotalHorasLab(Integer.parseInt(rs.getString("totalHorasLab")));
                rep.setSalario(Integer.parseInt(rs.getString("salario")));
                rep.setNombre_emp(rs.getString("(SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2)"));
                rep.setEstad_emp(rs.getString("(SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp)"));
                rep.setRol(rs.getString("(SELECT roles.nombre_rol FROM roles WHERE roles.id_rol = empleados.rol_emp)"));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

    public ArrayList<Reportes> consultaFecha() {
        ArrayList<Reportes> reporte = new ArrayList<>();
        String query = "SELECT DISTINCT fecha FROM reportes";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            Reportes rep;

            while (rs.next()) {
                rep = new Reportes();

                rep.setFecha(rs.getString("fecha"));

                reporte.add(rep);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error en la consulta: " + e.getMessage());
        }

        return reporte;
    }

}
