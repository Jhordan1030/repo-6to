package Inicial;

import java.util.Random;

public class Matriz {
    private char[][] matriz;
    private int filas;
    private int columnas;

    // Constructor de la clase Matriz
    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new char[filas][columnas];
        llenarMatriz();
    }

    // Método para llenar la matriz con letras aleatorias
    private void llenarMatriz() {
        Random random = new Random();
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                matriz[fila][columna] = (char) ('A' + random.nextInt(26)); // Llenar con letras aleatorias
            }
        }
    }

    // Método para imprimir la matriz
    public void imprimirMatriz() {
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }
    }

    // Método para obtener la matriz
    public char[][] getMatriz() {
        return matriz;
    }

    // Método para obtener el número de filas
    public int getFilas() {
        return filas;
    }

    // Método para obtener el número de columnas
    public int getColumnas() {
        return columnas;
    }

    // Método para obtener una columna específica
    public char[] getColumna(int columna) {
        char[] columnaArray = new char[filas];
        for (int fila = 0; fila < filas; fila++) {
            columnaArray[fila] = matriz[fila][columna];
        }
        return columnaArray;
    }

    // Método para establecer una columna en la matriz
    public void setColumna(int columna, char[] columnaArray) {
        for (int fila = 0; fila < filas; fila++) {
            matriz[fila][columna] = columnaArray[fila];
        }
    }
}
