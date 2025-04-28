import java.util.Scanner;

public class Main {

    // Método para verificar si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int divisor = 2; divisor * divisor <= numero; divisor++) {
            if (numero % divisor == 0) return false;
        }
        return true;
    }

    // Método para generar una lista de números primos
    public static int[] generarPrimos(int ordenMatriz) {
        int[] primos = new int[ordenMatriz * ordenMatriz];
        int contadorPrimos = 0;
        int numeroActual = 2;
        while (contadorPrimos < ordenMatriz * ordenMatriz) {
            if (esPrimo(numeroActual)) {
                primos[contadorPrimos] = numeroActual;
                contadorPrimos++;
            }
            numeroActual++;
        }
        return primos;
    }

    // Método para llenar la matriz en forma de caracol
    public static void llenarMatrizEnCaracol(int[][] matrizCaracol, int ordenMatriz) {
        int[] primos = generarPrimos(ordenMatriz);
        int indicePrimo = 0;

        int filaSuperior = 0, filaInferior = ordenMatriz - 1, columnaIzquierda = 0, columnaDerecha = ordenMatriz - 1;

        while (filaSuperior <= filaInferior && columnaIzquierda <= columnaDerecha) {
            // Llenar la fila inferior
            for (int columna = columnaIzquierda; columna <= columnaDerecha; columna++) {
                matrizCaracol[filaInferior][columna] = primos[indicePrimo++];
            }
            filaInferior--;

            // Llenar la columna derecha
            for (int fila = filaInferior; fila >= filaSuperior; fila--) {
                matrizCaracol[fila][columnaDerecha] = primos[indicePrimo++];
            }
            columnaDerecha--;

            // Llenar la fila superior
            if (filaSuperior <= filaInferior) {
                for (int columna = columnaDerecha; columna >= columnaIzquierda; columna--) {
                    matrizCaracol[filaSuperior][columna] = primos[indicePrimo++];
                }
                filaSuperior++;
            }

            // Llenar la columna izquierda
            if (columnaIzquierda <= columnaDerecha) {
                for (int fila = filaSuperior; fila <= filaInferior; fila++) {
                    matrizCaracol[fila][columnaIzquierda] = primos[indicePrimo++];
                }
                columnaIzquierda++;
            }
        }
    }

    // Método para imprimir la matriz
    public static void imprimirMatriz(int[][] matrizCaracol, int ordenMatriz) {
        for (int fila = 0; fila < ordenMatriz; fila++) {
            for (int columna = 0; columna < ordenMatriz; columna++) {
                System.out.print(matrizCaracol[fila][columna] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingreso del orden de la matriz
        System.out.print("Introduce el orden de la matriz (n): ");
        int ordenMatriz = scanner.nextInt();

        // Crear la matriz de orden n x n
        int[][] matrizCaracol = new int[ordenMatriz][ordenMatriz];

        // Llenar la matriz en forma de caracol con los números primos
        long tiempoInicio = System.nanoTime();
        llenarMatrizEnCaracol(matrizCaracol, ordenMatriz);
        long tiempoFin = System.nanoTime();

        // Imprimir la matriz
        System.out.println("\nMatriz llena en forma de caracol con los primeros " + (ordenMatriz * ordenMatriz) + " números primos:");
        imprimirMatriz(matrizCaracol, ordenMatriz);

        // Evaluar el tiempo de ejecución
        long duracion = tiempoFin - tiempoInicio;
        System.out.println("\nTiempo de ejecución: " + duracion + " nanosegundos");

    }
}
