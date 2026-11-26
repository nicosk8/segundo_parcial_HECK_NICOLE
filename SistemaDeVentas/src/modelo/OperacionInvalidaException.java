package modelo;

public class OperacionInvalidaException extends Exception {

    /**
     * Crea una excepcion con el motivo por el que no se puede operar.
     *
     * @param mensaje explicacion del error
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
