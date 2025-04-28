import java.math.BigInteger;
import java.math.RoundingMode; // Necesario para gcd
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Clase para representar Fracciones con BigInteger


public class Main {

    // Caché para Fibonacci (usando BigInteger)
    private static Map<Integer, BigInteger> fibCache = new HashMap<>();
    static {
        fibCache.put(1, BigInteger.ONE);
        fibCache.put(2, BigInteger.ONE);
    }

    // 1. Generador de números de Fibonacci (iterativo con caché y BigInteger)
    public static BigInteger fibonacci(int k) {
        if (k <= 0) {
            return BigInteger.ZERO; // F(0) o menos es 0
        }
        if (fibCache.containsKey(k)) {
            return fibCache.get(k);
        }

        // Encontrar el último k calculado en el caché
        int lastKnownK = 0;
        if (!fibCache.isEmpty()) {
            // Encontrar la clave máxima (puede ser ineficiente para Maps muy grandes)
            // Una mejor estructura (como TreeMap o seguimiento manual) sería más rápida
            for(int key : fibCache.keySet()) {
                lastKnownK = Math.max(lastKnownK, key);
            }
        }


        // Asegurar que la base F(1) y F(2) exista
        if (lastKnownK < 2) {
            fibCache.putIfAbsent(1, BigInteger.ONE);
            fibCache.putIfAbsent(2, BigInteger.ONE);
            lastKnownK = 2;
            if (k <= lastKnownK) return fibCache.get(k);
        }

        BigInteger a = fibCache.get(lastKnownK - 1); // Necesita F(0) si lastKnownK es 1
        if(a == null && lastKnownK == 1) a = BigInteger.ZERO; // Asumir F(0)=0
        if(a == null) throw new IllegalStateException("Cache está inconsistente"); // Error si falta F(k-1)

        BigInteger b = fibCache.get(lastKnownK);

        for (int i = lastKnownK + 1; i <= k; i++) {
            BigInteger nextFib = a.add(b);
            a = b;
            b = nextFib;
            fibCache.put(i, b); // Guardar en caché
        }
        return b; // Devuelve Fk
    }


    // 2. Función para calcular el k-ésimo término de la serie (Grupo 1)
    public static Fraction calculateTermGroup1(int k) {
        if (k <= 0) {
            return new Fraction(BigInteger.ZERO, BigInteger.ONE); // Fracción 0/1
        }

        BigInteger numerator = fibonacci(k);
        // Usar BigInteger para el cálculo del denominador también
        BigInteger denominator = BigInteger.valueOf(8).multiply(BigInteger.valueOf(k - 1)).subtract(BigInteger.ONE);

        if (denominator.equals(BigInteger.ZERO)) {
            System.out.println("Advertencia: Denominador cero para k=" + k);
            // Devolver 0/1 u otra indicación de error
            return new Fraction(BigInteger.ZERO, BigInteger.ONE);
        }

        return new Fraction(numerator, denominator);
    }

    // 3. Función para llenar la matriz A (Zigzag desde abajo-derecha)
    public static Fraction[][] fillMatrix(int n, int m) {
        Fraction[][] matrix = new Fraction[n][m];
        int termIndex = 1; // Índice k para la serie, comienza en 1
        int totalTerms = n * m;
        boolean goingUp = true; // Empezamos subiendo por la última columna

        // Iterar por las columnas de derecha a izquierda
        for (int j = m - 1; j >= 0; j--) { // Column index from m-1 down to 0
            if (goingUp) {
                // Llenar hacia arriba: filas desde n-1 hasta 0
                for (int i = n - 1; i >= 0; i--) { // Row index from n-1 down to 0
                    if (termIndex <= totalTerms) {
                        matrix[i][j] = calculateTermGroup1(termIndex); // Usa Grupo 1
                        termIndex++;
                    } else {
                        matrix[i][j] = new Fraction(0); // Rellenar con 0/1
                    }
                }
            } else {
                // Llenar hacia abajo: filas desde 0 hasta n-1
                for (int i = 0; i < n; i++) { // Row index from 0 up to n-1
                    if (termIndex <= totalTerms) {
                        matrix[i][j] = calculateTermGroup1(termIndex); // Usa Grupo 1
                        termIndex++;
                    } else {
                        matrix[i][j] = new Fraction(0);
                    }
                }
            }
            // Cambiar la dirección para la siguiente columna
            goingUp = !goingUp;
        }
        return matrix;
    }

