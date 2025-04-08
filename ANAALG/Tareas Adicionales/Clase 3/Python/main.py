import random

# Función para verificar si un número es primo
def es_primo(numero):
    if numero <= 1:
        return False
    for divisor in range(2, int(numero**0.5) + 1):
        if numero % divisor == 0:
            return False
    return True

# Función para generar números aleatorios y contar los primos
def contar_primos(arreglo_numeros):
    cantidad_primos = 0
    for numero in arreglo_numeros:
        if es_primo(numero):
            cantidad_primos += 1
    return cantidad_primos

# Función para calcular la suma de las filas y columnas de una matriz
def sumar_filas_y_columnas(matriz_numeros, tamaño_matriz):
    # Sumar filas
    for fila in range(tamaño_matriz):
        suma_fila = sum(matriz_numeros[fila])
        print(f"Suma de la fila {fila + 1}: {suma_fila}")

    # Sumar columnas
    for columna in range(tamaño_matriz):
        suma_columna = sum(matriz_numeros[fila][columna] for fila in range(tamaño_matriz))
        print(f"Suma de la columna {columna + 1}: {suma_columna}")

def main():
    # Parte 1: Ingresar arreglo de números aleatorios
    tamaño_arreglo = int(input("Ingresa el tamaño del arreglo: "))
    arreglo_numeros = [random.randint(0, 99) for _ in range(tamaño_arreglo)]

    print("Arreglo generado:")
    print(arreglo_numeros)

    # Contar los números primos
    cantidad_primos = contar_primos(arreglo_numeros)
    print(f"\nCantidad de números primos en el arreglo: {cantidad_primos}")

    # Parte 2: Crear y sumar las filas y columnas de una matriz
    tamaño_matriz = int(input("\nIngresa el tamaño de la matriz (n): "))
    matriz_numeros = [[random.randint(0, 99) for _ in range(tamaño_matriz)] for _ in range(tamaño_matriz)]

    print("Matriz generada:")
    for fila in matriz_numeros:
        print(fila)

    # Sumar las filas y columnas
    sumar_filas_y_columnas(matriz_numeros, tamaño_matriz)

if __name__ == "__main__":
    main()
