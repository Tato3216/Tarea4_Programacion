package AVL;

public class NodoAVL {
    int dato,fEquilibrio;
    NodoAVL izq;
    NodoAVL der;

    public NodoAVL(int dato){
        this.dato=dato;
        this.fEquilibrio=0;
        this.izq=null;
        this.der=null;
    }
}
