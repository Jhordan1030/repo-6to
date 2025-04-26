import java.util.Arrays;

public class Ordenador {
    public void ordenarColumnasDeMatriz(Matriz matriz) {
        char[][] contenido = matriz.obtenerContenido();
        int totalFilas = contenido.length;
        int totalColumnas = contenido[0].length;

        for (int columna = 0; columna < totalColumnas; columna++) {
            char[] columnaTemporal = new char[totalFilas];

            for (int fila = 0; fila < totalFilas; fila++) {
                columnaTemporal[fila] = contenido[fila][columna];
            }

            Arrays.sort(columnaTemporal);

            for (int fila = 0; fila < totalFilas; fila++) {
                contenido[fila][columna] = columnaTemporal[fila];
            }
        }

        matriz.actualizarContenido(contenido);
    }
}
