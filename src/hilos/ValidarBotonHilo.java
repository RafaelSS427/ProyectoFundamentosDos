/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.entidades.Reportes;
import modelo.ln.LnIngresoEmpleados;
import modelo.ln.LnReportes;

/**
 *
 * @author HP
 */
public class ValidarBotonHilo implements Runnable {

    private LnReportes reporte;
    private LnIngresoEmpleados ing_emp;
    private Button boton;
    private Label horaEntrada;

    public ValidarBotonHilo(Button boton, Label horaEntrada) {
        reporte = new LnReportes();
        ing_emp = new LnIngresoEmpleados();
        this.boton = boton;
        this.horaEntrada = horaEntrada;
    }

    public void run() {
        boolean x = true;
        Calendar calendario = Calendar.getInstance();
        int dia, mes, ano;
        do {
            try {
                Thread.sleep(1000);
                String cod = ing_emp.consultaCodigo().get(0).getCodigo();

                ano = calendario.get(Calendar.YEAR);
                mes = calendario.get(Calendar.MONTH);
                dia = calendario.get(Calendar.DAY_OF_MONTH);

                String fecha = ano + "-" + (mes + 1) + "-" + dia;

                Reportes nuevo = new Reportes(cod, fecha);

                if (reporte.consultaComplementaria(nuevo) == null || reporte.consultaComplementaria(nuevo).isEmpty()) {
                    System.out.println("Aún no existe un empleado a que consultar");
                } else {
                    if (reporte.consultaComplementaria(nuevo).get(0).getLaborando() == 1) {
                        boton.setText("MARCAR SALIDA");
                        horaEntrada.setText(reporte.consultaComplementaria(nuevo).get(0).getHoraEntrada() + "");
                        x = false;
                    } else {

                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ValidarBotonHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (x);

    }
}
