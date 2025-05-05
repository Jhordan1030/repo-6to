package Inicial;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        int m = 5; // Número de filas
        int n = 7; // Número de columnas
        Matriz generador = new Matriz(m, n);

        generador.mostrarMatriz();
        List<Integer> primosEncontrados = generador.encontrarNumerosPrimos();

        System.out.println("Números primos encontrados en la matriz:");
        System.out.println(primosEncontrados);
    }
    
}
