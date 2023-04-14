package ABB;

public class Arbol {
    Nodo root;

    public Arbol() {
        this.root = null;
    }

    public void insert(int dato) {
        if (this.root == null) {
            this.root = new Nodo(dato);
        } else {
            this.root.insert(dato);
        }
    }

    public void showPreOrder() {
        this.preOrder(this.root);
    }

    public void preOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        } else {
            System.out.print(nodo.getDato() + "->");
            preOrder(nodo.getIzq());
            preOrder(nodo.getDer());
        }
    }

    public void showInOrder() {
        this.inOrder(this.root);
    }

    public void inOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        } else {
            inOrder(nodo.getIzq());
            System.out.print(nodo.getDato() + "->");
            inOrder(nodo.getDer());
        }

    }

    public void showPostOrder() {
        this.postOrder(this.root);
    }

    public void postOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        } else {
            postOrder(nodo.getIzq());
            postOrder(nodo.getDer());
            System.out.print(nodo.getDato() + "->");
        }
    }

    public boolean buscar(int dato) {
        return buscar(this.root, dato) !=null;
    }

    public Nodo buscar(Nodo act, int dato) {
        if (act == null || act.getDato() == dato) {
            return act;
        }
        if (dato < act.getDato()) {
            return buscar(act.getIzq(), dato);
        } else {
            return buscar(act.getDer(), dato);
        }
    }

    public boolean delete(int dato){
        Nodo nodoEliminar=delete(this.root, dato);
        if(nodoEliminar!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public Nodo delete(Nodo nodo, int dato){
        if(nodo==null){
            return null;
        }
        if(dato<nodo.getDato()){
            nodo.setIzq(delete(nodo.getIzq(), dato));
        }
        else if(dato>nodo.getDato()){
            nodo.setDer(delete(nodo.getDer(),dato));
        }else{
            //No tiene hijos
            if(nodo.getIzq()==null && nodo.getDer()==null){
                return null;
            }
            //Tiene un hijo
            if(nodo.getIzq()==null){
                return nodo.getDer();
            }
            if(nodo.getDer()==null){
                return nodo.getIzq();
            }
            //Es raiz
            Nodo sucesor= searchSucesor(nodo.getDer());
            nodo.setDato(sucesor.getDato());

            nodo.setDato(sucesor.getDato());

            nodo.setDer(delete(nodo.getDer(),sucesor.getDato()));
        }
        return nodo;
    }

    public Nodo searchSucesor(Nodo nodo){
        if(nodo.getIzq()==null){
            return nodo;
        }
        return searchSucesor(nodo.getIzq());
    }
}
