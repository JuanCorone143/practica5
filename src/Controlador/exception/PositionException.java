
package controlador.exception;

/**
 *
 * @author Juan_fer
 */
public class PositionException extends Exception {

    /**
     * Creates a new instance of <code>PositionException</code> without detail
     * message.
     */
    public PositionException() {
        super("No existe la posicion en la lista");
    }

    /**
     * Constructs an instance of <code>PositionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PositionException(String msg) {
        super(msg);
    }
}
