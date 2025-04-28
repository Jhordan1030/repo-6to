package Principal;

import java.util.Random;

public class ArregloNumeros {
    private int[] numeros;
    private Random generadorAleatorio;

    public ArregloNumeros(int tamaño) {
        this.numeros = new int[tamaño];
        this.generadorAleatorio = new Random();
    }

    // Generar números aleatorios en el rango dado
    public void generarNumerosAleatorios(int minimo, int maximo) {
        for (int indice = 0; indice < numeros.length; indice++) {
            numeros[indice] = generadorAleatorio.nextInt(maximo - minimo + 1) + minimo;
        }
    }

    // Mostrar el contenido del arreglo
    public void mostrarArreglo() {
        System.out.println("Arreglo generado:");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
        System.out.println();
    }

    // Contar la cantidad de números primos en el arreglo
    public int contarPrimos() {
        int cantidadPrimos = 0;
        for (int numero : numeros) {
            if (esPrimo(numero)) {
                cantidadPrimos++;
            }
        }
        return cantidadPrimos;
    }

    // Método que verifica si un número es primo
    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int divisor = 2; divisor <= Math.sqrt(numero); divisor++) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
