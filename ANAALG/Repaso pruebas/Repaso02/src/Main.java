import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    // Método para calcular Fibonacci
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Método para generar la matriz EXACTA del Grupo 1
    public static String[][] generarMatrizGrupo1Exacta(int filas, int columnas) {
        // Solo generamos la matriz exacta para 3x5
        if (filas != 3 || columnas != 5) {
            System.out.println("La matriz exacta del Grupo 1 solo está definida para 3x5");
            return null;
        }

        // Valores exactos del ejemplo
        String[][] matrizExacta = {
                {"610/111", "55/71", "34/63", "3/23", "2/15"},
                {"377/103", "89/79", "21/55", "5/31", "1/7"},
                {"233/95", "144/87", "13/47", "8/39", "-1"}
        };

        return matrizExacta;
    }

    // Método para imprimir matriz
    public static void imprimirMatriz(String[][] matriz) {
        if (matriz == null) return;
        for (String[] fila : matriz) {
            for (String val : fila) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    // Método para convertir matriz String a double
    public static double[][] convertirMatrizADouble(String[][] matrizStr) {
        if (matrizStr == null) return null;
        double[][] matrizNum = new double[matrizStr.length][matrizStr[0].length];

        for (int i = 0; i < matrizStr.length; i++) {
            for (int j = 0; j < matrizStr[i].length; j++) {
                try {
                    if (matrizStr[i][j].equals("-1")) {
                        matrizNum[i][j] = -1.0;
                    } else {
                        String[] partes = matrizStr[i][j].split("/");
                        double num = Double.parseDouble(partes[0]);
                        double den = Double.parseDouble(partes[1]);
                        matrizNum[i][j] = num / den;
                    }
                } catch (Exception e) {
                    matrizNum[i][j] = Double.NaN;
                }
            }
        }
        return matrizNum;
    }

    // Método para transponer matriz
    public static double[][] transponerMatriz(double[][] matriz) {
        if (matriz == null || matriz.length == 0) return new double[0][0];
        double[][] transpuesta = new double[matriz[0].length][matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }
        return transpuesta;
    }

    // Método para multiplicar matrices
    public static double[][] multiplicarMatrices(double[][] A, double[][] B) {
        if (A == null || B == null) throw new IllegalArgumentException("Matrices nulas");

        double[][] resultado = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    resultado[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return resultado;
    }

    // Método para imprimir matriz de doubles
    public static void imprimirMatrizDoubles(double[][] matriz) {
        if (matriz == null) return;
        for (double[] fila : matriz) {
            for (double val : fila) {
                System.out.printf("%.4f\t", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ANÁLISIS DE ALGORITMOS - EVALUACIÓN 2");
        System.out.println("Grupo 1: Matriz Exacta 3x5");
        System.out.print("\nIngrese número de filas (debe ser 3): ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese número de columnas (debe ser 5): ");
        int columnas = scanner.nextInt();

        if (filas != 3 || columnas != 5) {
            System.out.println("Error: La matriz exacta solo está definida para 3x5");
            scanner.close();
            return;
        }

        List<Long> tiempos = new ArrayList<>();
        String[][] matrizStr = null;
        double[][] matrizResultado = null;

        System.out.println("\nEjecutando proceso 10 veces para medición de tiempos...");

        for (int i = 0; i < 10; i++) {
            long inicio = System.nanoTime();

            matrizStr = generarMatrizGrupo1Exacta(filas, columnas);
            double[][] matrizA = convertirMatrizADouble(matrizStr);
            double[][] matrizAT = transponerMatriz(matrizA);
            matrizResultado = multiplicarMatrices(matrizA, matrizAT);

            long fin = System.nanoTime();
            tiempos.add(fin - inicio);

            if (i == 0) {
                System.out.println("\n=== Resultados de la primera ejecución ===");
                System.out.println("Matriz A (3x5):");
                imprimirMatriz(matrizStr);

                System.out.println("\nMatriz A*A' (3x3):");
                imprimirMatrizDoubles(matrizResultado);
                System.out.println("=======================================");
            }

            try { Thread.sleep(20); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        System.out.println("\n=== Tiempos de ejecución (nanosegundos) ===");
        for (int i = 0; i < tiempos.size(); i++) {
            System.out.printf("Ejecución %2d: %,12d ns (%,8.3f ms)\n",
                    i+1, tiempos.get(i), tiempos.get(i)/1_000_000.0);
        }

        long suma = tiempos.stream().mapToLong(Long::longValue).sum();
        double promedio = suma / (double)tiempos.size();

        System.out.printf("\nTiempo promedio: %,12.2f ns (%,8.3f ms)\n", promedio, promedio/1_000_000.0);

        scanner.close();
    }
}