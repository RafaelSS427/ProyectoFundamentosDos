/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import modelo.bd.Conexion;

/**
 *
 * @author HP
 */
public class CrearReporteGeneralAdminHilo implements Runnable{
    private Conexion conexion;

    public CrearReporteGeneralAdminHilo() {
        this.conexion = new Conexion("empresa");
    }
    
    public String generarCod() {
        String cod[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "9", "9"};
        String salida = "";

        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * cod.length);
            salida += cod[random];
        }
        return salida;
    }

    public void run() {
        float[] colum = new float[]{10f, 20f, 30f, 10f}; 
        Document documento = new Document();
        documento.setPageSize(PageSize.A4.rotate());
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/reporte" + generarCod() + ".pdf"));
            documento.open();

            PdfPTable tabla = new PdfPTable(11);
            tabla.setWidthPercentage(90);
            
            tabla.addCell("id_rep");
            tabla.addCell("Código");
            tabla.addCell("HoraE");
            tabla.addCell("HoraS");
            tabla.addCell("Fecha");
            tabla.addCell("Laborando");
            tabla.addCell("TotalHorasLab");
            tabla.addCell("salario");
            tabla.addCell("Nombre");
            tabla.addCell("Estado");
            tabla.addCell("Rol");
            
             
            String query = "SELECT reportes.id_rep, reportes.cod_emp2, reportes.horaEntrada,"
                    + " reportes.horaSalida, reportes.fecha, reportes.laborando, reportes.totalHorasLab,"
                    + " reportes.salario, (SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2), (SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp), (SELECT roles.nombre_rol FROM roles "
                    + "WHERE roles.id_rol = empleados.rol_emp) FROM reportes INNER JOIN empleados ON reportes.cod_emp2 = empleados.cod_emp";

            PreparedStatement stmt;

            try {
                stmt = (PreparedStatement) this.conexion.getConector().prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    do {
                        tabla.addCell(rs.getString("id_rep"));
                        tabla.addCell(rs.getString("cod_emp2"));
                        tabla.addCell(rs.getString("horaEntrada"));
                        tabla.addCell(rs.getString("horaSalida"));
                        tabla.addCell(rs.getString("fecha"));
                        tabla.addCell(rs.getString("laborando"));
                        tabla.addCell(rs.getString("totalHorasLab"));
                        tabla.addCell(rs.getString("salario"));
                        tabla.addCell(rs.getString("(SELECT empleados.nombre_emp FROM empleados WHERE empleados.cod_emp = reportes.cod_emp2)"));
                        tabla.addCell(rs.getString("(SELECT estado.nombre_estado FROM estado WHERE estado.id_estado = empleados.estado_emp)"));
                        tabla.addCell(rs.getString("(SELECT roles.nombre_rol FROM roles WHERE roles.id_rol = empleados.rol_emp)"));

                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (DocumentException | SQLException e) {
            }
            //tabla.setWidths(new int[]{200, 50});
            documento.close();
            System.out.println("Reporte creado");
        } catch (DocumentException | FileNotFoundException e) {
        }
    }
}
