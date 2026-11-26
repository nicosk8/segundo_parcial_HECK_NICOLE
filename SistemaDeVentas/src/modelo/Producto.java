package modelo;

import java.io.Serializable;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private Double precio;
    private int stock;

    /**
     * Crea un producto validando sus datos.
     *
     * @param nombre nombre del producto
     * @param precio precio unitario, mayor a 0
     * @param stock unidades disponibles, 0 o mas
     * @throws OperacionInvalidaException si algun dato es invalido
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public Producto(String nombre, double precio, int stock) throws OperacionInvalidaException {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        validarDatos();
    }

    /**
     * Valida los datos del producto. Se usa al crearlo y al leerlo del archivo.
     *
     * @throws OperacionInvalidaException si algun dato es invalido
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void validarDatos() throws OperacionInvalidaException {
        if (esTextoVacio(nombre)) {
            throw new OperacionInvalidaException("El nombre no puede estar vacio.");
        }
        if (precio == null || !(precio > 0 && precio <= Double.MAX_VALUE)) {
            throw new OperacionInvalidaException("El precio de " + nombre + " debe ser un numero mayor a 0.");
        }
        if (stock < 0) {
            throw new OperacionInvalidaException("El stock de " + nombre + " no puede ser negativo.");
        }
    }

    /**
     * Indica si un texto es nulo, no tiene caracteres o solo tiene espacios.
     *
     * @param texto texto a revisar
     * @return true si esta vacio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private boolean esTextoVacio(String texto) {
        if (texto == null) {
            return true;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Descuenta del stock la cantidad pedida si alcanza.
     *
     * @param cantidad unidades a descontar, mayor a 0
     * @throws OperacionInvalidaException si la cantidad es invalida o supera el stock
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void descontarStock(int cantidad) throws OperacionInvalidaException {
        if (cantidad <= 0) {
            throw new OperacionInvalidaException("La cantidad debe ser mayor a 0.");
        }
        if (cantidad > stock) {
            throw new OperacionInvalidaException("Stock insuficiente de " + nombre + ". Disponible: " + stock);
        }
        stock = stock - cantidad;
    }

    /**
     * Devuelve el nombre.
     *
     * @return nombre del producto
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio.
     *
     * @return precio unitario
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve el stock.
     *
     * @return unidades disponibles
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public int getStock() {
        return stock;
    }
}
