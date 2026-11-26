package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoTicket {

    /**
     * Guarda el detalle y el total en ticket.txt. Reemplaza el ticket anterior.
     *
     * @param carrito carrito con la compra
     * @throws IOException si no se puede escribir el archivo
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void guardar(Carrito carrito) throws IOException {
        File archivo = new File("ticket.txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write("TICKET DE COMPRA");
            escritor.newLine();

            for (ItemCarrito item : carrito.getItems()) {
                escritor.write(item.getNombre() + " | Cantidad: " + item.getCantidad()
                        + " | Precio unitario: $ " + item.getPrecio()
                        + " | Subtotal: $ " + item.getSubtotal());
                escritor.newLine();
            }

            escritor.write("TOTAL: $ " + carrito.calcularTotal());
            escritor.newLine();
        }
    }
}
