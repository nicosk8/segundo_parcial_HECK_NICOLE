package sistemadeventas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaDeVentas extends Application {

    /**
     * Carga la vista y muestra la ventana principal.
     *
     * @param stage ventana principal creada por JavaFX
     * @throws Exception si no se puede cargar el archivo FXML
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/vista.fxml"));
        stage.setTitle("Sistema de Ventas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de la linea de comandos
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public static void main(String[] args) {
        launch(args);
    }
}
