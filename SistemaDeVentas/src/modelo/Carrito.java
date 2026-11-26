package modelo;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<ItemCarrito> items;

    /**
     * Crea un carrito vacio.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public Carrito() {
        items = new ArrayList<>();
    }

    /**
     * Agrega la cantidad pedida de un producto y descuenta su stock.
     *
     * @param producto producto a agregar
     * @param cantidad unidades pedidas
     * @throws OperacionInvalidaException si falta el producto, la cantidad es invalida o no hay stock
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void agregarProducto(Producto producto, int cantidad) throws OperacionInvalidaException {
        if (producto == null) {
            throw new OperacionInvalidaException("Debe seleccionar un producto.");
        }
        producto.descontarStock(cantidad);

        ItemCarrito item = buscarItem(producto);
        if (item == null) {
            items.add(new ItemCarrito(producto, cantidad));
        } else {
            item.sumarCantidad(cantidad);
        }
    }

    /**
     * Busca la linea que corresponde a un producto.
     *
     * @param producto producto buscado
     * @return la linea encontrada o null si no esta
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private ItemCarrito buscarItem(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto() == producto) {
                return item;
            }
        }
        return null;
    }

    /**
     * Suma los subtotales de todas las lineas.
     *
     * @return total del carrito
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Indica si el carrito no tiene productos.
     *
     * @return true si esta vacio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public boolean estaVacio() {
        return items.size() == 0;
    }

    /**
     * Quita todas las lineas. No repone stock porque se usa despues de la compra.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void vaciar() {
        items.clear();
    }

    /**
     * Devuelve las lineas del carrito.
     *
     * @return lista de lineas
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public ArrayList<ItemCarrito> getItems() {
        return items;
    }
}
