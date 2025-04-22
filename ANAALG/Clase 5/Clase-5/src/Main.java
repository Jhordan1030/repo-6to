public class Main {
    public static void main(String[] args) {
        int[] arreglo = generarArregloAleatorio(100); // Puedes cambiar el tamaño

        long tiempoInicio = System.nanoTime();

        insertionSort(arreglo);

        long tiempoFin = System.nanoTime();

        long tiempoEjecucion = tiempoFin - tiempoInicio;
        System.out.println("Tiempo de ejecución en nanosegundos: " + tiempoEjecucion);
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempoEjecucion / 1_000_000.0);
    }
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int actual = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > actual) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = actual;
        }
    }

    public static int[] generarArregloAleatorio(int tamaño) {
        int[] arreglo = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = (int)(Math.random() * 10000);
        }
        return arreglo;
    }
}