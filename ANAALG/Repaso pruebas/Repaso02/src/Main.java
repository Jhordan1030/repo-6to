import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit; // Optional: for converting ns to ms or s

public class Main {

    // Cache for Fibonacci numbers
    private static Map<Integer, BigInteger> fibCache = new HashMap<>();

    // --- Fibonacci Calculation ---
    public static BigInteger fibonacci(int k) {
        if (k <= 0) {
            // Consistent with Python: F(1)=1, F(2)=1
            if (k == 1) return BigInteger.ONE;
            return BigInteger.ZERO; // Or handle as error
        }
        if (k == 1 || k == 2) {
            return BigInteger.ONE;
        }
        if (fibCache.containsKey(k)) {
            return fibCache.get(k);
        }

        // Iterative calculation from k=3
        // Ensure cache has base cases
        fibCache.put(1, BigInteger.ONE);
        fibCache.put(2, BigInteger.ONE);

        BigInteger a = BigInteger.ONE; // F(k-2) starts at F(1)
        BigInteger b = BigInteger.ONE; // F(k-1) starts at F(2)

        for (int i = 3; i <= k; i++) {
            // Check if already computed by another call path
            if (fibCache.containsKey(i)) {
                a = fibCache.get(i-1);
                b = fibCache.get(i);
                continue; // Skip to next i if this one is cached
            }
            BigInteger nextFib = a.add(b);
            a = b;
            b = nextFib;
            fibCache.put(i, b); // Store F(i)
        }
        return b; // F(k)
    }


    // --- Series Term Calculation (Grupo 1) ---
    public static Fraction calculateTermGroup1(int k) {
        if (k <= 0) return new Fraction(0, 1); // Or handle error

        BigInteger numerator = fibonacci(k);
        // Denominator: 8*(k-1) - 1
        BigInteger denominator = BigInteger.valueOf(8).multiply(BigInteger.valueOf(k - 1)).subtract(BigInteger.ONE);

        if (denominator.equals(BigInteger.ZERO)) {
            System.err.println("Warning: Denominator is zero for k=" + k);
            // Decide how to handle: return 0, NaN equivalent, or throw exception
            return new Fraction(0, 1); // Return 0/1 for simplicity
        }

        return new Fraction(numerator, denominator);
    }

