package Hotel_Prototype.Structures;

import javax.swing.*;

public class ListC<E> {
    Node inicio;
    Node fin;
    int size=0;

    public ListC() {
        this.inicio = null;
        this.fin = null;
    }

    public boolean estaVacia(){
        if (inicio==null)return true;
        else return false;
    }
    public int size(){
        return size;
    }

    public void add( E dato ){
        Node actual;
        if (estaVacia()){
            actual= new Node(dato,null);
            inicio = actual;
            fin = actual;
            size++;
        }
        else{
            actual= new Node(dato,null);
            fin.setNext(actual);
            fin = actual;
            fin.setNext(inicio);
            size++;
        }
    }
    public E get(int posicion) throws Exception{
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                return (E) inicio.getdato();
            }else{
                Node aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                return (E) aux.getdato();
            }
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    public int getPosition(E dato) throws Exception {
        int i = 0;
        if (size != 0) {
            if (dato == inicio.getdato()) {
                return i;
            } else {
                Node aux = inicio;
                for (i = 1; i < size(); i++) {
                    aux = aux.getNext();
                    if (aux.getdato() == dato) {
                        break;
                    }
                }

            } return i;
        } else {
            throw  new Exception("No hay nada en la lista");
        }
    }
    public String mostrar() throws Exception {
        Node aux;
        int ciclico=0;
        if(estaVacia()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");
        }else{
            aux=inicio;
            String s ="[";
            while (aux!=null){
                s=s+aux.getdato()+",";
                if(aux ==fin){
                    s=s+"]";
                    ciclico++;
                    if (ciclico==2){
                        break;
                    }
                    s=s+" [";

                }
                aux=aux.getNext();
            }
          return  s;
        }
        throw new Exception("mal manejo de la lista");
    }
    public void delete(E b) {
        if (this.inicio.getdato() == b) {
            this.inicio = inicio.getNext();
            size--;
        }

        while (true) {

            int band = 0;
            Node auxiliar = this.inicio;

            while (auxiliar.getNext() != inicio && auxiliar.getNext().getdato() != b) {
                auxiliar = auxiliar.getNext();
            }

            if (auxiliar.getNext() != inicio) {

                auxiliar.setNext(auxiliar.getNext().getNext());
                band++;
                size--;
            }
            if(band==0){
                break;
            }
        }
    }
}
