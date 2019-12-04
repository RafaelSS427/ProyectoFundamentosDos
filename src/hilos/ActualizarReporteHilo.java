/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.Calendar;
import modelo.entidades.Reportes;
import modelo.ln.*;

/**
 *
 * @author HP
 */
public class ActualizarReporteHilo implements Runnable {

    private LnReportes reportes;
    private LnIngresoEmpleados ing_emp;
    private LnRoles rol;
    private LnEmpleado empleados;

    private int horaC;

    public ActualizarReporteHilo() {
        reportes = new LnReportes();
        ing_emp = new LnIngresoEmpleados();
        rol = new LnRoles();
        empleados = new LnEmpleado();
    }

    public void run() {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        //Reportes cod_emp2 = new Reportes(cod);
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        int dia, mes, ano;

        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);

        String horaSalida = hora + ":" + minutos + ":" + segundos;
        String fecha = ano + "-" + (mes + 1) + "-" + dia;

        Reportes rep = new Reportes(cod, fecha);
        int horaEntrada = reportes.consultaHoraEntrada(rep).get(0).getHoraEntrada();
        int totalHorasLab = minutos - horaEntrada;
        //int totalHorasRol = Integer.parseInt(reportes.obteniendoRolHorasP(cod_emp2).get(0).getCod_emp2());
        int totalHorasRol = buscarPagoHoraRoles(cod);
        int totalSalario = totalHorasLab * totalHorasRol;

        Reportes actualizar = new Reportes(minutos, totalHorasLab, totalSalario, cod, fecha);
        reportes.actualizarReporte(actualizar);
    }

    //Busca la horaPagoRol del rol apartir del codigo del empleado
    public int buscarPagoHoraRoles(String codigo) {
        int idRol = 0;
        int index = 0;

        for (int i = 0; i < empleados.consultaGeneral().size(); i++) {
            if (empleados.consultaGeneral().get(i).getCodigo().equals(codigo)) {
                idRol = empleados.consultaGeneral().get(i).getRol();
            }
        }

        for (int i = 0; i < rol.consultaRoles().size(); i++) {
            if (rol.consultaRoles().get(i).getId_rol() == idRol) {
                index = rol.consultaRoles().get(i).getPagoHora_rol();
            }
        }

        return index;
    }
}
