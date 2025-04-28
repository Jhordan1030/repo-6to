package Inicial;

import java.util.ArrayList;

public class Busqueda {

    public static ArrayList<String> buscarPalabras(Matriz matriz, String palabra) {
        ArrayList<String> posiciones = new ArrayList<>();
        int contador = 0;
        char[][] matrizArray = matriz.getMatriz();
        int filas = matriz.getFilas();
        int columnas = matriz.getColumnas();

        // Convertir la palabra a minúsculas para hacer una búsqueda insensible a mayúsculas
        palabra = palabra.toLowerCase();

        // Buscar en las filas
        for (int fila = 0; fila < filas; fila++) {
            String filaTexto = new String(matrizArray[fila]).toLowerCase(); // Convertir la fila a minúsculas
            int indice = filaTexto.indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + fila + "," + (indice + cont) + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = filaTexto.indexOf(palabra, indice + 1);
            }

            // Buscar de derecha a izquierda (invertir la fila)
            String filaTextoInvertida = new StringBuilder(filaTexto).reverse().toString();
            indice = filaTextoInvertida.indexOf(palabra);
            while (indice != -1) {

                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + fila + "," + (columnas - 1 - (indice + cont)) + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = filaTextoInvertida.indexOf(palabra, indice + 1);
            }
        }

        // Buscar en las columnas
        for (int columna = 0; columna < columnas; columna++) {
            StringBuilder columnaTexto = new StringBuilder();
            for (int fila = 0; fila < filas; fila++) {
                columnaTexto.append(matrizArray[fila][columna]);
            }
            String columnaString = columnaTexto.toString().toLowerCase();
            int indice = columnaString.indexOf(palabra);
            while (indice != -1) {

                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (indice + cont) + "," + columna + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = columnaString.indexOf(palabra, indice + 1); // Buscar la siguiente ocurrencia
            }

            // Buscar de abajo hacia arriba (invertir la columna)
            String columnaStringInvertida = new StringBuilder(columnaString).reverse().toString();
            indice = columnaStringInvertida.indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (filas - 1 - (indice + cont)) + "," + columna + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = columnaStringInvertida.indexOf(palabra, indice + 1); // Buscar la siguiente ocurrencia
            }
        }

        // Buscar en las diagonales (de arriba a abajo)
        for (int fila = 0; fila < filas; fila++) {
            StringBuilder diagonal1 = new StringBuilder();
            StringBuilder diagonal2 = new StringBuilder();
            for (int columna = 0; columna < columnas; columna++) {
                if (fila + columna < filas && columna < columnas) {
                    diagonal1.append(matrizArray[fila + columna][columna]);
                    diagonal2.append(matrizArray[fila + columna][columnas - 1 - columna]);
                }
            }
            // Buscar en diagonal 1
            String diagonal1String = diagonal1.toString().toLowerCase(); // Convertir la diagonal 1 a minúsculas
            int indice = diagonal1String.indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (fila + indice + cont) + "," + (indice + cont) + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = diagonal1String.indexOf(palabra, indice + 1);
            }

            // Buscar en diagonal 2
            String diagonal2String = diagonal2.toString().toLowerCase(); // Convertir la diagonal 2 a minúsculas
            indice = diagonal2String.indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (fila + indice + cont) + "," + (columnas - 1 - (indice + cont)) + ") ");
                }
                posiciones.add("Posición " + (contador + 1) + ": " + posicionesLetras.toString().trim());
                contador++;
                indice = diagonal2String.indexOf(palabra, indice + 1);
            }

            // Buscar en diagonal 1 de abajo hacia arriba
            StringBuilder diagonal1Invertida = new StringBuilder(diagonal1.toString()).reverse();
            indice = diagonal1Invertida.toString().indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (fila + indice + cont) + "," + (indice + cont) + ") ");
                }
                posiciones.add("Diagonal 1 invertida, Comienza en Fila " + (fila + indice) + ", Columna " + (indice));
                contador++;
                indice = diagonal1Invertida.toString().indexOf(palabra, indice + 1);
            }

            // Buscar en diagonal 2 de abajo hacia arriba
            StringBuilder diagonal2Invertida = new StringBuilder(diagonal2.toString()).reverse();
            indice = diagonal2Invertida.toString().indexOf(palabra);
            while (indice != -1) {
                // Al encontrar la palabra, recorremos sus letras y almacenamos sus posiciones
                StringBuilder posicionesLetras = new StringBuilder();
                for (int cont = 0; cont < palabra.length(); cont++) {
                    posicionesLetras.append("(" + (fila + indice + cont) + "," + (columnas - 1 - (indice + cont)) + ") ");
                }
                posiciones.add("Diagonal 2 invertida, Comienza en Fila " + (fila + indice) + ", Columna " + (columnas - 1 - indice));

                contador++;
                indice = diagonal2Invertida.toString().indexOf(palabra, indice + 1);
            }
        }

        // Mostrar cuántas veces se encontró la palabra
        System.out.println("\nLa palabra \"" + palabra + "\" se encontró " + contador + " vez/veces.");

        return posiciones;
    }
}
