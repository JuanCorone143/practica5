package Controlador.DAO;

import Controlador.ed.lista.ListaEnlazada;
import java.io.IOException;
import modelo.Artista;

/**
 *
 * @author Juan_fer
 */
public class ArtistaDAO extends AdaptadorDAO<Artista> {

    private Artista artista;

    public ArtistaDAO() {
        super(Artista.class);
    }

    public Artista getArtista() {
        if (this.artista == null) {
            this.artista = new Artista();
        }
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void guardar() throws IOException {
        artista.setId(generateID()); // Genera un ID único para el objeto artista
        this.guardar(artista); // Guarda el objeto artista en el archivo de datos
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(artista, pos); // Modifica el objeto artista en la posición pos del archivo de datos
    }

    private Integer generateID() {
        return listar().size() + 1; // Genera un ID único incrementando el tamaño actual de la lista de artistas
    }

    public ListaEnlazada<Artista> ordenarPorNombre(ListaEnlazada<Artista> artistas) throws Exception {
        int n = artistas.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (artistas.get(j).getNombre().compareToIgnoreCase(artistas.get(minIndex).getNombre()) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Artista temp = artistas.get(i);
                artistas.modificar(artistas.get(minIndex), i);
                artistas.modificar(temp, minIndex);
            }
        }

        return artistas; // Devuelve la lista de artistas ordenada por nombre
    }

    public ListaEnlazada<Artista> ordenarPorGenero(ListaEnlazada<Artista> artistas) throws Exception {
        int n = artistas.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (artistas.get(j).getGenero().compareToIgnoreCase(artistas.get(minIndex).getGenero()) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Artista temp = artistas.get(i);
                artistas.modificar(artistas.get(minIndex), i);
                artistas.modificar(temp, minIndex);
            }
        }

        return artistas; // Devuelve la lista de artistas ordenada por género
    }

    public ListaEnlazada<Artista> ordenarPorCancion(ListaEnlazada<Artista> artistas) throws Exception {
        int n = artistas.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                String cancionActual = artistas.get(j).getCancion().getNombreCancion();
                String cancionMin = artistas.get(minIndex).getCancion().getNombreCancion();

                if (cancionActual.compareToIgnoreCase(cancionMin) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Artista temp = artistas.get(i);
                artistas.modificar(artistas.get(minIndex), i);
                artistas.modificar(temp, minIndex);
            }
        }

        return artistas; // Devuelve la lista de artistas ordenada por canción
    }

    public ListaEnlazada<Artista> buscarArtistasBinario(ListaEnlazada<Artista> aux, String clave, String tipoBusqueda) throws Exception {
        ListaEnlazada<Artista> artistas = obtenerListaOrdenada(aux, tipoBusqueda);
        ListaEnlazada<Artista> artistasEncontrados = new ListaEnlazada<>();

        int izquierda = 0;
        int derecha = artistas.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Artista artistaActual = artistas.get(medio);
            String claveArtista = getClaveArtista(artistaActual, tipoBusqueda);

            if (claveArtista.toLowerCase().contains(clave.toLowerCase())) {
                artistasEncontrados.insertarNodo(artistaActual);
                break;
            }

            if (claveArtista.toLowerCase().compareTo(clave.toLowerCase()) >= 0) {
                derecha = medio - 1;
            } else {
                izquierda = medio + 1;
            }
        }

        return artistasEncontrados; // Devuelve la lista de artistas encontrados según la búsqueda binaria
    }

    private ListaEnlazada<Artista> obtenerListaOrdenada(ListaEnlazada<Artista> aux, String tipoBusqueda) throws Exception {
        switch (tipoBusqueda.toLowerCase()) {
            case "genero":
                return ordenarPorGenero(aux);
            case "nombre":
                return ordenarPorNombre(aux);
            case "cancion":
                return ordenarPorCancion(aux);
            default:
                throw new IllegalArgumentException("Búsqueda inválida: " + tipoBusqueda);
        }
    }

    public ListaEnlazada<Artista> buscarArtistaLinealBinaria(ListaEnlazada<Artista> aux, String clave, String tipoBusqueda) throws Exception {
        ListaEnlazada<Artista> artistas = null;

        switch (tipoBusqueda.toLowerCase()) {
            case "genero":
                artistas = ordenarPorGenero(aux);
                break;
            case "nombre":
                artistas = ordenarPorNombre(aux);
                break;
            case "cancion":
                artistas = ordenarPorCancion(aux);
                break;
            default:
                throw new IllegalArgumentException("Búsqueda inválida: " + tipoBusqueda);
        }

        ListaEnlazada<Artista> artistasEncontrados = new ListaEnlazada<>();

        for (int i = 0; i < artistas.size(); i++) {
            Artista artista = artistas.get(i);
            String claveArtista = getClaveArtista(artista, tipoBusqueda);

            if (claveArtista.toLowerCase().contains(clave.toLowerCase())) {
                artistasEncontrados.insertarNodo(artista);
            }
        }

        return artistasEncontrados;
    }

    private String getClaveArtista(Artista artista, String tipoBusqueda) {
        switch (tipoBusqueda.toLowerCase()) {
            case "genero":
                return artista.getGenero();
            case "nombre":
                return artista.getNombre();
            case "cancion":
                return artista.getCancion().getNombreCancion();
            default:
                return "";
        }
    }

}
