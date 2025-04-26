public class Buscador  extends Matriz{

    public Buscador(int filas, int columnas) {
        super(filas, columnas);
    }

    public String[] buscarPalabra(String palabra) {
        int[][] direcciones = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        String[] posicionesEncontradas = new String[filas * columnas];
        int contadorPosiciones = 0;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (esInicioDePalabra(fila, columna, palabra, direcciones)) {
                    posicionesEncontradas[contadorPosiciones] = "(" + fila + "," + columna + ")";
                    contadorPosiciones++;
                }
            }
        }

        String[] resultados = new String[contadorPosiciones];
        for (int indice = 0; indice < contadorPosiciones; indice++) {
            resultados[indice] = posicionesEncontradas[indice];
        }
        return resultados;
    }

    private boolean esInicioDePalabra(int fila, int columna, String palabra, int[][] direcciones) {
        for (int[] direccion : direcciones) {
            int deltaFila = direccion[0];
            int deltaColumna = direccion[1];

            if (buscarEnDireccion(fila, columna, deltaFila, deltaColumna, palabra)) {
                return true; // Si encuentra en alguna dirección, ya no busca más
            }
        }
        return false;
    }

    private boolean buscarEnDireccion(int filaInicio, int columnaInicio, int deltaFila, int deltaColumna, String palabra) {
        int filaActual = filaInicio;
        int columnaActual = columnaInicio;

        for (int posicion = 0; posicion < palabra.length(); posicion++) {
            if (filaActual < 0 || columnaActual < 0 || filaActual >= filas || columnaActual >= columnas) {
                return false;
            }
            if (contenido[filaActual][columnaActual] != palabra.charAt(posicion)) {
                return false;
            }
            filaActual += deltaFila;
            columnaActual += deltaColumna;
        }
        return true;
    }
}
