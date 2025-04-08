import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Ingrese el número de filas (m): ");
        int m = scanner.nextInt();
        System.out.print("Ingrese el número de columnas (n): ");
        int n = scanner.nextInt();

        int[][] matriz = new int[m][n];
        int[] primosPorFila = new int[m];
        ArrayList<Integer>[] numerosPrimosPorFila = new ArrayList[m];
        ArrayList<Integer> todosLosPrimos = new ArrayList<>();

        for (int fila = 0; fila < m; fila++) {
            numerosPrimosPorFila[fila] = new ArrayList<>();
        }

        System.out.println("\nMatriz generada:");
        for (int fila = 0; fila < m; fila++) {
            for (int columna = 0; columna < n; columna++) {
                matriz[fila][columna] = random.nextInt(10) + 1;
                System.out.print(matriz[fila][columna] + "\t");

                if (esPrimo(matriz[fila][columna])) {
                    primosPorFila[fila]++;
                    numerosPrimosPorFila[fila].add(matriz[fila][columna]);
                    todosLosPrimos.add(matriz[fila][columna]);
                }
            }
            System.out.println();
        }


        System.out.println("\nCantidad total de números primos en la matriz: " + todosLosPrimos.size());
        System.out.println("Números primos encontrados en toda la matriz: " + todosLosPrimos);

        scanner.close();
    }



    public static boolean esPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}