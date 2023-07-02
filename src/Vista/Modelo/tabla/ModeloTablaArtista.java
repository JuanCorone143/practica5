
package Vista.Modelo.tabla;

import Controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Artista;

/**
 *
 * @author Juan_fer
 */
public class ModeloTablaArtista extends AbstractTableModel{
    private ListaEnlazada<Artista> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Artista s = null;
        try {
            s = lista.get(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (i1) {
            case 0:
                return (s != null) ? s.getNombre() : "No definido";
            case 1:
                return (s != null) ? s.getGenero(): "No definido";
            case 2:
                return (s != null) ? s.getCancion().getAlbum(): "No definido";
            case 3:
                return (s != null) ? s.getCancion().getNombreCancion(): "No definido";
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Artista";
            case 1:
                return "Genero";
            case 2:
                return "Album";
            case 3:
                return "Cancion";
            default:
                return null;
        }
    }

    /**
     * @return the lista
     */
    public ListaEnlazada<Artista> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Artista> lista) {
        this.lista = lista;
    }

}
