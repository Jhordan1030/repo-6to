import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el número de filas (n): ");
            int filas = scanner.nextInt();

            System.out.print("Ingrese el número de columnas (m): ");
            int columnas = scanner.nextInt();

            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese la palabra a buscar: ");
            String palabra = scanner.nextLine().trim();

            if (filas <= 0 || columnas <= 0) {
                throw new IllegalArgumentException("Las dimensiones deben ser positivas");
            }
            if (!palabra.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("La palabra debe contener solo letras");
            }

            // Crear matriz original
            Matriz matrizOriginal = new Matriz(filas, columnas);
            System.out.println("\nMatriz Original:");
            matrizOriginal.imprimir();

            // Buscar palabra
            List<ResultadoBusqueda> resultados = matrizOriginal.buscarPalabra(palabra);

            if (resultados.isEmpty()) {
                System.out.println("La palabra '" + palabra + "' no se encontró en la matriz.");
            } else {
                System.out.println("La palabra '" + palabra + "' se encontró en las siguientes posiciones:");
                for (ResultadoBusqueda resultado : resultados) {
                    System.out.println("- Dirección: " + resultado.direccion);
                    System.out.print("  Posiciones: ");
                    for (int i = 0; i < resultado.posiciones.size(); i++) {
                        Coordenada coord = resultado.posiciones.get(i);
                        System.out.print("(" + coord.fila + "," + coord.columna + ")");
                        if (i < resultado.posiciones.size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }
            }

            // Ordenar columnas
            Matriz matrizOrdenada = matrizOriginal.ordenarColumnas();
            System.out.println("\nMatriz Ordenada (columnas ordenadas ascendentemente):");
            matrizOrdenada.imprimir();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class Coordenada {
    int fila;
    int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
}

class ResultadoBusqueda {
    List<Coordenada> posiciones;
    String direccion;

    public ResultadoBusqueda(List<Coordenada> posiciones, String direccion) {
        this.posiciones = posiciones;
        this.direccion = direccion;
    }
}

class Matriz {
    protected int filas;
    protected int columnas;
    protected char[][] matriz;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = generarMatrizAleatoria();
    }

    protected char[][] generarMatrizAleatoria() {
        Random random = new Random();
        char[][] matriz = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (char)(random.nextInt(26) + 'a');
            }
        }
        return matriz;
    }

    public List<ResultadoBusqueda> buscarPalabra(String palabra) {
        List<ResultadoBusqueda> resultados = new ArrayList<>();
        palabra = palabra.toLowerCase();

        int[][] direcciones = {
                {0, 1},   // derecha
                {0, -1},  // izquierda
                {1, 0},   // abajo
                {-1, 0},   // arriba
                {-1, 1},   // diagonal superior derecha
                {-1, -1},  // diagonal superior izquierda
                {1, 1},    // diagonal inferior derecha
                {1, -1}    // diagonal inferior izquierda
        };

        String[] nombresDirecciones = {
                "derecha", "izquierda", "abajo", "arriba",
                "diagonal superior derecha", "diagonal superior izquierda",
                "diagonal inferior derecha", "diagonal inferior izquierda"
        };

        char primeraLetra = palabra.charAt(0);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Character.toLowerCase(matriz[i][j]) == primeraLetra) {
                    for (int d = 0; d < direcciones.length; d++) {
                        if (verificarDireccion(palabra, i, j, direcciones[d][0], direcciones[d][1])) {
                            List<Coordenada> posiciones = obtenerPosiciones(
                                    palabra.length(), i, j, direcciones[d][0], direcciones[d][1]);
                            resultados.add(new ResultadoBusqueda(posiciones, nombresDirecciones[d]));
                        }
                    }
                }
            }
        }

        return resultados;
    }

    private boolean verificarDireccion(String palabra, int filaInicio, int columnaInicio, int dirFila, int dirColumna) {
        for (int k = 1; k < palabra.length(); k++) {
            int nuevaFila = filaInicio + k * dirFila;
            int nuevaColumna = columnaInicio + k * dirColumna;

            if (nuevaFila < 0 || nuevaFila >= filas || nuevaColumna < 0 || nuevaColumna >= columnas) {
                return false;
            }

            if (Character.toLowerCase(matriz[nuevaFila][nuevaColumna]) != palabra.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    private List<Coordenada> obtenerPosiciones(int longitud, int filaInicio, int columnaInicio, int dirFila, int dirColumna) {
        List<Coordenada> posiciones = new ArrayList<>();
        for (int k = 0; k < longitud; k++) {
            posiciones.add(new Coordenada(
                    filaInicio + k * dirFila,
                    columnaInicio + k * dirColumna
            ));
        }
        return posiciones;
    }

    public Matriz ordenarColumnas() {
        MatrizOrdenada matrizOrdenada = new MatrizOrdenada(filas, columnas);

        for (int j = 0; j < columnas; j++) {
            List<Character> columna = new ArrayList<>();
            for (int i = 0; i < filas; i++) {
                columna.add(matriz[i][j]);
            }

            Collections.sort(columna, (a, b) -> Character.compare(
                    Character.toLowerCase(a),
                    Character.toLowerCase(b)));

            for (int i = 0; i < filas; i++) {
                matrizOrdenada.matriz[i][j] = columna.get(i);
            }
        }

        return matrizOrdenada;
    }

    public void imprimir() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class MatrizOrdenada extends Matriz {
    public MatrizOrdenada(int filas, int columnas) {
        super(filas, columnas);
        this.matriz = new char[filas][columnas];
    }

    @Override
    public void imprimir() {
        super.imprimir();
    }
}