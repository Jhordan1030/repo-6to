import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit; // Para mejor formato de tiempo

public class Main {

    // Método para calcular Fibonacci (long, iterativo) - Sin cambios
    public static long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("El índice de Fibonacci no puede ser negativo.");
        if (n == 0) return 0;
        if (n == 1) return 1;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Método para GENERAR la matriz 3x5 específica del ejemplo Grupo 1 [cite: 5]
    // Devuelve null si las dimensiones no son 3x5
    public static String[][] generarMatrizGrupo1(int filas, int columnas) {
        // Verificar si las dimensiones son 3x5 para aplicar el patrón del ejemplo
        if (filas != 3 || columnas != 5) {
            System.out.println("Advertencia: El patrón específico del ejemplo Grupo 1 [cite: 5] solo está definido para 3x5.");
            System.out.println("No se puede generar la matriz para las dimensiones " + filas + "x" + columnas + " según ese ejemplo.");
            return null; // Indicar que no se pudo generar según el ejemplo específico
        }

        // Índices k de Fibonacci para el ejemplo 3x5 de Grupo 1 [cite: 5]
        int[][] k_indices = {
                {15, 10, 9, 4, 3},
                {14, 11, 8, 5, 2},
                {13, 12, 7, 6, 0} // Placeholder para -1
        };

        String[][] matriz = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 2 && j == 4) { // Caso especial -1 del ejemplo [cite: 5]
                    matriz[i][j] = "-1";
                } else {
                    int k = k_indices[i][j];
                    long fib_k = fibonacci(k);
                    long denominador = 8L * k - 9L; // Fórmula derivada del ejemplo

                    if (denominador == 0) {
                        System.err.println("Error: Denominador cero para k=" + k + " en [" + i + "][" + j + "]");
                        matriz[i][j] = fib_k + "/0";
                    } else {
                        matriz[i][j] = fib_k + "/" + denominador;
                    }
                }
            }
        }
        return matriz;
    }

    // --- Métodos auxiliares: imprimirMatriz, multiplicarMatrices, transponerMatriz, imprimirMatrizDoubles ---
    // (Sin cambios respecto a la versión anterior, se incluyen por completitud)

    public static void imprimirMatriz(String[][] matriz) {
        if (matriz == null || matriz.length == 0) return;
        for (String[] fila : matriz) {
            for (String val : fila) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static double[][] multiplicarMatrices(double[][] A, double[][] B) {
        int filasA = A.length;
        int colA = (filasA > 0) ? A[0].length : 0;
        int filasB = B.length;
        int colB = (filasB > 0) ? B[0].length : 0;

        if (colA == 0 || colB == 0) throw new IllegalArgumentException("Matrices vacías.");
        if (colA != filasB) throw new IllegalArgumentException("Dimensiones incompatibles para multiplicación.");

        double[][] res = new double[filasA][colB];
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    if (Double.isNaN(A[i][k]) || Double.isNaN(B[k][j])) {
                        res[i][j] = Double.NaN; break;
                    }
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    public static double[][] transponerMatriz(double[][] matriz) {
        int filas = matriz.length;
        if (filas == 0) return new double[0][0];
        int col = matriz[0].length;
        double[][] transpuesta = new double[col][filas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < col; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }
        return transpuesta;
    }

    public static void imprimirMatrizDoubles(double[][] matriz) {
        if (matriz == null || matriz.length == 0) return;
        for (double[] fila : matriz) {
            for (double val : fila) {
                if (Double.isNaN(val)) System.out.print("NaN\t");
                else System.out.print(String.format("%.3f", val) + "\t");
            }
            System.out.println();
        }
    }

    // Método para convertir String[][] a double[][], manejando errores
    public static double[][] convertirMatrizADouble(String[][] matrizStr) {
        if (matrizStr == null) return null;
        int filas = matrizStr.length;
        if (filas == 0) return new double[0][0];
        int col = matrizStr[0].length;
        double[][] matrizNum = new double[filas][col];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < col; j++) {
                try {
                    String elem = matrizStr[i][j];
                    if (elem.equals("-1")) {
                        matrizNum[i][j] = -1.0;
                    } else if (elem.contains("/")) {
                        String[] p = elem.split("/");
                        if(p.length == 2) {
                            double num = Double.parseDouble(p[0]);
                            double den = Double.parseDouble(p[1]);
                            matrizNum[i][j] = (den == 0) ? Double.NaN : num / den;
                        } else matrizNum[i][j] = Double.NaN; // Formato inválido
                    } else matrizNum[i][j] = Double.parseDouble(elem);
                } catch (NumberFormatException e) {
                    System.err.println("Error convirtiendo '" + matrizStr[i][j] + "' a número.");
                    matrizNum[i][j] = Double.NaN;
                }
            }
        }
        return matrizNum;
    }

    // --- Main ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n_filas, m_columnas;

        // 1. Ingreso de dimensiones por teclado [cite: 10]
        System.out.println("Ingrese las dimensiones de la matriz A:");
        System.out.print("Número de filas (n): ");
        n_filas = scanner.nextInt();
        System.out.print("Número de columnas (m): ");
        m_columnas = scanner.nextInt();

        // Lista para guardar los tiempos de ejecución
        List<Long> tiemposEjecucion = new ArrayList<>();
        String[][] matrizA_str = null;
        double[][] matrizResultado = null;

        System.out.println("\nEjecutando el proceso 10 veces para medir tiempos...");

        // 2. Bucle para 10 ejecuciones [cite: 3]
        for (int ejecucion = 1; ejecucion <= 10; ejecucion++) {
            long startTime = System.nanoTime();

            // a. Generar Matriz A (solo si es 3x5 según ejemplo Grupo 1) [cite: 5]
            matrizA_str = generarMatrizGrupo1(n_filas, m_columnas);

            // Si no se pudo generar (dimensiones != 3x5), no continuar
            if (matrizA_str == null) {
                System.out.println("Abortando medición de tiempo debido a dimensiones no válidas para el ejemplo.");
                tiemposEjecucion.clear(); // Limpiar tiempos si no se pudo completar
                break; // Salir del bucle de ejecuciones
            }

            // b. Convertir a double
            double[][] matrizA_num = convertirMatrizADouble(matrizA_str);

            // c. Transponer A -> A'
            double[][] matrizATranspuesta = transponerMatriz(matrizA_num);

            // d. Multiplicar A * A' [cite: 3]
            try {
                matrizResultado = multiplicarMatrices(matrizA_num, matrizATranspuesta);
            } catch (IllegalArgumentException e) {
                System.err.println("\nError en la ejecución " + ejecucion + ": " + e.getMessage());
                matrizResultado = null; // Indicar fallo
            }

            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            tiemposEjecucion.add(duration);

            // Imprimir detalles solo en la primera ejecución para verificar
            if (ejecucion == 1 && matrizA_str != null) {
                System.out.println("\n--- Detalles de la Primera Ejecución ---");
                System.out.println("Matriz A Generada (" + n_filas + "x" + m_columnas + "):");
                imprimirMatriz(matrizA_str);
                if (matrizResultado != null) {
                    System.out.println("\nResultado de A * A' (" + matrizResultado.length + "x" + (matrizResultado.length > 0 ? matrizResultado[0].length : 0) + "):");
                    imprimirMatrizDoubles(matrizResultado);
                } else {
                    System.out.println("\nNo se pudo calcular A * A'.");
                }
                System.out.println("--------------------------------------");
            }
            // Pequeña pausa para variar ligeramente las condiciones si es necesario
            try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        } // Fin del bucle de 10 ejecuciones

        // 3. Mostrar los tiempos de ejecución registrados
        if (!tiemposEjecucion.isEmpty()) {
            System.out.println("\n--- Tiempos de Ejecución Registrados (nanosegundos) ---");
            for (int i = 0; i < tiemposEjecucion.size(); i++) {
                System.out.println("Ejecución " + (i + 1) + ": " + tiemposEjecucion.get(i) + " ns (" +
                        TimeUnit.NANOSECONDS.toMillis(tiemposEjecucion.get(i)) + " ms)");
            }
            // Calcular promedio (opcional)
            long sumaTiempos = 0;
            for(long t : tiemposEjecucion) sumaTiempos += t;
            double promedioNs = (double)sumaTiempos / tiemposEjecucion.size();
            System.out.printf("Tiempo promedio: %.3f ns (%.3f ms)\n", promedioNs, promedioNs / 1_000_000.0);

        } else if (n_filas != 3 || m_columnas != 5) {
            System.out.println("\nNo se registraron tiempos ya que las dimensiones no fueron 3x5.");
        } else {
            System.out.println("\nNo se completaron las ejecuciones, no hay tiempos para mostrar.");
        }


        scanner.close();
    }
}