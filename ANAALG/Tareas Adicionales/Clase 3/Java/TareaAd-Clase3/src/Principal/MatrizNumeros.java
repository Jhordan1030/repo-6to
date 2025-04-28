package Principal;

import java.util.Random;

public class MatrizNumeros {
    private int[][] matriz;
    private int tamaño;
    private Random random;

    public MatrizNumeros(int tamaño) {
        this.tamaño = tamaño;
        matriz = new int[tamaño][tamaño];
        random = new Random();
    }

    public void generarNumerosAleatorios(int minimo, int maximo) {
        for (int fila = 0; fila < tamaño; fila++) {
            for (int columna = 0; columna < tamaño; columna++) {
                matriz[fila][columna] = random.nextInt(maximo - minimo + 1) + minimo;
            }
        }
    }

    public void mostrarMatriz() {
        System.out.println("Matriz generada:");
        for (int fila = 0; fila < tamaño; fila++) {
            for (int columna = 0; columna < tamaño; columna++) {
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }
    }

    public void sumarFilasYColumnas() {
        // Sumar filas
        for (int fila = 0; fila < tamaño; fila++) {
            int sumaFila = 0;
            for (int columna = 0; columna < tamaño; columna++) {
                sumaFila += matriz[fila][columna];
            }
            System.out.println("Suma de la fila " + (fila + 1) + ": " + sumaFila);
        }

        // Sumar columnas
        for (int columna = 0; columna < tamaño; columna++) {
            int sumaColumna = 0;
            for (int fila = 0; fila < tamaño; fila++) {
                sumaColumna += matriz[fila][columna];
            }
            System.out.println("Suma de la columna " + (columna + 1) + ": " + sumaColumna);
        }
    }
}
