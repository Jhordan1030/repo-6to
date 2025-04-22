import random
import math
import time

# Función para verificar si un número es primo
def es_primo(numero):
    if numero <= 1:
        return False
    for divisor in range(2, int(math.sqrt(numero)) + 1):
        if numero % divisor == 0:
            return False
    return True

# Función para generar números aleatorios y encontrar los primos en la matriz
def encontrar_primos_en_matriz(matriz_numeros, num_filas, num_columnas):
    cantidad_primos = 0
    print("Números primos encontrados: ", end="")
    # Recorrer la matriz para buscar los números primos
    for fila in range(num_filas):
        for columna in range(num_columnas):
            if es_primo(matriz_numeros[fila][columna]):
                print(matriz_numeros[fila][columna], end=" ")  # Mostrar el número primo
                cantidad_primos += 1
    print()  # Salto de línea después de mostrar los primos
    print(f"\nCantidad de números primos encontrados: {cantidad_primos}")

def main():
    # Crear el generador aleatorio y leer entradas del usuario
    random_gen = random.Random()

    # Ingresar las dimensiones de la matriz
    num_filas = int(input("Ingresar el número de filas: "))
    num_columnas = int(input("Ingresar el número de columnas: "))

    # Crear la matriz de enteros de orden m x n
    matriz_numeros = [[random_gen.randint(0, 99) for _ in range(num_columnas)] for _ in range(num_filas)]

    # Imprimir la matriz generada
    print("Matriz generada:")
    for fila in matriz_numeros:
        print(fila)

    # Medir el tiempo de ejecución para encontrar los primos
    start_time = time.perf_counter()  # Inicio del tiempo (alta precisión)

    # Buscar los primos en la matriz
    encontrar_primos_en_matriz(matriz_numeros, num_filas, num_columnas)

    end_time = time.perf_counter()  # Fin del tiempo (alta precisión)

    # Calcular el tiempo transcurrido
    duration = (end_time - start_time) * 1_000_000_000  # Convertir a nanosegundos
    print(f"\nTiempo estimado para encontrar los números primos: {int(duration)} nanosegundos.")

if __name__ == "__main__":
    main()
