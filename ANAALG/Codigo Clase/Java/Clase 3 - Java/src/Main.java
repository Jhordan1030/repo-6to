import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Leer los números desde el archivo
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("multiple_data_1_to_1000_corrected.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Ignorar las líneas con encabezados o vacías
                if (!linea.startsWith("Data Size") && !linea.trim().isEmpty()) {
                    String[] numerosEnLinea = linea.split("\\s+");
                    for (String num : numerosEnLinea) {
                        try {
                            // Intentar convertir solo si es un número válido
                            numeros.add(Integer.parseInt(num));
                        } catch (NumberFormatException e) {
                            // Ignorar cualquier cadena que no sea un número
                            System.out.println("Advertencia: No se puede convertir '" + num + "' a número.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tamaños de datos a ordenar
        int[] tamaños = {50, 100, 200, 300, 400, 500, 1000, 10000};

        // Procesar y ordenar los diferentes tamaños
        for (int tamaño : tamaños) {
            // Tomar solo los primeros 'tamaño' números
            List<Integer> subLista = numeros.subList(0, Math.min(tamaño, numeros.size()));

            // Medir el tiempo de ejecución
            long tiempoInicio = System.nanoTime();

            // Algoritmo de ordenamiento por burbuja
            for (int indiceFila = 0; indiceFila < subLista.size(); indiceFila++) {
                for (int indiceColumna = 0; indiceColumna < subLista.size() - 1 - indiceFila; indiceColumna++) {
                    if (subLista.get(indiceColumna) > subLista.get(indiceColumna + 1)) {
                        // Intercambiar
                        int temp = subLista.get(indiceColumna);
                        subLista.set(indiceColumna, subLista.get(indiceColumna + 1));
                        subLista.set(indiceColumna + 1, temp);
                    }
                }
            }

            long tiempoFin = System.nanoTime();
            long tiempoEjecucion = tiempoFin - tiempoInicio;

            // Convertir tiempo de nanosegundos a milisegundos
            double tiempoEjecucionEnMilisegundos = tiempoEjecucion / 1_000_000.0;

            // Imprimir los números ordenados y el tiempo de ejecución
            System.out.println("Primeros " + tamaño + " números ordenados:");
            for (int numero : subLista) {
                System.out.print(numero + " ");
            }
            System.out.println("\nTiempo de ejecución para ordenar " + tamaño + " números (en milisegundos): " + tiempoEjecucionEnMilisegundos);
            System.out.println("--------------------------------------------------------");
        }
    }
}