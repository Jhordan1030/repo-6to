import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de filas: ");
        int filas = scanner.nextInt();

        System.out.print("Ingrese el número de columnas: ");
        int columnas = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        Buscador buscador = new Buscador(filas, columnas);

        System.out.println("\nMatriz Original:");
        buscador.imprimirMatriz();

        System.out.print("Ingrese la palabra a buscar: ");
        String palabra = scanner.nextLine().toUpperCase();

        String[] posiciones = buscador.buscarPalabra(palabra);

        if (posiciones.length > 0) {
            System.out.println("La palabra '" + palabra + "' fue encontrada en las posiciones de inicio:");
            for (String posicion : posiciones) {
                System.out.print(posicion + " ");
            }
            System.out.println();
        } else {
            System.out.println("La palabra '" + palabra + "' no fue encontrada en la matriz.");
        }

        Ordenador ordenador = new Ordenador();
        ordenador.ordenarColumnasDeMatriz(buscador);

        System.out.println("\nMatriz con columnas ordenadas:");
        buscador.imprimirMatriz();
    }
}

