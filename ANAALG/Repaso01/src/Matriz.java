import java.util.Random;

public class Matriz {
    protected int filas, columnas;
    protected char[][] contenido;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.contenido = new char[filas][columnas];
        generarContenidoAleatorio();
    }

    private void generarContenidoAleatorio() {
        Random random = new Random();
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                contenido[fila][columna] = (char) (random.nextInt(26) + 'A');
            }
        }
    }

    public void imprimirMatriz() {
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                System.out.print(contenido[fila][columna] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public char[][] obtenerContenido() {
        return contenido;
    }

    public void actualizarContenido(char[][] nuevoContenido) {
        this.contenido = nuevoContenido;
    }
}
