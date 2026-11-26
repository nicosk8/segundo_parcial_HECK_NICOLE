package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ArchivoProductos;
import modelo.ArchivoTicket;
import modelo.Carrito;
import modelo.ItemCarrito;
import modelo.OperacionInvalidaException;
import modelo.Producto;

public class VistaController {

    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TextField txtCantidad;
    @FXML
    private ListView<ItemCarrito> lstCarrito;
    @FXML
    private Label lblTotal;

    private Carrito carrito;
    private ArchivoTicket archivoTicket;

    /**
     * Prepara las columnas, el carrito y los productos al abrir la ventana.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    public void initialize() {
        carrito = new Carrito();
        archivoTicket = new ArchivoTicket();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        cargarProductos();
        actualizarPantalla();
    }

    /**
     * Carga en la tabla los productos guardados en productos.dat.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void cargarProductos() {
        ArchivoProductos archivoProductos = new ArchivoProductos();

        try {
            for (Producto producto : archivoProductos.cargar()) {
                tblProductos.getItems().add(producto);
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se pudo leer productos.dat: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Clase no encontrada: " + e.getMessage());
        } catch (OperacionInvalidaException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos invalidos: " + e.getMessage());
        }
    }

    /**
     * Indica si un texto es un numero entero mayor a 0.
     *
     * @param texto cantidad ingresada
     * @return true si es valida
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private boolean esCantidadValida(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Agrega al carrito el producto seleccionado con la cantidad indicada.
     *
     * @param event clic en el boton Agregar al carrito
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    private void handleAgregar(ActionEvent event) {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Debe seleccionar un producto.");
            return;
        }
        if (!esCantidadValida(txtCantidad.getText())) {
            mostrarAlerta(Alert.AlertType.WARNING, "La cantidad debe ser un numero entero mayor a 0.");
            return;
        }

        int cantidad = Integer.parseInt(txtCantidad.getText());

        try {
            carrito.agregarProducto(seleccionado, cantidad);
            actualizarPantalla();
            txtCantidad.clear();
        } catch (OperacionInvalidaException e) {
            mostrarAlerta(Alert.AlertType.WARNING, e.getMessage());
        }
    }

    /**
     * Guarda el ticket y vacia el carrito solo si se pudo guardar.
     *
     * @param event clic en el boton Confirmar compra
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    private void handleConfirmar(ActionEvent event) {
        if (carrito.estaVacio()) {
            mostrarAlerta(Alert.AlertType.WARNING, "El carrito esta vacio.");
            return;
        }

        try {
            archivoTicket.guardar(carrito);
            carrito.vaciar();
            actualizarPantalla();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Compra confirmada. Se guardo ticket.txt.");
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se pudo guardar el ticket: " + e.getMessage());
        }
    }

    /**
     * Actualiza la tabla, la lista del carrito y el total.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void actualizarPantalla() {
        lstCarrito.getItems().clear();
        for (ItemCarrito item : carrito.getItems()) {
            lstCarrito.getItems().add(item);
        }
        lstCarrito.refresh();
        tblProductos.refresh();
        lblTotal.setText("$ " + carrito.calcularTotal());
    }

    /**
     * Muestra un mensaje al usuario.
     *
     * @param tipo tipo de alerta
     * @param mensaje texto a mostrar
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
