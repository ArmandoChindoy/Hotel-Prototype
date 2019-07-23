package Hotel_Prototype.Structures;

import javax.swing.*;

public class Stack <E>{
    Node inicio;
    Node fin;
    int size=0;

    public int size(){
        return size;
    }

    public boolean empty(){
        if (fin==null)return true;
        else return false;
    }

    public void push(E dato){
        Node actual;
        if(fin==null){
            actual=new Node(dato,null);
            fin=actual;
            inicio=actual;
            size++;
        }else{
            actual= new Node (dato,null);
            actual.setNext(inicio);
            inicio = actual;
            size++;
        }
    }
    public void pop(){
        Node aux=inicio.getNext();
        inicio=aux;
        size--;
    }
    public String show()throws Exception{
        Node aux;
        if(empty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");
        }else{
            aux=inicio;
            String s ="[";
            while (aux!=null){
                if(aux.getNext() == null){
                    s=s+aux.getdato()+"]";
                }
                else {
                    s = s + aux.getdato() + ",";
                }
                aux = aux.getNext();
            }
            return s;
        }
        throw  new Exception("No hay nada en la lista");
    }
    public E peek() throws Exception{
        if(!empty()){
            return (E)inicio.getdato();
        } else {
            throw new Exception("No hay nada en la pila.");
        }
    }
    public void setPop(E dato) throws Exception {
        if (size != 0) {
            if (dato == inicio.getdato()) {
                inicio=inicio.getNext();
                size--;
            } else {
                Node aux = inicio;
                int salir=0;
                while (aux.getNext()!=null) {
                    if (aux.getNext().getdato() == dato) {
                        aux.setNext(aux.getNext().getNext());
                        size--;
                        salir++;
                    }
                    if (salir!=0){
                        break;
                    }
                    aux = aux.getNext();
                }
            }
        } else {
            throw  new Exception("No hay nada en la lista");
        }
    }

    public void setPush(E dato, int posicion) throws Exception {
        int actual = 0;
        if (size != 0) {
            if (posicion > size) {
                JOptionPane.showMessageDialog(null,"No exsite esa posicion en la lista");
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
            throw  new Exception("No hay nada en la lista");
        }
    }
    public void reverseElements()throws Exception {
        if (size>0) {
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

}