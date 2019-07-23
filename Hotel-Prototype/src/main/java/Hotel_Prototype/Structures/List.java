package Hotel_Prototype.Structures;

import javax.swing.*;

public class List<E> {
    Node inicio;
    Node fin;
    int size=0;

    public List() {
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
    public void  removerLista() {
        inicio = null;
        fin = null;
    }
    public String mostrar()throws Exception{
        Node aux;
        if(estaVacia()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");
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
    public void removerNumero(E b) {

        if (this.inicio.getdato() == b) {
            this.inicio = inicio.getNext();
        }

        while (true) {

            int band = 0;
            Node auxiliar = this.inicio;

            while (auxiliar.getNext() != null && auxiliar.getNext().getdato() != b) {
                auxiliar = auxiliar.getNext();
            }

            if (auxiliar.getNext() != null) {

                auxiliar.setNext(auxiliar.getNext().getNext());
                band++;
            }

            if(band==0){
                break;
            }
        }
    }

}
