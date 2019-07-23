package Hotel_Prototype.Structures;

public class NodoDoble<E> {

    E data;
    NodoDoble next;
    NodoDoble before;

    public NodoDoble( E dato) {

        next = null;
        before = null;
        data = dato;
    }

    public NodoDoble(E dato, NodoDoble s, NodoDoble a) {
        data = dato;
        next = s;
        before = a;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodoDoble getNext() {
        return next;
    }

    public void setNext(NodoDoble next) {
        this.next = next;
    }

    public NodoDoble getBefore() {
        return before;
    }

    public void setBefore(NodoDoble before) {
        this.before = before;
    }

    @Override
    public String toString() {
        return
                " data = " + data +
                " next = " + next +
                " before = " + before;
    }
}
