package Hotel_Prototype.Structures;

import javax.swing.*;

public class Queue<E> {
    Node inicio;
    Node fin;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean empty() {
        if (fin == null) return true;
        else return false;
    }

    public void enqueue(E dato) {
        Node actual;
        if (fin == null) {
            actual = new Node(dato, null);
            fin = actual;
            inicio = actual;
            size++;
        } else {
            actual = new Node(dato, null);
            actual.setNext(inicio);
            inicio = actual;
            size++;
        }
    }

    public void dequeue() throws Exception{
        if(size>1) {
            reverseElements();
            inicio = inicio.getNext();
            size--;
            if(size>1) {
                reverseElements();
            }else{}
        }else {
            inicio = inicio.getNext();
            size--;
        }
    }
    public E getdequeue() throws Exception{
        E elemento;
        if(size>=1) {
            reverseElements();
            elemento = (E) inicio.getdato();
            inicio = inicio.getNext();
            size--;
            if(size>1) {
                reverseElements();
            }
        }else {
            elemento = (E) inicio.getdato();
            inicio = inicio.getNext();
            fin = null;
            size--;
        }
        return  elemento;
    }

    public E get(int posicion) throws Exception {
        if (posicion >= 0 && posicion < size) {
            if (posicion == 0) {
                return (E) inicio.getdato();
            } else {
                Node aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                return (E) aux.getdato();
            }
        } else {
            throw new Exception("Posicion inexistente en la Cola.");
        }
    }

    public void dequeueByIndex( int number) throws Exception {
        for (int i = 0; i <number; i++) {
            dequeue();
        }
    }
    public String show()throws Exception{
        Node aux;
        if(empty()){
            JOptionPane.showMessageDialog(null,"La cola esta vacia");
        }else{
            String s = "";
            aux=inicio;
            while (aux!=null){
                s = s + aux.getdato();
                aux=aux.getNext();
            }
            return s;
        }
        throw  new Exception("mal manejo de la lista");
    }
    public void setQueue(E dato, int posicion) throws Exception {
        int actual = 0;
        if (size != 0) {
            if (posicion > size) {
                JOptionPane.showMessageDialog(null,"No exsite esa posicion en la cola");
            } else {
                Node aux = inicio;
                if (posicion==actual){
                    Node auxx= new Node(dato,aux);
                    inicio=auxx;
                    size++;
                }else {
                    while (aux.getNext() != null) {
                        actual++;
                        if (actual == posicion) {
                            Node auxx= new Node(dato,aux.getNext());
                            aux.setNext(auxx);
                            size++;
                        }
                        if (aux.getNext()==null) {
                            break;
                        }
                        aux = aux.getNext();
                    }
                }
            }
        } else {
            throw  new Exception("No hay nada en la cola");
        }
    }
    public void set(int posicion , E valor){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el data de elementos del la lista.
        if(posicion>=0 && posicion<size){
            // Consulta si el nodo a eliminar es el primero.
            if(posicion == 0){
                // Alctualiza el valor delprimer nodo.
                inicio.setDato(valor);
            }
            else{
                // En caso que el nodo a eliminar este por el medio
                // o sea el ultimo
                Node aux = inicio;
                // Recorre la lista hasta lleger al nodo before al eliminar.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                // Alctualiza el valor del nodo.
                aux.setDato(valor);
            }
        }
    }
    public void reverseElements()throws Exception {
        if (size>1) {
            Node auxinicio = inicio;
            fin = new Node(inicio.getdato(),null);
            inicio = new Node(auxinicio.getdato(),null);
            while (auxinicio != null) {
                Node aux;
                auxinicio = auxinicio.getNext();
                aux=new Node(auxinicio.dato,inicio);
                inicio=aux;
                if (auxinicio.getNext()== null) {
                    break;
                }
            }
        } else {
            throw new Exception("No hay nada en la lista");
        }
    }
    /*public void organizar() throws Exception {

        while(true){
            int band=0;
            Node auxiliar = this.inicio;
            Node auxiliar2 = this.inicio.getNext();

            while (auxiliar2.getNext() != null) {

                if ( (E)auxiliar.getdato() > (E)(auxiliar2.getdato())) {
                    int aux = auxiliar.getdato();
                    auxiliar.setDato(auxiliar2.getdato());
                    auxiliar2.setDato(aux);
                    band++;
                }
                auxiliar = auxiliar2;
                auxiliar2 = auxiliar.getNext();
                if (auxiliar2 != null && auxiliar2.getNext() == null) {
                    if (auxiliar.getdato() > auxiliar2.getdato()) {
                        int aux = auxiliar.getdato();
                        auxiliar.setDato(auxiliar2.getdato());
                        auxiliar2.setDato(aux);
                        band++;
                    }
                }
            }
            if(band==0){
                break;
            }
        }

    }*/
}
