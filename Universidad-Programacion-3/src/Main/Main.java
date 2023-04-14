package Main;

import java.util.Scanner;

import ABB.Arbol;
import AVL.ArbolAVL;
import AVL.ArbolAVL.*;

public class Main {
    public static void main(String[] args) {
        int opcion, menu;
        Scanner input = new Scanner(System.in);
        Arbol arbol = new Arbol();
        ArbolAVL arbolavl = new ArbolAVL();
        do {
            System.out.println("Menu de Arboles");
            System.out.println("1. Arboles de Busqueda");
            System.out.println("2. Arboles AVL");
            System.out.println("0. Salir");
            System.out.println("Ingresa una opcion: ");
            opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Bienvenido al menu de Arboles de Busqueda");
                    System.out.println("1. Ingresar datos para armar el arbol: ");
                    System.out.println("2. Ver recorridos");
                    System.out.println("3. Buscar nodo");
                    System.out.println("4. Eliminar nodo");
                    System.out.println("Opcion: ");
                    menu = input.nextInt();
                    if (menu == 1) {
                        Main.armarArbolABB(arbol);
                    } else if (menu == 2) {
                        System.out.println("Mostrando Recorridos...");
                        System.out.print("Recorrido Inorden :");
                        arbol.showInOrder();
                        System.out.println("\n");
                        System.out.print("Recorrido Post-Orden :");
                        arbol.showPostOrder();
                        System.out.println("\n");
                        System.out.print("Recorrido Pre-Orden :");
                        arbol.showPreOrder();
                        System.out.println("\n");
                    } else if (menu == 3) {
                        System.out.print("Ingrese el valor del nodo a buscar: ");
                        int valorBuscar = input.nextInt();
                        if (arbol.buscar(valorBuscar)) {
                            System.out.println("El nodo con valor " + valorBuscar + " se encuentra en el árbol.");
                        } else {
                            System.out.println("El nodo con valor " + valorBuscar + " no se encuentra en el árbol.");
                        }
                    } else if (menu == 4) {
                        Main.eliminarABB(arbol);
                    } else {
                        System.out.println("Opcion no valida!");
                    }
                    break;
                case 2:
                    System.out.println("Bienvenido al menu de Arboles AVL");
                    System.out.println("1. Ingresar datos para armar el arbol: ");
                    System.out.println("2. Ver recorridos");
                    System.out.println("3. Buscar nodo");
                    System.out.println("4. Eliminar nodo");
                    menu = input.nextInt();
                    if (menu == 1) {
                        Main.armarArbolAVL(arbolavl);
                    } else if (menu == 2) {
                        System.out.println("Mostrando Recorridos...");
                        System.out.print("Recorrido Inorden :");
                        arbolavl.inOrden(arbolavl.root);
                        System.out.println("\n");
                        System.out.print("Recorrido Post-Orden :");
                        arbolavl.postOrden(arbolavl.root);
                        System.out.println("\n");
                        System.out.print("Recorrido Pre-Orden :");
                        arbolavl.preOrden(arbolavl.root);
                        System.out.println("\n");
                    } else if (menu == 3) {
                        System.out.print("Ingrese el valor del nodo a buscar: ");
                        int valorBuscar = input.nextInt();
                        if (arbolavl.buscar(valorBuscar)) {
                            System.out.println("El nodo con valor " + valorBuscar + " se encuentra en el árbol.");
                        } else {
                            System.out.println("El nodo con valor " + valorBuscar + " no se encuentra en el árbol.");
                        }
                    } else if (menu == 4) {
                        System.out.println("Ingrese numero a eliminar");
                        int numDelete = input.nextInt();
                        arbolavl.root = arbolavl.eliminarAVL(arbolavl.root, numDelete);
                        if (arbolavl.buscar(numDelete)) {
                            System.out.println("El valor: " + numDelete + " no se ha eliminado correctamente!");
                        } else {
                            System.out.println("El valor: " + numDelete + " ha sido eliminado del arbol.");
                        }
                    } else {
                        System.out.println("Opcion no Valida!");
                    }
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion!");
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente!");
                    break;
            }
        } while (opcion != 0);
    }

    public static void armarArbolABB(Arbol arbol) {
        Scanner sc = new Scanner(System.in);
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("Ingrese un número para agregar al árbol o presione 'a' para dejar de ingresar:");
            String entrada = sc.nextLine();
            if (entrada.equalsIgnoreCase("a")) {
                seguirIngresando = false;
            } else {
                try {
                    int valor = Integer.parseInt(entrada);
                    arbol.insert(valor);
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido o 'a' para terminar.");
                }
            }
        }
    }

    public static void eliminarABB(Arbol arbol) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el numero a buscar: ");
        int numSearch = sc.nextInt();
        if (arbol.buscar(numSearch)) {
            System.out.println("El valor: " + numSearch + " se encuentra en el arbol.");
        } else {
            System.out.println("El valor: " + numSearch + " se encuentra en el arbol.");
        }

        System.out.println("Ingrese numero a eliminar");
        int numDelete = sc.nextInt();
        boolean eliminado = arbol.delete(numDelete);

        if (eliminado) {
            System.out.println("El nodo con el dato: " + numDelete + " ha sido eliminado!");
        } else {
            System.out.println("El nodo con el dato: " + numDelete + " no se ha encontrado en el arbol!");
        }
    }

    // Funciones AVL
    public static void armarArbolAVL(ArbolAVL arbolavl) {
        Scanner sc = new Scanner(System.in);
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("Ingrese un número para agregar al árbol o presione 'a' para dejar de ingresar:");
            String entrada = sc.nextLine();
            if (entrada.equalsIgnoreCase("a")) {
                seguirIngresando = false;
            } else {
                try {
                    int valor = Integer.parseInt(entrada);
                    arbolavl.insertar(valor);
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido o 'a' para terminar.");
                }
            }
        }
    }

}
