package Principal;

import java.util.Random;

public class MatrizNumeros {
    private int[][] matriz;
    private int filas;
    private int columnas;
    private Random generadorAleatorio;

    public MatrizNumeros(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int[filas][columnas];
        this.generadorAleatorio = new Random();
    }

    // Método para llenar la matriz con números aleatorios en un rango
    public void generarNumerosAleatorios(int minimo, int maximo) {
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                matriz[fila][columna] = generadorAleatorio.nextInt(maximo - minimo + 1) + minimo;
            }
        }
    }

    // Método para imprimir la matriz
    public void imprimirMatriz() {
        for (int[] fila : matriz) {
            for (int numero : fila) {
                System.out.printf("%4d", numero);
            }
            System.out.println();
        }
    }

    // Método para buscar y mostrar los números primos en la matriz
    public void buscarNumerosPrimos() {
        System.out.println("\nNúmeros primos encontrados:");
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (esPrimo(matriz[fila][columna])) {
                    System.out.printf("Primo %d encontrado en [%d][%d]\n", matriz[fila][columna], fila, columna);
                }
            }
        }
    }

    // Método para verificar si un número es primo
    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero == 2) {
            return true;
        }
        if (numero % 2 == 0) {
            return false;
        }
        int raiz = (int) Math.sqrt(numero);  // Calcula la raíz cuadrada una sola vez
        for (int divisor = 3; divisor <= raiz; divisor += 2) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
