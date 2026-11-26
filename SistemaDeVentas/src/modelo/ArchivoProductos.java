package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ArchivoProductos {

    /**
     * Deserializa la lista de productos de productos.dat y valida cada uno.
     *
     * @return lista de productos
     * @throws IOException si el archivo no existe o no se puede leer
     * @throws ClassNotFoundException si no se encuentra una clase guardada
     * @throws OperacionInvalidaException si el contenido es invalido
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public ArrayList<Producto> cargar()
            throws IOException, ClassNotFoundException, OperacionInvalidaException {
        File archivo = new File("productos.dat");

        if (!archivo.exists()) {
            throw new IOException("No existe el archivo " + archivo.getAbsolutePath());
        }

        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))) {
            Object contenido = lector.readObject();

            if (!(contenido instanceof ArrayList)) {
                throw new OperacionInvalidaException("El archivo no contiene una lista.");
            }

            ArrayList<?> lista = (ArrayList<?>) contenido;
            ArrayList<Producto> productos = new ArrayList<>();

            for (Object elemento : lista) {
                if (!(elemento instanceof Producto)) {
                    throw new OperacionInvalidaException("La lista tiene un elemento que no es un producto.");
                }
                Producto producto = (Producto) elemento;
                producto.validarDatos();
                productos.add(producto);
            }

            return productos;
        }
    }
}
