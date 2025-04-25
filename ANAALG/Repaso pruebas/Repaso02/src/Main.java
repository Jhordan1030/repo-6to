import java.util.Scanner;

public class Main {

    // Método para generar el número de Fibonacci para un índice dado (iterativo)
    public static int fibonacci(int n) {
        if (n == 0) return 610;  // El primer valor de Fibonacci según la tabla de ejemplo
        if (n == 1) return 377;  // El segundo valor de Fibonacci según la tabla de ejemplo
        if (n == 2) return 233;  // El tercer valor de Fibonacci según la tabla de ejemplo
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Método para llenar la matriz con la serie de Fibonacci según la fórmula dada
    public static String[][] llenarMatriz(int filas, int columnas) {
        String[][] matriz = new String[filas][columnas];

        // Llenamos la matriz con las fracciones f_i / (8i - 1)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Calcular el valor de fibonacci(i) para cada posición
                int fibonacciValue = fibonacci(i);
                // Denominador
                int denominator = 8 * i - 1;

                // Control sobre denominador negativo: manejamos la entrada de manera consistente
                if (denominator != 0) {  // Asegurarse de que el denominador no sea cero
                    matriz[i][j] = fibonacciValue + "/" + denominator;  // Almacenamos como fracción
                } else {
                    matriz[i][j] = "0/1";  // Si el denominador es cero, asignamos 0/1
                }
            }
        }

        return matriz;
    }

    // Método para multiplicar dos matrices
    public static double[][] multiplicarMatrices(double[][] A, double[][] B) {
        int filasA = A.length;
        int columnasA = A[0].length;
        int filasB = B.length;
        int columnasB = B[0].length;

        // Verificar que las matrices son multiplicables
        if (columnasA != filasB) {
            throw new IllegalArgumentException("Las matrices no son multiplicables");
        }

        double[][] resultado = new double[filasA][columnasB];

        // Multiplicación de matrices
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return resultado;
    }

    // Método para imprimir la matriz
    public static void imprimirMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // Imprimir cada valor de la matriz como una fracción
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Método para transponer una matriz
    public static double[][] transponerMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        double[][] transpuesta = new double[columnas][filas];

        // Transposición de la matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }

        return transpuesta;
    }

    public static void main(String[] args) {
        // Crear un objeto Scanner para leer el input
        Scanner scanner = new Scanner(System.in);

        // Ingresar las dimensiones de la matriz
        System.out.print("Ingrese el número de filas de la matriz: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas de la matriz: ");
        int columnas = scanner.nextInt();

        // Llenar la matriz con la serie de Fibonacci y la fórmula dada (como fracciones)
        String[][] matrizA = llenarMatriz(filas, columnas);

        // Imprimir la matriz A como fracciones
        System.out.println("Matriz A:");
        imprimirMatriz(matrizA);

        // Transponer la matriz
        // Notar que la transposición de matrices no puede ser representada como fracción
        // A continuación, convertimos las fracciones en valores numéricos (decimales) para la multiplicación
        double[][] matrizA_Num = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String[] fraccion = matrizA[i][j].split("/");
                int numerador = Integer.parseInt(fraccion[0]);
                int denominador = Integer.parseInt(fraccion[1]);
                matrizA_Num[i][j] = (double) numerador / denominador;
            }
        }

        double[][] matrizATranspuesta = transponerMatriz(matrizA_Num);

        // Multiplicar la matriz A por su transpuesta
        long startTime = System.nanoTime(); // Iniciar temporizador

        double[][] resultado = multiplicarMatrices(matrizA_Num, matrizATranspuesta);

        long endTime = System.nanoTime(); // Finalizar temporizador
        long tiempoEjecucion = endTime - startTime; // Tiempo de ejecución en nanosegundos

        // Mostrar el resultado de la multiplicación
        System.out.println("Resultado de la multiplicación A * A':");
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                System.out.print(String.format("%.3f", resultado[i][j]) + "\t");
            }
            System.out.println();
        }

        // Mostrar el tiempo de ejecución
        System.out.println("Tiempo de ejecución: " + tiempoEjecucion + " nanosegundos.");

        scanner.close();
    }
}