    // --- Matrix Operations ---
    public static Fraction[][] fillMatrix(int n, int m) {
        Fraction[][] matrix = new Fraction[n][m];
        int termIndex = 1; // k starts from 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = calculateTermGroup1(termIndex++);
            }
        }
        return matrix;
    }

    public static Fraction[][] transpose(Fraction[][] matrix) {
        if (matrix == null || matrix.length == 0) return new Fraction[0][0];
        int n = matrix.length;
        int m = matrix[0].length;
        Fraction[][] transposedMatrix = new Fraction[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    public static Fraction[][] multiplyMatrices(Fraction[][] matrixA, Fraction[][] matrixB) {
        int nA = matrixA.length;
        int mA = matrixA[0].length;
        int nB = matrixB.length;
        int mB = matrixB[0].length;

        if (mA != nB) {
            throw new IllegalArgumentException("Matrix dimensions are incompatible for multiplication: A(" + nA + "x" + mA + "), B(" + nB + "x" + mB + ")");
        }

        Fraction[][] resultMatrix = new Fraction[nA][mB];

        for (int i = 0; i < nA; i++) { // Rows of A
            for (int j = 0; j < mB; j++) { // Columns of B
                Fraction sum = new Fraction(0, 1); // Initialize sum for C[i][j]
                for (int k = 0; k < mA; k++) { // Columns of A / Rows of B
                    if (matrixA[i][k] != null && matrixB[k][j] != null) {
                        sum = sum.add(matrixA[i][k].multiply(matrixB[k][j]));
                    } else {
                        // Handle nulls if they can occur (shouldn't with current fill logic)
                        System.err.println("Warning: Null element encountered during multiplication at A["+i+"]["+k+"] or B["+k+"]["+j+"]");
                    }
                }
                resultMatrix[i][j] = sum; // The sum is already simplified by Fraction class
            }
        }
        return resultMatrix;
    }

    // --- Utility to Print Matrix ---
    public static void printMatrix(String title, Fraction[][] matrix) {
        System.out.println("\n" + title + ":");
        if (matrix == null || matrix.length == 0) {
            System.out.println("[Empty Matrix]");
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < m; j++) {
                System.out.print((matrix[i][j] != null ? matrix[i][j].toString() : "null") + (j == m - 1 ? "" : ", "));
            }
            System.out.println(" ]");
        }
    }


    // --- Main Execution Logic ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0;

        // 1. Get User Input
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
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Introduce números enteros.");
                scanner.next(); // Consume invalid input
            }
        }
        scanner.close(); // Close scanner when done

        System.out.println("\nEjecutando experimento para matriz " + n + "x" + m + " (Grupo 1)...");

        List<Long> executionTimesNanos = new ArrayList<>();
        Fraction[][] matrixAExample = null;
        Fraction[][] resultMatrixExample = null;

        // 2. Timing Loop (10 executions)
        for (int i = 0; i < 10; i++) {
            System.out.println("--- Ejecución " + (i + 1) + "/10 ---");
            // Clear Fibonacci cache for a fairer timing of each run,
            // simulating independent execution (optional, depends on desired measurement)
            // fibCache.clear();

            long startTime = System.nanoTime();

            // Perform the core tasks
            Fraction[][] matrixA = fillMatrix(n, m);
            Fraction[][] matrixATransposed = transpose(matrixA);
            Fraction[][] resultMatrix = multiplyMatrices(matrixA, matrixATransposed);

            long endTime = System.nanoTime();
            long durationNanos = endTime - startTime;
            executionTimesNanos.add(durationNanos);

            // Convert nanoseconds to milliseconds or seconds for readability
            double durationMillis = durationNanos / 1_000_000.0;
            System.out.printf("Tiempo de ejecución: %.3f ms%n", durationMillis);
            // System.out.printf("Tiempo de ejecución: %d ns%n", durationNanos); // Alt: print nanoseconds


            // Save matrices from the first run
            if (i == 0) {
                matrixAExample = matrixA;
                resultMatrixExample = resultMatrix;
            }
        }

        // 3. Display Final Results
        System.out.println("\n--- Resultados Finales ---");

        printMatrix("Matriz A (primera ejecución, " + n + "x" + m + ")", matrixAExample);
        printMatrix("Resultado A * A^T (primera ejecución, " + n + "x" + n + ")", resultMatrixExample);


        System.out.println("\nTiempos de ejecución de las 10 corridas (nanosegundos):");
        long totalNanos = 0;
        for (int i = 0; i < executionTimesNanos.size(); i++) {
            long t = executionTimesNanos.get(i);
            System.out.println("Corrida " + (i + 1) + ": " + t + " ns");
            totalNanos += t;
        }

        if (!executionTimesNanos.isEmpty()) {
            double averageNanos = (double) totalNanos / executionTimesNanos.size();
            double sumSqDiff = 0;
            for (long t : executionTimesNanos) {
                sumSqDiff += Math.pow(t - averageNanos, 2);
            }
            double stdDevNanos = Math.sqrt(sumSqDiff / executionTimesNanos.size());

            System.out.printf("\nTiempo promedio: %.3f ns (%.3f ms)%n", averageNanos, averageNanos / 1_000_000.0);
            System.out.printf("Desviación estándar: %.3f ns (%.3f ms)%n", stdDevNanos, stdDevNanos / 1_000_000.0);
        }

        System.out.println("\nNota: Para graficar los tiempos, necesitarías exportar estos valores y usar una herramienta externa o una biblioteca de gráficos Java (como JFreeChart).");
    }
}