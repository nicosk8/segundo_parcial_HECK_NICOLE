package modelo;

public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    /**
     * Crea una linea del carrito.
     *
     * @param producto producto seleccionado
     * @param cantidad unidades pedidas, mayor a 0
     * @throws OperacionInvalidaException si el producto es nulo o la cantidad es invalida
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public ItemCarrito(Producto producto, int cantidad) throws OperacionInvalidaException {
        if (producto == null) {
            throw new OperacionInvalidaException("Debe indicar un producto.");
        }
        if (cantidad <= 0) {
            throw new OperacionInvalidaException("La cantidad debe ser mayor a 0.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Suma unidades a una linea existente.
     *
     * @param unidades unidades a sumar, ya validadas al descontar el stock
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void sumarCantidad(int unidades) {
        cantidad = cantidad + unidades;
    }

    /**
     * Devuelve el producto de la linea.
     *
     * @return producto
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return nombre
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public String getNombre() {
        return producto.getNombre();
    }

    /**
     * Devuelve el precio unitario.
     *
     * @return precio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getPrecio() {
        return producto.getPrecio();
    }

    /**
     * Devuelve las unidades de la linea.
     *
     * @return cantidad
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el importe de la linea.
     *
     * @return precio por cantidad
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Define el texto que muestra el ListView.
     *
     * @return nombre y cantidad
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @Override
    public String toString() {
        return getNombre() + " - Cantidad: " + cantidad;
    }
}
