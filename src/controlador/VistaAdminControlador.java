/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.entidades.*;
import modelo.ln.*;
import hilos.*;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class VistaAdminControlador implements Initializable {

    @FXML
    private Button btn_salida;

    private static Stage dashBoardStage = new Stage();
    @FXML
    private TableView<Empleado> table_empleados;

    ArrayList<Empleado> ejemplo = new ArrayList<>();
    @FXML
    private TextField txt_nombreEmp;
    @FXML
    private TextField txt_apellUEmp;
    @FXML
    private TextField txt_apellDEmp;
    @FXML
    private TextField txt_cedEmp;
    @FXML
    private TextField txt_telEmp;
    @FXML
    private TextField txt_correoEmp;
    @FXML
    private ComboBox<String> box_rolEmp;
    @FXML
    private ComboBox<String> box_estadoEmp;
    @FXML
    private Pane panel_empleado;

    private LnRoles rol;

    private LnEstado estado;

    private LnReportes reportes;

    private LnEmpleado empleado;
    @FXML
    private TextField txt_buscarEmp;

    private boolean busquedaEmpleado = false;

    private boolean actualizarEmpleado = false;

    private boolean tablaSecundariaEmpleado = false;

    private String codigoEmpleado = "";

    @FXML
    private Button btnEmpleadoAccion;
    @FXML
    private Pane panel_botones;
    @FXML
    private Button btn_subModuloEmp;
    @FXML
    private Button btn_subModuloRol;
    @FXML
    private Button btn_subModuloRep;
    @FXML
    private Pane panel_roles;
    @FXML
    private TextField txt_nombreRol;
    @FXML
    private TextField txt_pagoHoraRol;
    @FXML
    private TableView<Roles> table_rol;
    @FXML
    private Button btn_agregarRol;

    private boolean editarTablaRol = false;
    @FXML
    private TableView<RegistroInnerJoin> table_reportes;
    @FXML
    private Button btn_reporteGeneral;
    @FXML
    private Button btnFechaReporte;
    @FXML
    private ComboBox<String> box_fechaUno;
    @FXML
    private ComboBox<String> box_fechaDos;
    @FXML
    private Pane panel_reportes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rol = new LnRoles();
        estado = new LnEstado();
        empleado = new LnEmpleado();
        reportes = new LnReportes();

        /*Panel empleado*/
        txt_buscarEmp.setPromptText("Ingrese el código del empleado...");
        agregarFilasPanelEmpleado();
        agregarColumnasTablaPanelEmpleado();
        agregarRolesBoxPanelEmpleado();
        agregarEstadosBoxPanelEmpleado();
        crearMenuTablaEmpleado();

        /*Pane rol*/
        agregarFilasPanelRol();
        agregarColumnasTablaPanelRol();
        crearMenuTablaRol();

        /*Panel Reporte*/
        agregarFilasTablaPanelRep();
        agregarColumnasTablaPanelRep();
        llenarComboBoxFechaUno();
        llenarComboBoxFechaDos();
        //crearMenuTablaReporte(); -> Existe un error al crear el pdf, en el hilo
        

    }

    public boolean getTablaSecundariaEmpleado() {
        return tablaSecundariaEmpleado;
    }

    public void setTablaSecundariaEmpleado(boolean tablaSecundariaEmpleado) {
        this.tablaSecundariaEmpleado = tablaSecundariaEmpleado;
    }

    public boolean getBusquedaEmpleado() {
        return busquedaEmpleado;
    }

    public void setBusquedaEmpleado(boolean busquedaEmpleado) {
        this.busquedaEmpleado = busquedaEmpleado;
    }

    public boolean getActualizarEmpleado() {
        return actualizarEmpleado;
    }

    public void setActualizarEmpleado(boolean actualizarEmpleado) {
        this.actualizarEmpleado = actualizarEmpleado;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public boolean getEditarTablaRol() {
        return editarTablaRol;
    }

    public void setEditarTablaRol(boolean editarTablaRol) {
        this.editarTablaRol = editarTablaRol;
    }

    public void alertaSuccess(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Success");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void alertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void eliminarEmpleadoItemMenu() {
        int index = table_empleados.getSelectionModel().getSelectedIndex();
        if (getBusquedaEmpleado() == false) {
            System.out.println("Funciona, codigo del empleado seleccionado " + empleado.consultaGeneral().get(index).getCodigo());
            System.out.println("Index de la tabla seleccionado: " + index);
            Empleado e = new Empleado(empleado.consultaGeneral().get(index).getCodigo());

            if (empleado.eliminarEmpleado(e)) {
                alertaSuccess("Se eliminó el empleado exitosamente");
                actualizarFilasEmpleado();
            } else {
                alertaError("No se pudo eliminar el empleado");
            }
        } else {
            Empleado buscando = new Empleado(txt_buscarEmp.getText());
            System.out.println("Funciona, codigo del empleado seleccionado " + empleado.consultaUnica(buscando).get(index).getCodigo());
            System.out.println("Index de la tabla seleccionado: " + index);
            Empleado eliminando = new Empleado(empleado.consultaUnica(buscando).get(index).getCodigo());

            if (empleado.eliminarEmpleado(eliminando)) {
                alertaSuccess("Se eliminó el empleado exitosamente");
                buscarEmpleado();
            } else {
                alertaError("No se pudo eliminar al empleado");
            }
        }
    }

    public void actualizarEmpleadoItemMenu() {
        int index = table_empleados.getSelectionModel().getSelectedIndex();
        if (getBusquedaEmpleado() == false) {
            setActualizarEmpleado(true);
            btnEmpleadoAccion.setText("ACTUALIZAR");
            System.out.println("Actualiza en la tabla principal");
            System.out.println("Actualizar=" + getActualizarEmpleado());
            box_estadoEmp.setDisable(false);
            box_estadoEmp.setOpacity(1);

            System.out.println("Funciona, codigo del empleado seleccionado " + empleado.consultaGeneral().get(index).getCodigo());
            System.out.println("Index de la tabla seleccionado: " + index);

            setCodigoEmpleado(empleado.consultaGeneral().get(index).getCodigo());
            txt_nombreEmp.setText(empleado.consultaGeneral().get(index).getNombre());
            txt_apellUEmp.setText(empleado.consultaGeneral().get(index).getApell_uno());
            txt_apellDEmp.setText(empleado.consultaGeneral().get(index).getApell_dos());
            txt_cedEmp.setText(empleado.consultaGeneral().get(index).getCedula());
            txt_telEmp.setText(empleado.consultaGeneral().get(index).getTelefono());
            txt_correoEmp.setText(empleado.consultaGeneral().get(index).getCorreo());
            box_rolEmp.getSelectionModel().select(buscarNombreRoles(empleado.consultaGeneral().get(index).getRol()));
            box_estadoEmp.getSelectionModel().select(buscarNombreEstado(empleado.consultaGeneral().get(index).getEstado()));
        } else {
            System.out.println("Actualiza en la tabla secundaria");
            setTablaSecundariaEmpleado(true);
            txt_buscarEmp.setDisable(true);

            setActualizarEmpleado(true);
            Empleado e = new Empleado(txt_buscarEmp.getText());
            System.out.println("Funciona, codigo del empleado seleccionado " + empleado.consultaUnica(e).get(index).getCodigo());
            System.out.println("Index de la tabla seleccionado: " + index);

            btnEmpleadoAccion.setText("ACTUALIZAR");
            box_estadoEmp.setDisable(false);
            box_estadoEmp.setOpacity(1);

            setCodigoEmpleado(empleado.consultaUnica(e).get(index).getCodigo());
            txt_nombreEmp.setText(empleado.consultaUnica(e).get(index).getNombre());
            txt_apellUEmp.setText(empleado.consultaUnica(e).get(index).getApell_uno());
            txt_apellDEmp.setText(empleado.consultaUnica(e).get(index).getApell_dos());
            txt_cedEmp.setText(empleado.consultaUnica(e).get(index).getCedula());
            txt_telEmp.setText(empleado.consultaUnica(e).get(index).getTelefono());
            txt_correoEmp.setText(empleado.consultaUnica(e).get(index).getCorreo());
            box_rolEmp.getSelectionModel().select(buscarNombreRoles(empleado.consultaUnica(e).get(index).getRol()));
            box_estadoEmp.getSelectionModel().select(buscarNombreEstado(empleado.consultaUnica(e).get(index).getEstado()));
        }
    }

    public void crearMenuTablaEmpleado() {
        table_empleados.setRowFactory(
                new Callback<TableView<Empleado>, TableRow<Empleado>>() {
            @Override
            public TableRow<Empleado> call(TableView<Empleado> tableView) {
                TableRow<Empleado> row = new TableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                MenuItem eliminar = new MenuItem("Eliminar");
                MenuItem editar = new MenuItem("Editar");

                //Método funciona adecuadamente
                eliminar.setOnAction(e -> eliminarEmpleadoItemMenu());
                editar.setOnAction(e -> actualizarEmpleadoItemMenu());

                rowMenu.getItems().addAll(eliminar, editar);

                //only display context menu for non-null items:
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise((ContextMenu) null));
                return row;
            }
        });
    }

    //agrega los tipos de roles exixtentes en la base de datos a un comboBox
    public void agregarRolesBoxPanelEmpleado() {
        box_rolEmp.getItems().removeAll(box_rolEmp.getItems());
        for (int i = 0; i < rol.consultaRoles().size(); i++) {
            box_rolEmp.getItems().addAll(rol.consultaRoles().get(i).getNombre_rol());
        }
        box_rolEmp.getSelectionModel().select("Seleccione rol");
    }

    //agrega los tipos de estados exixtentes en la base de datos a un comboBox
    public void agregarEstadosBoxPanelEmpleado() {
        box_estadoEmp.setDisable(true);
        box_estadoEmp.setOpacity(0.9);

        box_estadoEmp.getItems().removeAll(box_estadoEmp.getItems());
        for (int i = 0; i < estado.consultaEstados().size(); i++) {
            box_estadoEmp.getItems().addAll(estado.consultaEstados().get(i).getNombre_estado());
        }
        box_estadoEmp.getSelectionModel().select("Activo");
    }

    //Busca el id del rol apartir del nombre
    public int buscarIdRoles(String nombre) {
        int index = 0;
        for (int i = 0; i < rol.consultaRoles().size(); i++) {
            if (rol.consultaRoles().get(i).getNombre_rol().equals(nombre)) {
                index = rol.consultaRoles().get(i).getId_rol();
            }
        }
        return index;
    }

    //busca el nombre del rol apartir de id
    public String buscarNombreRoles(int index) {
        String nombre = "";
        for (int i = 0; i < rol.consultaRoles().size(); i++) {
            if (rol.consultaRoles().get(i).getId_rol() == index) {
                nombre = rol.consultaRoles().get(i).getNombre_rol();
            }
        }
        return nombre;
    }

    //busca el id del estado apartir del nombre
    public int buscarIdEstado(String nombre) {
        int index = 0;
        for (int i = 0; i < estado.consultaEstados().size(); i++) {
            if (estado.consultaEstados().get(i).getNombre_estado().equals(nombre)) {
                index = estado.consultaEstados().get(i).getId_estado();
            }
        }
        return index;
    }

    //busca el nombre del estado apartir del id
    public String buscarNombreEstado(int index) {
        String nombre = "";
        for (int i = 0; i < estado.consultaEstados().size(); i++) {
            if (estado.consultaEstados().get(i).getId_estado() == index) {
                nombre = estado.consultaEstados().get(i).getNombre_estado();
            }
        }
        return nombre;
    }

    public void actualizarFilasEmpleado() {
        agregarFilasPanelEmpleado();
    }

    public void vaciarCamposTextoEmpleado() {
        txt_nombreEmp.setText("");
        txt_apellUEmp.setText("");
        txt_apellDEmp.setText("");
        txt_cedEmp.setText("");
        txt_telEmp.setText("");
        txt_correoEmp.setText("");
        box_rolEmp.getSelectionModel().select("Seleccione rol");
    }

    public void agregarColumnasTablaPanelEmpleado() {
        TableColumn uno = new TableColumn("Cod");
        uno.setCellValueFactory(new PropertyValueFactory<Empleado, String>("codigo"));
        uno.sortableProperty().set(false);

        TableColumn dos = new TableColumn("Nom");
        dos.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        dos.sortableProperty().set(false);

        TableColumn tres = new TableColumn("ApellU");
        tres.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apell_uno"));
        tres.sortableProperty().set(false);

        TableColumn cuatro = new TableColumn("ApellD");
        cuatro.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apell_dos"));
        cuatro.sortableProperty().set(false);

        TableColumn cinco = new TableColumn("Ced");
        cinco.setCellValueFactory(new PropertyValueFactory<Empleado, String>("cedula"));
        cinco.sortableProperty().set(false);

        TableColumn seis = new TableColumn("Numero");
        seis.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        seis.sortableProperty().set(false);

        TableColumn siete = new TableColumn("Correo");
        siete.setCellValueFactory(new PropertyValueFactory<Empleado, String>("correo"));
        siete.sortableProperty().set(false);

        TableColumn ocho = new TableColumn("Rol");
        ocho.setCellValueFactory(new PropertyValueFactory<Empleado, String>("rol"));
        ocho.sortableProperty().set(false);

        TableColumn nueve = new TableColumn("Estado");
        nueve.setCellValueFactory(new PropertyValueFactory<Empleado, String>("estado"));
        nueve.sortableProperty().set(false);

        TableColumn diez = new TableColumn("Salario");
        diez.setCellValueFactory(new PropertyValueFactory<Empleado, String>("salario"));
        diez.sortableProperty().set(false);

        table_empleados.getColumns().addAll(uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, diez);
    }

    public void agregarFilasPanelEmpleado() {
        final ObservableList<Empleado> data = FXCollections.observableArrayList();

        for (int i = 0; i < empleado.consultaGeneral().size(); i++) {
            data.add(empleado.consultaGeneral().get(i));//Acá se cargará los datos de los empleados mediante la consulta que se encuentra en empleados
        }
        table_empleados.setItems(data);
    }

    public void buscarEmpleado() {
        Empleado e = new Empleado(txt_buscarEmp.getText());
        final ObservableList<Empleado> data = FXCollections.observableArrayList();

        for (int i = 0; i < empleado.consultaUnica(e).size(); i++) {
            data.add(empleado.consultaUnica(e).get(i));//Acá se cargará los datos de los empleados mediante la consulta que se encuentra en empleados
        }
        table_empleados.setItems(data);
    }

    /*METODOS DE SUBPANEL DEL ROL*/
    //agregando filas a la tabla de roles
    public void agregarFilasPanelRol() {
        final ObservableList<Roles> data = FXCollections.observableArrayList();

        for (int i = 0; i < rol.consultaRoles().size(); i++) {
            data.add(rol.consultaRoles().get(i));//Acá se cargará los datos de los empleados mediante la consulta que se encuentra en empleados
        }
        table_rol.setItems(data);
    }

    //agregando columnas a la tabla de roles
    public void agregarColumnasTablaPanelRol() {
        TableColumn uno = new TableColumn("ID");
        uno.setCellValueFactory(new PropertyValueFactory<Roles, String>("id_rol"));
        uno.sortableProperty().set(false);
        uno.prefWidthProperty().bind(table_rol.widthProperty().multiply(0.35));

        TableColumn dos = new TableColumn("Nombre");
        dos.setCellValueFactory(new PropertyValueFactory<Roles, String>("nombre_rol"));
        dos.sortableProperty().set(false);
        dos.prefWidthProperty().bind(table_rol.widthProperty().multiply(0.35));

        TableColumn tres = new TableColumn("Pago por hora");
        tres.setCellValueFactory(new PropertyValueFactory<Roles, String>("pagoHora_rol"));
        tres.sortableProperty().set(false);
        tres.prefWidthProperty().bind(table_rol.widthProperty().multiply(0.35));

        table_rol.getColumns().addAll(uno, dos, tres);
    }

    //creando menu para la tabla roles
    public void crearMenuTablaRol() {
        table_rol.setRowFactory(
                new Callback<TableView<Roles>, TableRow<Roles>>() {
            @Override
            public TableRow<Roles> call(TableView<Roles> tableView) {
                TableRow<Roles> row = new TableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                MenuItem eliminar = new MenuItem("Eliminar");
                MenuItem editar = new MenuItem("Editar");

                //Método funciona adecuadamente
                eliminar.setOnAction(e -> eliminarRolItemMenu());
                editar.setOnAction(e -> actualizarRolItemMenu());

                rowMenu.getItems().addAll(eliminar, editar);

                //only display context menu for non-null items:
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise((ContextMenu) null));
                return row;
            }
        });
    }

    public void eliminarRolItemMenu() {
        int index = table_rol.getSelectionModel().getSelectedIndex();

        int cod = rol.consultaRoles().get(index).getId_rol();

        Roles elim = new Roles(cod);

        if (rol.eliminarRol(elim)) {
            alertaSuccess("Se eliminó el rol exitosamente");
            agregarFilasPanelRol();
        } else {
            alertaError("No se pudo eliminar el rol, puede que algún empleado esté ejerciendo esta labor");
        }
    }

    public void actualizarRolItemMenu() {
        int index = table_rol.getSelectionModel().getSelectedIndex();

        String nombre = rol.consultaRoles().get(index).getNombre_rol();
        int horaLab = rol.consultaRoles().get(index).getPagoHora_rol();

        txt_nombreRol.setText(nombre);
        txt_pagoHoraRol.setText(horaLab + "");
        table_rol.setDisable(true);

        setEditarTablaRol(true);
        btn_agregarRol.setText("EDITAR ROL");
    }

    @FXML
    private void volver_login(ActionEvent event) {
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

    /*Metodos del subpanel de reporte*/
    public void agregarFilasTablaPanelRep() {
        ObservableList<RegistroInnerJoin> data = FXCollections.observableArrayList();
        //RegistroInnerJoin rep = new RegistroInnerJoin();

        for (int i = 0; i < reportes.consultaInnerDos().size(); i++) {
            data.add(reportes.consultaInnerDos().get(i));//Acá se cargará los datos de los empleados mediante la consulta que se encuentra en reportes
        }

        table_reportes.setItems(data);
    }

    public void agregarColumnasTablaPanelRep() {
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

        table_reportes.getColumns().addAll(uno, nueve, diez, once, dos, tres, cuatro, cinco, seis, siete, ocho);
    }
    
    public void crearMenuTablaReporte() {
        table_reportes.setRowFactory(
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

        int index = table_reportes.getSelectionModel().getSelectedIndex();

        int idRep = reportes.consultaInnerDos().get(index).getId_rep();
        System.out.println(idRep);
        
        Runnable runn = new GenerarReporteAdminHilo(index);
        Thread tr = new Thread(runn);
        tr.start();
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

    @FXML
    private void agregarNuevoEmpleado(ActionEvent event) {
        if (getActualizarEmpleado() == false) {
            String cod = empleado.generarCod();
            String nombre = txt_nombreEmp.getText();
            String apellidoU = txt_apellUEmp.getText();
            String apellidoD = txt_apellDEmp.getText();
            String cedula = txt_cedEmp.getText();
            String tel = txt_telEmp.getText();
            String correo = txt_correoEmp.getText();

            String rol = box_rolEmp.getValue();
            int idRol = buscarIdRoles(rol);

            String est = box_estadoEmp.getValue();
            int idEstado = buscarIdEstado(est);

            int salario = 0;
            /*String est = box_estadoEmp.getValue();//captura el id del comboBox(es como un JtextField.getText())
        String rol = box_rolEmp.getValue();*/

            System.out.println("Valor de rol select: " + rol + " Y su incide es: " + buscarIdRoles(rol));
            System.out.println("Valor de estado select: " + est + " Y su incide es: " + buscarIdEstado(est));

            //panel_empleado.setVisible(false);-> funciona
            Empleado nuevo = new Empleado(cod, nombre, apellidoU, apellidoD, cedula, tel, correo, idRol, idEstado, salario);

            if (txt_nombreEmp.getText().length() != 0 && txt_apellUEmp.getText().length() != 0 && txt_apellDEmp.getText().length() != 0
                    && txt_cedEmp.getText().length() != 0 && txt_telEmp.getText().length() != 0 && txt_correoEmp.getText().length() != 0) {
                if (empleado.ingresarEmpleado(nuevo)) {
                    actualizarFilasEmpleado();
                    vaciarCamposTextoEmpleado();

                    alertaSuccess("Se agregó el empleado exitosamente");

                } else {
                    alertaError("No se pudo agregar al empleado, intente nuevamente");
                }
            } else {
                alertaError("No pueden existir campos vacíos");
            }
        } else {
            //Aqui se ejecutará el metodo de actualizar empleado
            btnEmpleadoAccion.setText("NUEVO EMPLEADO");
            setActualizarEmpleado(false);
            System.out.println("Actualizar=" + getActualizarEmpleado());

            //Capturando información de empleado
            String nombre = txt_nombreEmp.getText();
            String apellidoU = txt_apellUEmp.getText();
            String apellidoD = txt_apellDEmp.getText();
            String cedula = txt_cedEmp.getText();
            String tel = txt_telEmp.getText();
            String correo = txt_correoEmp.getText();

            String rol = box_rolEmp.getValue();
            int idRol = buscarIdRoles(rol);

            String est = box_estadoEmp.getValue();
            int idEstado = buscarIdEstado(est);

            //luego de ejecutar el metodo de acualizar se vaciarán los campos de texto
            Empleado nuevo = new Empleado(getCodigoEmpleado(), nombre, apellidoU, apellidoD, cedula, tel, correo, idRol, idEstado);
            if (empleado.actualizarEmpleado(nuevo)) {
                vaciarCamposTextoEmpleado();
                box_estadoEmp.setDisable(true);
                box_estadoEmp.setOpacity(0.9);
                box_estadoEmp.getSelectionModel().select("Activo");

                if (getTablaSecundariaEmpleado() == false) {
                    actualizarFilasEmpleado();
                } else {
                    buscarEmpleado();
                    txt_buscarEmp.setDisable(false);
                    setTablaSecundariaEmpleado(false);
                }
                alertaSuccess("Se ha actualizado el empleado exitosamente");
                //System.out.println("Se actualizó empleado");
            } else {
                alertaError("No se pudo actualizar al empleado");
                System.out.println("No se actualizó empleado");
            }

        }

    }

    @FXML
    private void BuscarEmpleado(KeyEvent event) {
        try {
            if (txt_buscarEmp.getText().length() == 0) {//toLowerCase, pasa todo en minuscula
                System.out.println("Salí");
                actualizarFilasEmpleado();
                setBusquedaEmpleado(false);
            } else {
                System.out.println("Entré");
                System.out.println(txt_buscarEmp.getText());
                buscarEmpleado();
                setBusquedaEmpleado(true);
            }
        } catch (Exception e) {
            System.out.println("se capturó el error: " + e.getMessage());
        }

    }

    @FXML
    private void BuscarEmpleado(MouseEvent event) {
    }

    @FXML
    private void subModuloEmp(ActionEvent event) {
        panel_empleado.setVisible(true);
        panel_roles.setVisible(false);
        panel_reportes.setVisible(false);
        agregarRolesBoxPanelEmpleado();
    }

    @FXML
    private void subModuloRol(ActionEvent event) {
        panel_empleado.setVisible(false);
        panel_roles.setVisible(true);
        panel_reportes.setVisible(false);
    }

    @FXML
    private void subModuloRep(ActionEvent event) {
        panel_empleado.setVisible(false);
        panel_roles.setVisible(false);
        panel_reportes.setVisible(true);
    }

    @FXML
    private void metodoAgregarRol(ActionEvent event) {

        if (getEditarTablaRol() == false) {

            if (txt_nombreRol.getText().length() == 0 || txt_pagoHoraRol.getText().length() == 0) {
                alertaError("No pueden existir campos vacios");
            } else {
                String nombre = txt_nombreRol.getText();
                int horaLab = Integer.parseInt(txt_pagoHoraRol.getText());

                Roles nuevo = new Roles(nombre, horaLab);

                if (rol.ingresarRol(nuevo)) {
                    alertaSuccess("Se agregó un nuevo rol");
                    agregarFilasPanelRol();
                    txt_nombreRol.setText("");
                    txt_pagoHoraRol.setText("");

                } else {
                    alertaError("No se puede agregar rol");
                }
            }

        } else {
            int index = table_rol.getSelectionModel().getSelectedIndex();
            int cod = rol.consultaRoles().get(index).getId_rol();
            String nombre = txt_nombreRol.getText();
            int horaLab = Integer.parseInt(txt_pagoHoraRol.getText());
            Roles edit = new Roles(cod, nombre, horaLab);

            //Aqui de editará el rol
            if (rol.modificarRol(edit)) {
                alertaSuccess("Se actualizó el rol exitosamente");
                agregarFilasPanelRol();
                txt_nombreRol.setText("");
                txt_pagoHoraRol.setText("");
                setEditarTablaRol(false);
                table_rol.setDisable(false);
                btn_agregarRol.setText("AGREGAR ROL");
            } else {
                alertaError("No se pudo actualizar rol");
            }

        }
    }

    @FXML
    private void crearReporteGeneral(ActionEvent event) {
        Runnable r1 = new CrearReporteGeneralAdminHilo();
        Thread t1 = new Thread(r1);
        t1.start();
    }

    @FXML
    private void crearReporteConFecha(ActionEvent event) {
        String fechaU = box_fechaUno.getValue();
        String fechaD = box_fechaDos.getValue();
        
        System.out.println(fechaU);
        System.out.println(fechaD);

        Runnable r2 = new CrearReporteFechaAdminHilo(fechaU, fechaD);
        Thread t2 = new Thread(r2);
        t2.start();
    }

}