    // 4. Función para calcular la transpuesta de una matriz de Fracciones
    public static Fraction[][] transpose(Fraction[][] matrix) {
        if (matrix == null || matrix.length == 0) return new Fraction[0][0];
        int n = matrix.length;    // filas originales
        int m = matrix[0].length; // columnas originales
        Fraction[][] transposedMatrix = new Fraction[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }


    // 5. Función para multiplicar dos matrices de Fracciones (A * B)
    public static Fraction[][] multiplyMatrices(Fraction[][] matrixA, Fraction[][] matrixB) {
        if (matrixA == null || matrixB == null || matrixA.length == 0 || matrixB.length == 0) {
            throw new IllegalArgumentException("Matrices no pueden ser nulas o vacías");
        }
        int n1 = matrixA.length;    // Filas A
        int m1 = matrixA[0].length; // Columnas A
        int n2 = matrixB.length;    // Filas B
        int m2 = matrixB[0].length; // Columnas B

        if (m1 != n2) {
            throw new IllegalArgumentException("Dimensiones incompatibles para multiplicación: " + m1 + " != " + n2);
        }

        Fraction[][] resultMatrix = new Fraction[n1][m2];

        for (int i = 0; i < n1; i++) { // Iterar filas resultado (filas A)
            for (int j = 0; j < m2; j++) { // Iterar columnas resultado (columnas B)
                Fraction sum = new Fraction(0); // Suma para C[i][j] inicializada a 0/1
                for (int k = 0; k < m1; k++) { // Iterar sobre columnas A / filas B
                    Fraction termA = matrixA[i][k] != null ? matrixA[i][k] : new Fraction(0);
                    Fraction termB = matrixB[k][j] != null ? matrixB[k][j] : new Fraction(0);
                    sum = sum.add(termA.multiply(termB));
                }
                resultMatrix[i][j] = sum; // Asignar suma (ya simplificada por constructor de Fraction)
            }
        }
        return resultMatrix;
    }


    // --- Ejecución Principal ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0;

        // 1. Obtener dimensiones del usuario
        while (true) {
            try {
                System.out.print("Introduce el número de filas (n): ");
                n = scanner.nextInt();
                System.out.print("Introduce el número de columnas (m): ");
                m = scanner.nextInt();
                if (n > 0 && m > 0) {
                    break;
                } else {
                    System.out.println("n y m deben ser enteros positivos.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Introduce números enteros.");
                scanner.next(); // Limpiar buffer del scanner
            }
        }

        System.out.printf("\nEjecutando experimento para matriz %dx%d (Grupo 1, Llenado Zigzag)...\n", n, m);

        List<Long> executionTimesNanos = new ArrayList<>();
        Fraction[][] matrixAExample = null;
        Fraction[][] resultMatrixExample = null;

        // 2. Bucle de 10 ejecuciones para medir tiempos
        for (int i = 0; i < 10; i++) {
            System.out.printf("--- Ejecución %d/10 ---\n", i + 1);
            long startTime = System.nanoTime();

            // --- Experimento ---
            // a) Llenar matriz A
            Fraction[][] matrixA = fillMatrix(n, m);
            // b) Calcular transpuesta A^T
            Fraction[][] matrixAT = transpose(matrixA);
            // c) Multiplicar A * A^T
            Fraction[][] resultMatrix = null;
            try {
                resultMatrix = multiplyMatrices(matrixA, matrixAT);
            } catch (IllegalArgumentException e) {
                System.out.println("Error en multiplicación: " + e.getMessage());
                // Continuar sin resultado o manejar error
            }
            // --- Fin Experimento ---


            long endTime = System.nanoTime();
            long durationNanos = endTime - startTime;
            executionTimesNanos.add(durationNanos);
            // Convertir a segundos para mostrar
            double durationSeconds = durationNanos / 1_000_000_000.0;
            System.out.printf("Tiempo de ejecución: %.6f segundos\n", durationSeconds);

            // Guardar matrices de la primera ejecución
            if (i == 0) {
                matrixAExample = matrixA;
                resultMatrixExample = resultMatrix;
            }
            // Limpiar caché de Fibonacci entre ejecuciones podría ser relevante
            // si se quiere medir el cálculo completo cada vez, pero
            // la versión Python tampoco lo hacía explícitamente dentro del bucle.
            // fibCache.clear(); fibCache.put(1, BigInteger.ONE); fibCache.put(2, BigInteger.ONE);

        }

        // 3. Mostrar resultados finales
        System.out.println("\n--- Resultados Finales ---");

        System.out.println("\nMatriz A (llenado zigzag, primera ejecución):");
        if (matrixAExample != null) {
            for (int i = 0; i < matrixAExample.length; i++) {
                System.out.print("[");
                for (int j = 0; j < matrixAExample[i].length; j++) {
                    System.out.print("'" + matrixAExample[i][j] + "'");
                    if (j < matrixAExample[i].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        } else {
            System.out.println("No se pudo generar la Matriz A.");
        }

        System.out.printf("\nResultado de la multiplicación A * A^T (matriz %dx%d, primera ejecución):\n", n, n);
        if (resultMatrixExample != null) {
            for (int i = 0; i < resultMatrixExample.length; i++) {
                System.out.print("[");
                for (int j = 0; j < resultMatrixExample[i].length; j++) {
                    System.out.print("'" + resultMatrixExample[i][j] + "'");
                    if (j < resultMatrixExample[i].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        } else {
            System.out.println("No se pudo calcular la multiplicación.");
        }

        // Calcular estadísticas de tiempo manualmente
        System.out.println("\nTiempos de ejecución de las 10 corridas (nanosegundos):");
        long sumNanos = 0;
        for (int i = 0; i < executionTimesNanos.size(); i++) {
            long t = executionTimesNanos.get(i);
            System.out.printf("Corrida %d: %d ns (%.6f s)\n", i + 1, t, t / 1_000_000_000.0);
            sumNanos += t;
        }

        double meanNanos = (double) sumNanos / executionTimesNanos.size();
        double variance = 0;
        for (long t : executionTimesNanos) {
            variance += Math.pow(t - meanNanos, 2);
        }
        variance /= executionTimesNanos.size();
        double stdDevNanos = Math.sqrt(variance);

        System.out.printf("\nTiempo promedio: %.0f ns (%.6f segundos)\n", meanNanos, meanNanos / 1_000_000_000.0);
        System.out.printf("Desviación estándar: %.0f ns (%.6f segundos)\n", stdDevNanos, stdDevNanos / 1_000_000_000.0);

        // Sugerencia para graficar (requiere biblioteca externa en Java como JFreeChart o similar)
        System.out.println("\nPara graficar los tiempos en Java, necesitarías usar una biblioteca gráfica como JFreeChart, Apache Commons Chart, etc.");
        System.out.println("El proceso implica añadir la biblioteca a tu proyecto y escribir código para crear el gráfico.");

        scanner.close(); // Cerrar scanner
    }
}