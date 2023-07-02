
package Controlador.ed.lista;

import controlador.exception.EmptyException;
import controlador.exception.PositionException;

/**
 *
 * @author Juan_fer
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size = 0;

    /**
     * @return the cabecera;
     */
    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera the cabecera to set
     */
    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * Método que devuelve un true si la lista enlazada está vacia y un false si
     * la lista contiene datos
     */
    public boolean isEmpty() {
        return cabecera == null;
    }

    /**
     * Método que devuelve el tamaño de la lista contada desde 0
     *
     * @return
     */
    public Integer size() {
        return size;
    }

    /**
     * Método que me permite insertar un nuevo nodo al final de la lista
     *
     * @param info objeto de la clase NodoLista
     */
    public void insertarNodo(E info) {
        NodoLista<E> nuevo = new NodoLista<>(info, null);
        if (isEmpty()) {
            this.cabecera = nuevo;
            this.size++;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSig() != null) {
                aux = aux.getSig();
            }
            aux.setSig(nuevo);
            this.size++;
        }
    }

    /**
     * Método que inserta un nuevo nodo en la cabecera
     *
     * @param info objeto de la clase NodoLista
     */
    public void insertarInicio(E info) {
        if (isEmpty()) {
            insertarNodo(info);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            nuevo.setSig(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    /**
     * Método que permite ingresar un nuevo desde una posición específica
     *
     * @param info objeto de la clase NodoLista
     * @param pos número entero que me indica la posición donde deseo ingresar
     * el nodo
     * @throws PositionException
     */
    public void insertarPosicion(E info, Integer pos) throws PositionException {
        if (isEmpty()) {
            insertarInicio(info);
        } else if (pos == 0) {
            insertarInicio(info);
        } else if (pos > 0 && pos < size()) {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < (pos - 1); i++) {
                aux = aux.getSig();

            }
            NodoLista<E> sig = aux.getSig();
            aux.setSig(nuevo);
            nuevo.setSig(sig);
            size++;
        } else {
            throw new PositionException();
        }
    }

    /**
     * método para eliminar un nodo de una posición arbitraria
     *
     * @param pos número entero que me indica la posición del nodo que deseo
     * eliminar
     * @throws PositionException
     * @throws EmptyException retorna la información borrada
     */
    public E delete(Integer pos) throws PositionException, EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                    cabecera = cabecera.getSig();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < (pos - 1); i++) {
                        aux = aux.getSig();
                    }
                    NodoLista<E> nodoEliminar = aux.getSig();
                    dato = nodoEliminar.getInfo();
                    aux.setSig(nodoEliminar.getSig());
                    size--;
                }
            } else {
                throw new PositionException();
            }
            return dato;
        }
    }

    /**
     * método que obtiene la información del nodo especificando el índice
     *
     * @param pos posición del nodo del cual se desea obtener el dato
     * @return la información del nodo
     * @throws EmptyException
     * @throws PositionException
     */
    public E get(Integer pos) throws EmptyException, PositionException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < (pos); i++) {
                        aux = aux.getSig();
                    }
                    dato = aux.getInfo();
                }
            } else {
                throw new PositionException();
            }
            return dato;
        }
    }

    /**
     * método que modifica un elemento de la lista
     *
     * @param info
     * @param pos
     * @throws PositionException
     */
    public void modificar(E info, Integer pos) throws PositionException {
        NodoLista<E> aux = cabecera;
        int indice = 0;

        while (aux != null) {
            if (indice == pos) {
                aux.setInfo(info);
                break;
            }
            aux = aux.getSig();
            indice++;
        }
    }

    /**
     * método para borrar toda la lista
     */
    public void deleteAll() {
        this.cabecera = null;
        this.size = 0;
    }

    /**
     * método para imprimir la información de la lista
     *
     * @throws EmptyException
     */
    public void imprimir() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            NodoLista<E> aux = cabecera;
            System.out.println("-----LISTA-----");
            for (int i = 0; i < size(); i++) {
                System.out.println(aux.getInfo() + "   ");
                aux = aux.getSig();
            }
            System.out.println("-----LISTA FIN-----");
        }
    }

    //transformar lista al arreglo
    public E[] toArray() {
        Class<E> clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            matriz = (E[]) java.lang.reflect.Array.newInstance(cabecera.getInfo().getClass(), this.size);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getSig();
            }
        }
        return matriz;
    }

    public ListaEnlazada<E> toList(E[] matriz) {
        this.deleteAll();
        for (int i = 0; i < matriz.length; i++) {
            this.insertarNodo(matriz[i]);
        }
        return this;
    }
}
