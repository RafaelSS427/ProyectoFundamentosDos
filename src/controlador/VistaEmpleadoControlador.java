/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hilos.GenerarReporteHilo;
import hilos.ActualizarReporteHilo;
import hilos.CrearReporteFechaHilo;
import hilos.CrearReporteGeneralHilo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.util.Callback;
import modelo.entidades.*;
import modelo.ln.*;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class VistaEmpleadoControlador implements Initializable {

    /*Variables locales*/
    private static Stage dashBoardStage = new Stage();
    private LnEmpleado empleado;
    private LnIngresoEmpleados ing_emp;
    private LnReportes reportes;

    private boolean tipoHora = false;
    private boolean existeEmpleado = false;
    private boolean nuevoReporte = false;

    @FXML
    private Button btn_marcas;
    @FXML
    private Button btn_reportes;
    @FXML
    private Button btn_salir;
    @FXML
    private Label label_nombre;
    @FXML
    private Label label_cedula;
    @FXML
    private Button btn_capturaH;
    @FXML
    private Label label_entradaH;
    @FXML
    private Label label_salidaH;
    @FXML
    private CheckBox check_edit;
    @FXML
    private Pane subModulo_marcas;
    @FXML
    private Pane subModulo_reportes;
    @FXML
    private TableView<RegistroInnerJoin> table_reporteEmp;
    @FXML
    private Button btn_reporteGeneral;
    @FXML
    private Button btnFechaReporte;
    @FXML
    private ComboBox<String> box_fechaUno;
    @FXML
    private ComboBox<String> box_fechaDos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado = new LnEmpleado();
        ing_emp = new LnIngresoEmpleados();
        reportes = new LnReportes();

        /*Sub modulo de marcas*/
        label_entradaH.setVisible(false);
        label_salidaH.setVisible(false);
        visibleInfoEmpleado();
        try {
            consultaComp(); //5GLHR2
        } catch (Exception e) {
        }

        /*Sub modulo de reportes*/
        agregarColumnasTabla();
        crearMenuTablaReporte();

    }

    /*METODOS GET Y SET*/
    public boolean getTipoHora() {
        return tipoHora;
    }

    public void setTipoHora(boolean tipoHora) {
        this.tipoHora = tipoHora;
    }

    public boolean getExisteEmpleado() {
        return existeEmpleado;
    }

    public void setExisteEmpleado(boolean existeEmpleado) {
        this.existeEmpleado = existeEmpleado;
    }

    /*FIN DE METODOS GET Y SET*/
 /*----------------------------------*/
 /*METODODS LOCALES*/
    public void visibleInfoEmpleado() {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();

        Empleado emp = new Empleado(cod);

        try {
            label_nombre.setText(empleado.infoEmpl(emp).get(0).getNombre());
            label_cedula.setText(empleado.infoEmpl(emp).get(0).getCedula());
        } catch (Exception e) {
        }
    }

    public void consultaComp() {
        Calendar calendario = Calendar.getInstance();
        int dia, mes, ano;
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();

        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);

        String fecha = ano + "-" + (mes + 1) + "-" + dia;

        Reportes nuevo = new Reportes(cod, fecha);
        System.out.println(cod + "/" + fecha);

        if (reportes.consultaComplementaria(nuevo) == null || reportes.consultaComplementaria(nuevo).isEmpty()) {
            System.out.println("Aún no existe un empleado a que consultar");
            check_edit.setVisible(false);
        } else {
            if (reportes.consultaComplementaria(nuevo).get(0).getLaborando() == 1) {
                btn_capturaH.setText("MARCAR SALIDA");
                btn_capturaH.setDisable(true);
                btn_capturaH.setStyle("-fx-background-color:orange;");
                setTipoHora(true);
                label_entradaH.setText(reportes.consultaComplementaria(nuevo).get(0).getHoraEntrada() + "");
                label_entradaH.setVisible(true);
                System.out.println("entré");
            } else {
                if (reportes.consultaHoraSalida(nuevo).get(0).getLaborando() == 0) {
                    btn_capturaH.setText("MARCAR SALIDA");
                    btn_capturaH.setStyle("-fx-background-color:orange;");
                    btn_capturaH.setDisable(true);
                    check_edit.setVisible(false);
                    label_entradaH.setText(reportes.consultaComplementaria(nuevo).get(0).getHoraEntrada() + "");
                    label_salidaH.setText(reportes.consultaHoraSalida(nuevo).get(0).getHoraSalida() + "");
                    label_entradaH.setVisible(true);
                    label_salidaH.setVisible(true);
                }

            }
        }

        System.out.println(reportes.consultaComplementaria(nuevo).get(0).getHoraEntrada());
    }

    public void agregarColumnasTabla() {
        TableColumn uno = new TableColumn("id_rep");
        uno.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("id_rep"));
        uno.sortableProperty().set(false);

        TableColumn dos = new TableColumn("cod_emp");
        dos.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("cod_emp2"));
        dos.sortableProperty().set(false);

        TableColumn tres = new TableColumn("HoraE");
        tres.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("horaEntrada"));
        tres.sortableProperty().set(false);

        TableColumn cuatro = new TableColumn("HoraS");
        cuatro.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("horaSalida"));
        cuatro.sortableProperty().set(false);

        TableColumn cinco = new TableColumn("Fecha");
        cinco.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("fecha"));
        cinco.sortableProperty().set(false);

        TableColumn seis = new TableColumn("Laborando");
        seis.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("laborando"));
        seis.sortableProperty().set(false);

        TableColumn siete = new TableColumn("TotalHorasLab");
        siete.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("totalHorasLab"));
        siete.sortableProperty().set(false);

        TableColumn ocho = new TableColumn("Salario");
        ocho.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("salario"));
        ocho.sortableProperty().set(false);

        TableColumn nueve = new TableColumn("Nombre");
        nueve.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("nombre_emp"));
        nueve.sortableProperty().set(false);

        TableColumn diez = new TableColumn("Rol");
        diez.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("rol"));
        diez.sortableProperty().set(false);

        TableColumn once = new TableColumn("Estado");
        once.setCellValueFactory(new PropertyValueFactory<RegistroInnerJoin, String>("estad_emp"));
        once.sortableProperty().set(false);

        table_reporteEmp.getColumns().addAll(uno, nueve, diez, once, dos, tres, cuatro, cinco, seis, siete, ocho);
    }

    public void agregarFilasTabla() {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        ObservableList<RegistroInnerJoin> data = FXCollections.observableArrayList();

        RegistroInnerJoin rep = new RegistroInnerJoin(cod);

        for (int i = 0; i < reportes.consultaInner(rep).size(); i++) {
            data.add(reportes.consultaInner(rep).get(i));//Acá se cargará los datos de los empleados mediante la consulta que se encuentra en reportes
        }

        table_reporteEmp.setItems(data);
    }

    public void crearMenuTablaReporte() {
        table_reporteEmp.setRowFactory(
                new Callback<TableView<RegistroInnerJoin>, TableRow<RegistroInnerJoin>>() {
            @Override
            public TableRow<RegistroInnerJoin> call(TableView<RegistroInnerJoin> tableView) {
                TableRow<RegistroInnerJoin> row = new TableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                MenuItem generar = new MenuItem("Generar Reporte");

                //Método funciona adecuadamente
                generar.setOnAction(e -> generandoReporte());

                rowMenu.getItems().addAll(generar);

                //only display context menu for non-null items:
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise((ContextMenu) null));
                return row;
            }
        });
    }

    public void generandoReporte() {
        //System.out.println("funciona");
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        LnReportes ejem = new LnReportes();
        RegistroInnerJoin rep = new RegistroInnerJoin(cod);

        int index = table_reporteEmp.getSelectionModel().getSelectedIndex();

        int idRep = ejem.consultaInner(rep).get(index).getId_rep();
        System.out.println(idRep);

        Runnable r4 = new GenerarReporteHilo(idRep);
        Thread t4 = new Thread(r4);
        t4.start();
    }

    public void llenarComboBoxFechaUno() {
        box_fechaUno.getItems().removeAll(box_fechaUno.getItems());
        for (int i = 0; i < reportes.consultaFecha().size(); i++) {
            box_fechaUno.getItems().addAll(reportes.consultaFecha().get(i).getFecha());
        }
        box_fechaUno.getSelectionModel().select(reportes.consultaFecha().get(0).getFecha());
    }

    public void llenarComboBoxFechaDos() {
        box_fechaDos.getItems().removeAll(box_fechaDos.getItems());
        for (int i = 0; i < reportes.consultaFecha().size(); i++) {
            box_fechaDos.getItems().addAll(reportes.consultaFecha().get(i).getFecha());
        }
        box_fechaDos.getSelectionModel().select(reportes.consultaFecha().get(0).getFecha());
    }

    /*FIN METODOS LOCALES*/
    @FXML
    private void nuevaMarca(ActionEvent event) {
        subModulo_marcas.setVisible(true);
        subModulo_reportes.setVisible(false);
    }

    @FXML
    private void verReportes(ActionEvent event) {
        subModulo_reportes.setVisible(true);
        subModulo_marcas.setVisible(false);
        try {
            agregarFilasTabla();
        } catch (Exception e) {
            System.out.println("Existen campos a vacios en el array");
        }

        try {
            llenarComboBoxFechaUno();
            llenarComboBoxFechaDos();
        } catch (Exception e) {
            System.out.println("Existen campos a vacios en el array");
        }

    }

    @FXML
    private void volverLogin(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que desea salir?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.setTitle("Ventana emergente");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/VistaPrograma.fxml"));
                Pane ventana = (Pane) loader.load();

                Scene scene = new Scene(ventana);
                dashBoardStage.getIcons().add(new Image(VistaProgramaControlador.class.getResourceAsStream("/imagenes/icon.png")));
                dashBoardStage.setScene(scene);
                dashBoardStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }

        } catch (IOException e) {

        }
    }

    @FXML
    private void capturarHora(ActionEvent event) {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        Reportes cod_emp2 = new Reportes(cod);
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        int dia, mes, ano;

        if (getTipoHora() == false) {

            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            ano = calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DAY_OF_MONTH);

            String fecha = ano + "-" + (mes + 1) + "-" + dia;

            String horaEntrada = hora + ":" + minutos + ":" + segundos;

            Reportes nuevo = new Reportes(cod, minutos, fecha);
            reportes.nuevoReporte(nuevo);
            //ing_emp.actualizarLaborando(1);

            label_entradaH.setText(horaEntrada);
            label_entradaH.setVisible(true);
            setTipoHora(true);

            btn_capturaH.setDisable(true);
            check_edit.setVisible(true);

            btn_capturaH.setStyle("-fx-background-color:orange;");
            btn_capturaH.setText("MARCAR SALIDA");

        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            String horaSalida = hora + ":" + minutos + ":" + segundos;

            Runnable r2 = new ActualizarReporteHilo();
            Thread t2 = new Thread(r2);
            t2.start();

            label_salidaH.setText(horaSalida);
            label_salidaH.setVisible(true);
            //btn_capturaH.setText("MARCAR ENTRADA");
            btn_capturaH.setDisable(true);
            check_edit.setVisible(false);
            //ing_emp.actualizarLaborando(0);
            setTipoHora(false);

            /*Runnable r = new ValidarHora(btn_capturaH);
            Thread t = new Thread(r);
            t.start();*/
        }

    }

    @FXML
    private void check_editMetodo(ActionEvent event) {
        if (check_edit.isSelected()) {
            btn_capturaH.setDisable(false);

        } else {
            btn_capturaH.setDisable(true);
        }
    }

    @FXML
    private void crearReporteGeneral(ActionEvent event) {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        Runnable r5 = new CrearReporteGeneralHilo(cod);
        Thread t5 = new Thread(r5);
        t5.start();
    }

    @FXML
    private void crearReporteConFecha(ActionEvent event) {
        String cod = ing_emp.consultaCodigo().get(0).getCodigo();
        String fechaU = box_fechaUno.getValue();
        String fechaD = box_fechaDos.getValue();

        Runnable r6 = new CrearReporteFechaHilo(fechaU, fechaD, cod);
        Thread t6 = new Thread(r6);
        t6.start();
    }

}
