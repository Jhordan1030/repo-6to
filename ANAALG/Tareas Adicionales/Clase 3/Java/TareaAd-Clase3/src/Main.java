import java.util.Random;
import java.util.Scanner;

public class Main {
    // Función para verificar si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int divisor = 2; divisor <= Math.sqrt(numero); divisor++) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    // Función para generar números aleatorios y contar los primos
    public static int contarPrimos(int[] arregloNumeros) {
        int cantidadPrimos = 0;
        for (int numero : arregloNumeros) {
            if (esPrimo(numero)) {
                cantidadPrimos++;
            }
        }
        return cantidadPrimos;
    }

    // Función para calcular la suma de las filas y columnas de una matriz
    public static void sumarFilasYColumnas(int[][] matrizNumeros, int tamañoMatriz) {
        // Sumar filas
        for (int fila = 0; fila < tamañoMatriz; fila++) {
            int sumaFila = 0;
            for (int columna = 0; columna < tamañoMatriz; columna++) {
                sumaFila += matrizNumeros[fila][columna];
            }
            System.out.println("Suma de la fila " + (fila + 1) + ": " + sumaFila);
        }

        // Sumar columnas
        for (int columna = 0; columna < tamañoMatriz; columna++) {
            int sumaColumna = 0;
            for (int fila = 0; fila < tamañoMatriz; fila++) {
                sumaColumna += matrizNumeros[fila][columna];
            }
            System.out.println("Suma de la columna " + (columna + 1) + ": " + sumaColumna);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parte 1: Ingresar arreglo de números aleatorios
        System.out.print("Ingresa el tamaño del arreglo: ");
        int tamañoArreglo = scanner.nextInt();
        int[] arregloNumeros = new int[tamañoArreglo];
        Random generadorAleatorio = new Random();

        // Llenar el arreglo con números aleatorios
        System.out.println("Arreglo generado:");
        for (int indice = 0; indice < tamañoArreglo; indice++) {
            arregloNumeros[indice] = generadorAleatorio.nextInt(100);  // Números aleatorios entre 0 y 99
            System.out.print(arregloNumeros[indice] + " ");
        }

        // Contar los números primos
        int cantidadPrimos = contarPrimos(arregloNumeros);
        System.out.println("\nCantidad de números primos en el arreglo: " + cantidadPrimos);

        // Parte 2: Crear y sumar las filas y columnas de una matriz
        System.out.print("\nIngresa el tamaño de la matriz (n): ");
        int tamañoMatriz = scanner.nextInt();
        int[][] matrizNumeros = new int[tamañoMatriz][tamañoMatriz];

        // Llenar la matriz con números aleatorios
        System.out.println("Matriz generada:");
        for (int fila = 0; fila < tamañoMatriz; fila++) {
            for (int columna = 0; columna < tamañoMatriz; columna++) {
                matrizNumeros[fila][columna] = generadorAleatorio.nextInt(100);  // Números aleatorios entre 0 y 99
                System.out.print(matrizNumeros[fila][columna] + " ");
            }
            System.out.println();
        }

        // Sumar las filas y columnas
        sumarFilasYColumnas(matrizNumeros, tamañoMatriz);

    }
}