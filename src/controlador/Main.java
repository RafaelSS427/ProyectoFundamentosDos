package controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/VistaPrograma.fxml"));
            Pane ventana = (Pane) loader.load();
            
            
            Scene scene = new Scene(ventana);
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagenes/icon.png")));
            //primaryStage.getIcons().add(new Image("/imagenes/icon.png"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
