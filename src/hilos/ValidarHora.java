/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 *
 * @author HP
 */
public class ValidarHora implements Runnable {

    private Button boton;

    public ValidarHora(Button boton) {
        this.boton = boton;
    }

    public void run() {
        boolean x = true;
        //Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;

        /*minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);*/
        do {            
            try {
                TimeUnit.MINUTES.sleep(60);
                Calendar calendario = Calendar.getInstance();
                hora = calendario.get(Calendar.HOUR_OF_DAY);
                //minutos = calendario.get(Calendar.MINUTE);
                System.out.println(hora);
                if (hora == 22) {
                    boton.setDisable(false);
                    x = false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ValidarHora.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (x);
    }
}
