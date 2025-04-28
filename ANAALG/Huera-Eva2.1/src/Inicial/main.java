package Inicial;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Grupo 2 ---- Huera Jhordan---");

        // Pedir al usuario el tamaño de la matriz
        System.out.print("Ingrese el número de filas (m): ");
        int filas = sc.nextInt();
        System.out.print("Ingrese el número de columnas (n): ");
        int columnas = sc.nextInt();

        // Crear la matriz
        Matriz matriz = new Matriz(filas, columnas);

        // Imprimir la matriz generada
        System.out.println("Matriz generada:");
        matriz.imprimirMatriz();

        // Pedir al usuario la palabra que desea buscar
        System.out.print("\nIngrese la palabra que desea buscar: ");
        String palabra = sc.next();

        // Medir el tiempo de ejecución de la búsqueda de palabras
        long inicioBusqueda = System.nanoTime();

        // Buscar la palabra en la matriz y mostrar las posiciones encontradas
        ArrayList<String> posiciones = Busqueda.buscarPalabras(matriz, palabra);

        long finBusqueda = System.nanoTime();
        long tiempoBusquedaNano = finBusqueda - inicioBusqueda;

        // Convertir el tiempo de nanosegundos a segundos
        double tiempoBusquedaSegundos = tiempoBusquedaNano / 1_000_000_000.0;

        // Mostrar el tiempo de ejecución de la búsqueda en segundos
        System.out.println("\nTiempo de ejecución de la búsqueda: " + tiempoBusquedaSegundos + " segundos.");

        // Mostrar las posiciones de la palabra en la matriz
        if (posiciones.isEmpty()) {
            System.out.println("La palabra no se encontró en la matriz.");
        } else {
            System.out.println("\nLa palabra se encuentra en las siguientes posiciones:");
            for (String posicion : posiciones) {
                System.out.println(posicion);
            }
        }

        // Pedir al usuario qué columna desea ordenar
        System.out.print("\nIngrese el número de columna (0 a " + (columnas - 1) + ") que desea ordenar: ");
        int columna = sc.nextInt();

        // Validar que la columna esté dentro del rango
        if (columna >= 0 && columna < columnas) {
            // Ordenar la columna seleccionada de manera descendente usando Merge Sort
            Ordenamiento.ordenarColumnaConMergeSort(matriz, columna);
            System.out.println("\nMatriz después de ordenar la columna " + columna + ":");
            matriz.imprimirMatriz();
        } else {
            System.out.println("Columna no válida.");
        }

        System.out.println("Valor adicional: \n 1.Codigo limpio \n 2.POO");
    }
}
