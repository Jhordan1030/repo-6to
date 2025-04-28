package Principal;

import java.util.Scanner;

public class Inicial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de filas: ");
        int filas = scanner.nextInt();

        System.out.print("Ingrese el número de columnas: ");
        int columnas = scanner.nextInt();

        MatrizNumeros matriz = new MatrizNumeros(filas, columnas);
        matriz.generarNumerosAleatorios(1, 100); // Generar números entre 1 y 100

        System.out.println("\nMatriz generada:");
        matriz.imprimirMatriz();

        long inicioTiempo = System.nanoTime();
        matriz.buscarNumerosPrimos();
        long finTiempo = System.nanoTime();

        // Convertir de nanosegundos a segundos
        double tiempoSegundos = (finTiempo - inicioTiempo) / 1_000_000_000.0;
        System.out.printf("\nTiempo estimado para encontrar primos: %.4f segundos\n", tiempoSegundos);

        scanner.close();
    }
}
