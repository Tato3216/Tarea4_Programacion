package AVL;

public class ArbolAVL {
    public NodoAVL root;

    public ArbolAVL() {
        root = null;
    }

    public NodoAVL obtenerRaiz() {
        return root;
    }

    // Metodo Buscar
    public boolean buscar(NodoAVL nodo, int dato) {
        if (nodo == null) {
            return false;
        }
        if (dato == nodo.dato) {
            return true;
        } else if (dato < nodo.dato) {
            return buscar(nodo.izq, dato);
        } else {
            return buscar(nodo.der, dato);
        }
    }

    // Metodo Factor de Equilibrio
    public int obtenerFE(NodoAVL x) {
        if (x == null) {
            return -1;
        } else {
            return x.fEquilibrio;
        }
    }

    // Rotacion Simple Izquierda
    public NodoAVL rotarIzquierda(NodoAVL z) {
        NodoAVL temp = z.izq;
        z.izq = temp.der;
        temp.der = z;
        z.fEquilibrio = Math.max(obtenerFE(z.izq), obtenerFE(z.der)) + 1;
        temp.fEquilibrio = Math.max(obtenerFE(temp.izq), obtenerFE(temp.der)) + 1;
        return temp;
    }

    // Rotacion Simple Derecha
    public NodoAVL rotarDerecha(NodoAVL z) {
        NodoAVL temp = z.der;
        z.der = temp.izq;
        temp.izq = z;
        z.fEquilibrio = Math.max(obtenerFE(z.izq), obtenerFE(z.der)) + 1;
        temp.fEquilibrio = Math.max(obtenerFE(temp.izq), obtenerFE(temp.der)) + 1;
        return temp;
    }

    // Rotacion Doble Derecha
    public NodoAVL rotarDobleIzquierda(NodoAVL z) {
        NodoAVL temp;
        z.izq = rotarDerecha(z.izq);
        temp = rotarIzquierda(z);
        return temp;
    }

    // Rotacion Doble Izquierda
    public NodoAVL rotarDobleDerecha(NodoAVL z) {
        NodoAVL temp;
        z.der = rotarIzquierda(z.der);
        temp = rotarDerecha(z);
        return temp;
    }

    // Metodo Insertar Balanceadamente
    public NodoAVL insertarAVL(NodoAVL nuevo, NodoAVL subAr) {
        NodoAVL nuevoPadre = subAr;
        if (nuevo.dato < subAr.dato) {
            if (subAr.izq == null) {
                subAr.izq = nuevo;
            } else {
                subAr.izq = insertarAVL(nuevo, subAr.izq);
                if ((obtenerFE(subAr.izq) - obtenerFE(subAr.der) == 2)) {
                    if (nuevo.dato < subAr.izq.dato) {
                        nuevoPadre = rotarIzquierda(subAr);
                    } else {
                        nuevoPadre = rotarDobleIzquierda(subAr);
                    }
                }
            }
        } else if (nuevo.dato > subAr.dato) {
            if (subAr.der == null) {
                subAr.der = nuevo;
            } else {
                subAr.der = insertarAVL(nuevo, subAr.der);
                if ((obtenerFE(subAr.der) - obtenerFE(subAr.izq) == 2)) {
                    if (nuevo.dato > subAr.der.dato) {
                        nuevoPadre = rotarDerecha(subAr);
                    } else {
                        nuevoPadre = rotarDobleDerecha(subAr);
                    }
                }
            }
        } else {
            System.out.println("Nodo Duplicado!");
        }
        // Actualizando FE
        if ((subAr.izq == null) && (subAr.der != null)) {
            subAr.fEquilibrio = subAr.der.fEquilibrio + 1;
        } else if ((subAr.der == null) && (subAr.izq != null)) {
            subAr.fEquilibrio = subAr.izq.fEquilibrio + 1;
        } else {
            subAr.fEquilibrio = Math.max(obtenerFE(subAr.izq), obtenerFE(subAr.der)) + 1;
        }
        return nuevoPadre;
    }

    // Metodo Insertar Desbalanceado
    public void insertar(int dato) {
        NodoAVL nuevo = new NodoAVL(dato);
        if (root == null) {
            root = nuevo;
        } else {
            root = insertarAVL(nuevo, root);
        }
    }

    // Recorrido Inorden
    public void inOrden(NodoAVL a) {
        if (a != null) {
            inOrden(a.izq);
            System.out.print(a.dato + "->");
            inOrden(a.der);
        }
    }

    // Recorrido Preorden
    public void preOrden(NodoAVL a) {
        if (a != null) {
            System.out.print(a.dato + "->");
            preOrden(a.izq);
            preOrden(a.der);
        }
    }

    // Recorrido Postorden
    public void postOrden(NodoAVL a) {
        if (a != null) {
            postOrden(a.izq);
            postOrden(a.der);
            System.out.print(a.dato + "->");
        }
    }

    public boolean buscar(int dato) {
        return buscar(root, dato);
    }

    public NodoAVL eliminarAVL(NodoAVL nodo, int dato) {
        if (nodo == null) {
            return nodo;
        }
        if (dato < nodo.dato) {
            nodo.izq = eliminarAVL(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = eliminarAVL(nodo.der, dato);
        } else {
            // Encontró el nodo a eliminar
            if ((nodo.izq == null) || (nodo.der == null)) {
                NodoAVL temp;
                if (nodo.izq == null) {
                    temp = nodo.der;
                } else {
                    temp = nodo.izq;
                }
                // Caso de un hijo o ningún hijo
                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                // Caso de dos hijos
                NodoAVL temp = encontrarMinimo(nodo.der);
                nodo.dato = temp.dato;
                nodo.der = eliminarAVL(nodo.der, temp.dato);
            }
        }
        // Si el árbol solo tiene un nodo, regresa ese nodo
        if (nodo == null) {
            return nodo;
        }
        // Actualiza el factor de equilibrio
        nodo.fEquilibrio = Math.max(obtenerFE(nodo.izq), obtenerFE(nodo.der)) + 1;
        // Revisa si el árbol está balanceado
        int fe = obtenerFE(nodo);
        if (fe > 1 && obtenerFE(nodo.izq) >= 0) {
            return rotarDerecha(nodo);
        }
        if (fe > 1 && obtenerFE(nodo.izq) < 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }
        if (fe < -1 && obtenerFE(nodo.der) <= 0) {
            return rotarIzquierda(nodo);
        }
        if (fe < -1 && obtenerFE(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    public NodoAVL encontrarMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

}
