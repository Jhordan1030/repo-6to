package Inicial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matriz {

    private int filas;
    private int columnas;
    private int[][] matrizNumeros;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matrizNumeros = new int[filas][columnas];
        generarMatrizAleatoria();
    }

    private void generarMatrizAleatoria() {
        Random generadorAleatorio = new Random();
        for (int indiceFila = 0; indiceFila < filas; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
                this.matrizNumeros[indiceFila][indiceColumna] = generadorAleatorio.nextInt(100); // Genera números entre 0 y 999
            }
        }
    }

    public void mostrarMatriz() {
        System.out.println("Matriz generada:");
        for (int indiceFila = 0; indiceFila < filas; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
                System.out.printf("%4d ", this.matrizNumeros[indiceFila][indiceColumna]);
            }
            System.out.println();
        }
    }

    public List<Integer> encontrarNumerosPrimos() {
        long tiempoInicio = System.nanoTime();
        List<Integer> numerosPrimosEncontrados = new ArrayList<>();
        for (int indiceFila = 0; indiceFila < filas; indiceFila++) {
            for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
                int numeroActual = this.matrizNumeros[indiceFila][indiceColumna];
                if (esPrimo(numeroActual)) {
                    numerosPrimosEncontrados.add(numeroActual);
                }
            }
        }
        long tiempoFin = System.nanoTime();
        long tiempoEstimadoNano = tiempoFin - tiempoInicio;
        double tiempoEstimadoMilis = (double) tiempoEstimadoNano / 1_000_000.0;

        System.out.printf("Tiempo estimado para encontrar primos: %.3f milisegundos%n", tiempoEstimadoMilis);
        return numerosPrimosEncontrados;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }


}
