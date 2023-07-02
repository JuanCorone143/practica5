
package controlador.exception;

/**
 *
 * @author Juan_fer
 */
public class EmptyException extends Exception {

    /**
     * Creates a new instance of <code>EmptyException</code> without detail
     * message.
     */
    public EmptyException() {
        super("La lista esta vacia");
    }

    /**
     * Constructs an instance of <code>EmptyException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EmptyException(String msg) {
        super(msg);
    }
}
