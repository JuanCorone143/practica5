
package Controlador.DAO;

import Controlador.DAO.Conexion.Conexion;
import Controlador.ed.lista.ListaEnlazada;
import controlador.exception.PositionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 *
 * @author Juan_fer
 */
public class AdaptadorDAO<T> {

    private Conexion conexion;
    private Class clazz;
    private String url;
    public static Integer Ascendente = 0;
    public static Integer Descendente = 1;

    public AdaptadorDAO(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
        this.url = Conexion.URL + clazz.getSimpleName().toLowerCase() + ".json";
    }

    /**
     * Guarda un objeto en la fuente de datos.
     *
     * @param obj El objeto a guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public void guardar(T obj) throws IOException {
        // Obtener la lista existente de objetos desde la fuente de datos
        ListaEnlazada<T> lista = listar();

        // Insertar el nuevo objeto en la lista
        lista.insertarNodo(obj);

        // Configurar el alias de la lista en el objeto de conexión
        conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);

        // Convertir la lista a XML y escribirlo en el archivo de la URL especificada
        conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    /**
     * Modifica un objeto en la fuente de datos.
     *
     * @param obj El objeto modificado.
     * @param pos La posición del objeto en la lista.
     */
    public void modificar(T obj, Integer pos) {
        // Obtener la lista existente de objetos desde la fuente de datos
        ListaEnlazada<T> lista = listar();

        try {
            // Modificar el objeto en la posición especificada en la lista
            lista.modificar(obj, pos);

            // Configurar el alias de la lista en el objeto de conexión
            conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);

            // Convertir la lista a XML y escribirlo en el archivo de la URL especificada
            conexion.getXstream().toXML(lista, new FileWriter(url));
        } catch (PositionException | IOException ex) {
            // Manejar las excepciones y registrar un mensaje de error
            Logger.getLogger(ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de objetos desde la fuente de datos.
     *
     * @return La lista de objetos.
     */
    public ListaEnlazada listar() {
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        try {
            // Leer la lista desde el archivo XML en la URL especificada
            lista = (ListaEnlazada<T>) conexion.getXstream().fromXML(new File(url));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    /**
     * Obtiene un objeto por su identificador desde la fuente de datos.
     *
     * @param id El identificador del objeto.
     * @return El objeto correspondiente al identificador, o null si no se encuentra.
     */
    public T obtener(Integer id) {
        T obj = null;
        ListaEnlazada<T> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            try {
                T dato = lista.get(i);
                // Comprobar si el identificador coincide con el valor del campo 'id' del objeto
                if (id.intValue() == ((Integer) getValueField(lista.get(i))).intValue()) {
                    obj = dato;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error en metodo " + e);
            }
        }
        return obj;
    }   

    /**
     * Obtiene el valor del campo 'id' de un objeto utilizando reflexión.
     *
     * @param dato El objeto del cual se quiere obtener el valor del campo 'id'.
     * @return El valor del campo 'id' del objeto.
     * @throws Exception Si ocurre un error al obtener el valor del campo 'id'.
     */
    private Object getValueField(T dato) throws Exception {
        Method metodo = null;
        for (Method aux : this.clazz.getDeclaredMethods()) {
            if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                metodo = aux;
            }
        }

        if (metodo == null) {
            for (Method aux : this.clazz.getSuperclass().getDeclaredMethods()) {
                if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                    metodo = aux;
                }
            }
        }

        // Invocar el método 'getId' en el objeto para obtener el valor del campo 'id'
        return metodo.invoke(dato);
    }

    /**
     * Genera un nuevo identificador para un objeto.
     *
     * @return El nuevo identificador generado.
     */
    public Integer generarId() {
        // Obtener el tamaño de la lista y sumar 1 para obtener el nuevo identificador
        return listar().size() + 1;
    }
}


