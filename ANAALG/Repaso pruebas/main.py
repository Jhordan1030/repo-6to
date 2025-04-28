# Necesitarás instalar numpy si no lo tienes: pip install numpy
import numpy as np
import time
from fractions import Fraction
import math # No se usa activamente pero estaba en el original

# --- Funciones Auxiliares ---

# 1. Generador de números de Fibonacci (iterativo con caché)
_fib_cache = {1: 1, 2: 1}
def fibonacci(k):
    """Calcula el k-ésimo número de Fibonacci (Fk), con F1=1, F2=1."""
    if k <= 0:
        if k == 1: return 1 # Caso especial F(1)=1
        return 0
    if k in _fib_cache:
        return _fib_cache[k]
    if k == 1 or k == 2:
        return 1

    last_known_k = max(_fib_cache.keys()) if _fib_cache else 0
    # Si k ya está o es base, retorna
    if k <= last_known_k: return _fib_cache.get(k, 1) # Debería estar en cache

    # Calcular iterativamente desde el último conocido
    if last_known_k < 2: # Asegurar F(1) y F(2) si no están
        _fib_cache[1] = 1
        _fib_cache[2] = 1
        last_known_k = 2
        if k <= last_known_k: return _fib_cache[k]

    a, b = _fib_cache[last_known_k-1], _fib_cache[last_known_k]

    for i in range(last_known_k + 1, k + 1):
        a, b = b, a + b
        _fib_cache[i] = b
    return b

# 2. Función para calcular el k-ésimo término de la serie (Grupo 1)
def calculate_term_group1(k):
    """Calcula el k-ésimo término (fracción) para la serie del Grupo 1."""
    if k <= 0:
        return Fraction(0)

    numerator = fibonacci(k)
    denominator = 8 * (k - 1) - 1

    if denominator == 0:
        print(f"Advertencia: Denominador cero para k={k}")
        return Fraction(0) # O manejar como error

    return Fraction(numerator, denominator)

# 3. Función para llenar la matriz A (MODIFICADA CON ZIGZAG)
def fill_matrix(n, m):
    """
    Crea y llena una matriz n x m con términos de la serie Grupo 1
    en un patrón de zigzag comenzando desde la esquina inferior derecha.
    """
    # Crear una matriz numpy de objetos para almacenar Fracciones
    matrix = np.empty((n, m), dtype=object)
    term_index = 1 # Índice k para la serie, comienza en 1
    going_up = True # Empezamos subiendo por la última columna

    # Iterar por las columnas de derecha a izquierda
    for j in range(m - 1, -1, -1): # Column index from m-1 down to 0
        if going_up:
            # Llenar hacia arriba: filas desde n-1 hasta 0
            for i in range(n - 1, -1, -1): # Row index from n-1 down to 0
                if term_index <= n * m:
                    matrix[i, j] = calculate_term_group1(term_index)
                    term_index += 1
                else:
                     # Si hay más celdas que términos, llenar con 0 o None
                     matrix[i, j] = Fraction(0) # O None
        else:
            # Llenar hacia abajo: filas desde 0 hasta n-1
            for i in range(n): # Row index from 0 up to n-1
                 if term_index <= n * m:
                    matrix[i, j] = calculate_term_group1(term_index)
                    term_index += 1
                 else:
                     matrix[i, j] = Fraction(0) # O None

        # Cambiar la dirección para la siguiente columna
        going_up = not going_up

    return matrix

# --- Función Principal del Experimento ---
def run_experiment(n, m):
    """Realiza el llenado (zigzag), multiplicación y devuelve matrices."""
    # 1. Llenar la matriz A con el nuevo patrón
    matrix_a = fill_matrix(n, m)

    # 2. Calcular la transpuesta A^T
    matrix_a_t = matrix_a.T

    # 3. Multiplicar A * A^T
    try:
        # Intentar multiplicar directamente los objetos Fraction
        result_matrix = np.dot(matrix_a.astype(object), matrix_a_t.astype(object))
    except Exception as e:
        # Fallback o manejo de error si la multiplicación directa falla
        # Podría ser necesario convertir a float, perdiendo precisión
        print(f"Error multiplicando objetos Fraction: {e}. Intentando con floats.")
        try:
            matrix_a_float = matrix_a.astype(float)
            matrix_a_t_float = matrix_a_t.astype(float)
            result_matrix = np.dot(matrix_a_float, matrix_a_t_float)
        except Exception as e_float:
             print(f"Error multiplicando como float: {e_float}")
             result_matrix = None # Indicar fallo

    return matrix_a, result_matrix

# --- Ejecución y Medición de Tiempos ---
if __name__ == "__main__":
    # 1. Obtener dimensiones del usuario
    while True:
        try:
            n = int(input("Introduce el número de filas (n): "))
            m = int(input("Introduce el número de columnas (m): "))
            if n > 0 and m > 0:
                break
            else:
                print("n y m deben ser enteros positivos.")
        except ValueError:
            print("Entrada inválida. Introduce números enteros.")

    print(f"\nEjecutando experimento para matriz {n}x{m} (Grupo 1, Llenado Zigzag)...")

    execution_times = []
    matrix_a_example = None
    result_matrix_example = None

    # 2. Bucle de 10 ejecuciones para medir tiempos
    for i in range(10):
        print(f"--- Ejecución {i+1}/10 ---")
        start_time = time.perf_counter()

        # Ejecutar el experimento con llenado zigzag
        matrix_a, result_matrix = run_experiment(n, m)

        end_time = time.perf_counter()
        duration = end_time - start_time
        execution_times.append(duration)
        print(f"Tiempo de ejecución: {duration:.6f} segundos")

        # Guardar las matrices de la primera ejecución para mostrarlas al final
        if i == 0:
            matrix_a_example = matrix_a
            result_matrix_example = result_matrix

    # 3. Mostrar resultados finales
    print("\n--- Resultados Finales ---")

    print("\nMatriz A (llenado zigzag, primera ejecución):")
    if matrix_a_example is not None:
        # Imprimir de forma legible
        for row in matrix_a_example:
             print([str(f) for f in row]) # Muestra fracciones como 'num/den'
    else:
        print("No se pudo generar la Matriz A.")


    print(f"\nResultado de la multiplicación A * A^T (matriz {n}x{n}, primera ejecución):")
    if result_matrix_example is not None:
        # Decide cómo imprimir el resultado (puede ser float si Fraction falló)
        if isinstance(result_matrix_example[0,0], Fraction):
             for row in result_matrix_example:
                 print([str(f) for f in row])
        else: # Probablemente numpy array de floats
             print(result_matrix_example)

    else:
        print("No se pudo calcular la multiplicación.")



    print(f"\nTiempo promedio: {np.mean(execution_times):.6f} segundos")


