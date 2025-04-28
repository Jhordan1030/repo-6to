package Principal;

import java.util.Scanner;

public class Inicial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parte 1: Arreglo de números aleatorios
        System.out.print("Ingresa el tamaño del arreglo: ");
        int tamañoArreglo = scanner.nextInt();
        ArregloNumeros arreglo = new ArregloNumeros(tamañoArreglo);

        arreglo.generarNumerosAleatorios(0, 99);
        arreglo.mostrarArreglo();
        int cantidadPrimos = arreglo.contarPrimos();
        System.out.println("\nCantidad de números primos en el arreglo: " + cantidadPrimos);

        // Parte 2: Matriz de números aleatorios
        System.out.print("\nIngresa el tamaño de la matriz (n): ");
        int tamañoMatriz = scanner.nextInt();
        MatrizNumeros matriz = new MatrizNumeros(tamañoMatriz);

        matriz.generarNumerosAleatorios(0, 99);
        matriz.mostrarMatriz();
        matriz.sumarFilasYColumnas();

        scanner.close();;
    }
    
}
