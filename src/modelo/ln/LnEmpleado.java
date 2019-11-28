/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ln;

import java.sql.DatabaseMetaData;
import modelo.bd.Conexion;
import modelo.entidades.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LnEmpleado {

    private Conexion conexion;// se crea un objeto para la conexión

    public LnEmpleado() {
        this.conexion = new Conexion("empresa");// se establece la conexión con la base de datos
    }

    //Todos los métodos reciben como parámetro un objeto
    //Método para ingresar un nuevo empleado
    public boolean ingresarEmpleado(Empleado emp) {
        boolean res = false;
        String query = "INSERT INTO empleados(cod_emp, nombre_emp, apellidoUno_emp, apellidoDos_emp, cedula_emp, telefono_emp, correo_emp, rol_emp, estado_emp, salario_emp)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStam;

        try {
            preparedStam = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            preparedStam.setString(1, emp.getCodigo());
            preparedStam.setString(2, emp.getNombre());
            preparedStam.setString(3, emp.getApell_uno());
            preparedStam.setString(4, emp.getApell_dos());
            preparedStam.setString(5, emp.getCedula());
            preparedStam.setString(6, emp.getTelefono());
            preparedStam.setString(7, emp.getCorreo());
            preparedStam.setInt(8, emp.getRol());
            preparedStam.setInt(9, emp.getEstado());
            preparedStam.setInt(10, emp.getSalario());

            res = !preparedStam.execute();

        } catch (SQLException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
        return res;
    }

    //Método que realiza una consulta general de todos los empleados existentes
    //y se guardaran en un ArrayList
    public ArrayList<Empleado> consultaGeneral() {
        ResultSet rs = conexion.getQuery("SELECT * FROM empleados");
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            Empleado emp;

            while (rs.next()) {
                emp = new Empleado();
                emp.setCodigo(rs.getString("cod_emp"));
                emp.setNombre(rs.getString("nombre_emp"));
                emp.setApell_uno(rs.getString("apellidoUno_emp"));
                emp.setApell_dos(rs.getString("apellidoDos_emp"));
                emp.setCedula(rs.getString("cedula_emp"));
                emp.setTelefono(rs.getString("telefono_emp"));
                emp.setCorreo(rs.getString("correo_emp"));
                emp.setRol(Integer.parseInt(rs.getString("rol_emp")));
                emp.setEstado(Integer.parseInt(rs.getString("estado_emp")));
                emp.setSalario(Integer.parseInt(rs.getString("salario_emp")));

                empleados.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("No se realizó la consulta: " + e.getMessage());
        }

        return empleados;
    }

    //Realiza la busqueda de un solo empleado guardandolo en un ArrayList
    public ArrayList<Empleado> consultaUnica(Empleado emp) {
        String query = "SELECT * FROM empleados WHERE cod_emp LIKE ?";
        PreparedStatement stmt;
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, "%" + emp.getCodigo() + "%");

            ResultSet rs = stmt.executeQuery();
            //Empleado emp;

            while (rs.next()) {
                //emp = new Empleado();
                emp.setCodigo(rs.getString("cod_emp"));
                emp.setNombre(rs.getString("nombre_emp"));
                emp.setApell_uno(rs.getString("apellidoUno_emp"));
                emp.setApell_dos(rs.getString("apellidoDos_emp"));
                emp.setCedula(rs.getString("cedula_emp"));
                emp.setTelefono(rs.getString("telefono_emp"));
                emp.setCorreo(rs.getString("correo_emp"));
                emp.setRol(Integer.parseInt(rs.getString("rol_emp")));
                emp.setEstado(Integer.parseInt(rs.getString("estado_emp")));
                emp.setSalario(Integer.parseInt(rs.getString("salario_emp")));

                empleados.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("No se realizó la consulta: " + e.getMessage());
        }

        return empleados;
    }

    //Método que me permite verificar si existe un empleado o no
    //método pensado para el funcionamiento del login
    public boolean verificaEmpleado(Empleado emp) {
        boolean existe = false;

        String query = "SELECT * FROM empleados WHERE cod_emp=?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, emp.getCodigo());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = true;
            } else {
                existe = false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return existe;
    }

    //Esto es un método que me permite obtener el nombre de las columnas de nuestra tabla que
    //está en nuestra base de datos, fue pensada para agregar los nombres de las columnas de forma
    //dinámica en JTable pero al final no lo vi necesario, podemos agregarlas de forma estática
    public ArrayList<String> nomColumEmp() {
        ArrayList<String> columnas = new ArrayList<>();
        try {
            DatabaseMetaData cabezera = conexion.getConector().getMetaData();
            ResultSet rs = cabezera.getColumns(null, null, "empleados", null);

            while (rs.next()) {
                columnas.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
        }

        return columnas;
    }

    //Método que genera el código del empleado el cual se guardará en la base de datos
    //pueden cambiar los caracteres si no les parece a como están
    public String generarCod() {
        String cod[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "9", "9"};
        String salida = "";

        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * cod.length);
            salida += cod[random];
        }
        return salida;
    }

    //Elimina a un empleado
    public boolean eliminarEmpleado(Empleado e) {
        boolean resp = false;
        String query = "DELETE FROM empleados WHERE cod_emp = ?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, e.getCodigo());

            resp = !stmt.execute();
        } catch (SQLException ex) {
            System.out.println("No se pudo eliminar, error: " + ex.getMessage());
        }
        return resp;
    }

    //Actualiza un empleado
    public boolean actualizarEmpleado(Empleado emp) {
        boolean res = false;
        String query = "UPDATE empleados SET nombre_emp=?, apellidoUno_emp=?, apellidoDos_emp=?, cedula_emp=?, telefono_emp=?, correo_emp=?, rol_emp=?, estado_emp=? WHERE cod_emp=?";
        PreparedStatement stmt;

        try {
            stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
            stmt.setString(1, emp.getNombre());
            stmt.setString(2, emp.getApell_uno());
            stmt.setString(3, emp.getApell_dos());
            stmt.setString(4, emp.getCedula());
            stmt.setString(5, emp.getTelefono());
            stmt.setString(6, emp.getCorreo());
            stmt.setInt(7, emp.getRol());
            stmt.setInt(8, emp.getEstado());
            stmt.setString(9, emp.getCodigo());

            res = !stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Ocurrio un error al actualizar al empleado: " + ex.getMessage());
        }
        return res;
    }

    public ArrayList<Empleado> infoEmpl(Empleado emp) {
        ArrayList<Empleado> empleado = new ArrayList<>();
        String query = "SELECT nombre_emp, cedula_emp FROM empleados WHERE cod_emp = ?";
        PreparedStatement stmt;
        try {
            stmt = (PreparedStatement) conexion.getConector().prepareStatement(query);
            stmt.setString(1, emp.getCodigo());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                emp.setNombre(rs.getString("nombre_emp"));
                emp.setCedula(rs.getString("cedula_emp"));
                
                empleado.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }

        return empleado;
    }
}
