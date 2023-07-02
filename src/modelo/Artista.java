
package modelo;

/**
 *
 * @author Juan_fer
 */
public class Artista {
    private int id;
    private String nombre;
    private String genero;
    private Cancion cancion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
    
    @Override
    public String toString() {
        return "Artista{"+"Nombre:"+ nombre +"Genero" + genero + '}';
    }
}
