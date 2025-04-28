package Inicial;

public class Ordenamiento {

    public static void ordenarColumnaConMergeSort(Matriz matriz, int columna) {
        int filas = matriz.getFilas();
        char[] columnaArray = matriz.getColumna(columna);

        // Aplicar Merge Sort a la columna
        mergeSort(columnaArray);

        // Insertar los valores ordenados de vuelta en la matriz
        matriz.setColumna(columna, columnaArray);
    }

    // Método para realizar el ordenamiento por mezcla (Merge Sort)
    public static void mergeSort(char[] array) {
        if (array.length < 2) {
            return;
        }
        int mitad = array.length / 2;
        char[] izquierda = new char[mitad];
        char[] derecha = new char[array.length - mitad];

        System.arraycopy(array, 0, izquierda, 0, mitad);
        System.arraycopy(array, mitad, derecha, 0, array.length - mitad);

        mergeSort(izquierda);
        mergeSort(derecha);
        merge(array, izquierda, derecha);
    }

    // Método para combinar dos arrays en uno ordenado
    private static void merge(char[] array, char[] izquierda, char[] derecha) {
        int izquierdaIndice = 0, derechaIndice = 0, arrayIndice = 0;
        while (izquierdaIndice < izquierda.length && derechaIndice < derecha.length) {
            if (izquierda[izquierdaIndice] > derecha[derechaIndice]) {
                array[arrayIndice++] = izquierda[izquierdaIndice++];
            } else {
                array[arrayIndice++] = derecha[derechaIndice++];
            }
        }
        while (izquierdaIndice < izquierda.length) {
            array[arrayIndice++] = izquierda[izquierdaIndice++];
        }
        while (derechaIndice < derecha.length) {
            array[arrayIndice++] = derecha[derechaIndice++];
        }
    }
}
