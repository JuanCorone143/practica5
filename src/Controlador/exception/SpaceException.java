
package controlador.exception;

/**
 *
 * @author Juan_fer
 */
public class SpaceException extends Exception{

    public SpaceException() {
        super("Espacio Lleno o posicion no valida");
    }

    public SpaceException(String message) {
        super(message);
    }
    
}
