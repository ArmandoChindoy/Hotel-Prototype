package Hotel_Prototype.Structures;

public class Node<E> {
    E dato;
    Node Next;

    public Node(E dato, Node node) {
        this.dato = dato;
        Next = node;
    }

    public E getdato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public Node getNext() {
        return Next;
    }

    public void setNext(Node next) {
        Next = next;
    }

}
