package Hotel_Prototype.Structures;

public class ListaD<E> {

    public NodoDoble inicio, fin;
    int size=0;

    public ListaD() {
        inicio = null;
        fin = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(E numero) {

        NodoDoble nuevo = new NodoDoble(numero);
        nuevo.data = numero;

        if (inicio == null) {
            inicio = nuevo;
            inicio.next = null;
            inicio.before = null;
            fin = inicio;
            size++;
        } else {
            fin.next = nuevo;
            nuevo.before = fin;
            nuevo.next = null;
            fin = nuevo;
            size++;
        }
    }

    public void seeLeft() {
        NodoDoble actual = fin;

        while (actual != null) {
            System.out.print("[" + actual.data + "] ");
            actual = actual.before;
        }
    }

    public void seeRight() {
        
        NodoDoble actual = inicio;
        while (actual != null) {
            System.out.print("[" + actual.data + "] ");
            actual = actual.next;
        }
    }

    public boolean itsEmpty() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }
    public E get(int posicion) throws Exception{
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                return (E) inicio.getData();
            }else{
                NodoDoble aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                return (E) aux.getData();
            }
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }

    public void addToFinal(E n) {
        if (!itsEmpty()) {
            fin = new NodoDoble(n, null, fin);
            fin.before.next = fin;
            size++;
        } else {
            inicio = fin = new NodoDoble(n);
            size++;
        }
    }

    public void addToBegining(E n) {
        if (!itsEmpty()) {
            inicio = new NodoDoble(n, inicio, null);
            inicio.next.before = inicio;
            size++;
        } else {
            inicio = fin = new NodoDoble(n);
            size++;
        }
    }

    //metodo para mostrar la lista inicio a fin
    public void MostrarInicioFIn() {
        if (!itsEmpty()) {
            String datos1 = " ";
            NodoDoble aux = inicio;
            while (aux != null) {
                datos1 = datos1 + " [" + aux.data + "] " + "<->";
                aux = aux.next;
            }
            System.out.print(datos1);
        }
    }

    public void MostrarFinInicio() {
        if (!itsEmpty()) {
            String datos1 = " <-> ";
            NodoDoble aux = fin;
            while (aux != null) {
                datos1 = datos1 + " [" + aux.data + "] " + "<->";
                aux = aux.before;
            }
            System.out.println(datos1);
        }
    }
}
