package ABB;

public class Nodo {
    private int dato;
    private Nodo izq;
    private Nodo der;

    public Nodo(int dato) {
        this.dato = dato;
        this.der = null;
        this.izq = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public void insert(int dato) {
        if (dato < this.dato) {
            // Insertando en lado izquierdo
            if (this.izq == null) {
                this.izq = new Nodo(dato);
            } else {
                this.izq.insert(dato);
            }
        } else {
            // Insertar en lado derecho
            if (this.der == null) {
                this.der = new Nodo(dato);
            } else {
                this.der.insert(dato);
            }
        }
    }


}
