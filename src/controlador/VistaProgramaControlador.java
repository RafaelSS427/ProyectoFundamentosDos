/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.entidades.Empleado;
import modelo.entidades.IngresoEmpleados;
import modelo.entidades.Usuario;
import modelo.ln.LnEmpleado;
import modelo.ln.LnIngresoEmpleados;
import modelo.ln.LnUsuario;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class VistaProgramaControlador implements Initializable {

    private LnUsuario ls;

    private LnEmpleado le;

    private LnIngresoEmpleados guardandoEmp;

    @FXML
    private TextField txt_user;
    @FXML
    private TextField txt_pass;
    @FXML
    private Button btn_admin;
    @FXML
    private TextField txt_emp;

    private Stage dashBoardStage = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ls = new LnUsuario();
        le = new LnEmpleado();
        guardandoEmp = new LnIngresoEmpleados();
        /*button.setOnAction(event -> {
            nClicks++;
            System.out.println("Clicked " + nClicks + " times.");
        });*///evento directo
    }

    @FXML
    private void ingreso_sistema(ActionEvent event) {

        String usuario = txt_user.getText();
        String pass = txt_pass.getText();

        System.out.println(usuario);
        System.out.println(pass);

        Usuario u = new Usuario(usuario, pass);
        if (ls.verificarUsuario(u)) {
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/VistaAdmin.fxml"));
                Pane ventana = (Pane) loader.load();

                Scene scene = new Scene(ventana);
                dashBoardStage.setScene(scene);
                dashBoardStage.getIcons().add(new Image(VistaAdminControlador.class.getResourceAsStream("/imagenes/icon.png")));
                dashBoardStage.setResizable(false);
                dashBoardStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();

            } catch (IOException e) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Usuario o contraseña incorrecta, intente de nuevo");
            alert.showAndWait();
        }
    }

    @FXML
    private void ingreso_empleado(ActionEvent event) {
        String empleado = txt_emp.getText();
        IngresoEmpleados ing_emp = new IngresoEmpleados(empleado);

        System.out.println(empleado);

        Empleado emp = new Empleado(empleado);

        if (le.verificaEmpleado(emp)) {
            try {
                guardandoEmp.insertarCodigo(ing_emp);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/VistaEmpleado.fxml"));
                Pane ventana = (Pane) loader.load();

                Scene scene = new Scene(ventana);
                dashBoardStage.getIcons().add(new Image(VistaEmpleadoControlador.class.getResourceAsStream("/imagenes/icon.png")));
                dashBoardStage.setScene(scene);
                dashBoardStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Codigo de empleado incorrecto, intente de nuevo");
            alert.showAndWait();
        }
    }

}
