import random
import time


class MatrizNumeros:
    def __init__(self, filas, columnas):
        self.filas = filas
        self.columnas = columnas
        self.matriz = [[0 for _ in range(columnas)] for _ in range(filas)]

    # Método para llenar la matriz con números aleatorios en un rango
    def generar_numeros_aleatorios(self, minimo, maximo):
        for fila in range(self.filas):
            for columna in range(self.columnas):
                self.matriz[fila][columna] = random.randint(minimo, maximo)

    # Método para imprimir la matriz
    def imprimir_matriz(self):
        for fila in self.matriz:
            for numero in fila:
                print(f"{numero:4}", end="")
            print()

    # Método para buscar y mostrar los números primos en la matriz
    def buscar_numeros_primos(self):
        print("\nNúmeros primos encontrados:")
        for fila in range(self.filas):
            for columna in range(self.columnas):
                if self.es_primo(self.matriz[fila][columna]):
                    print(f"Primo {self.matriz[fila][columna]} encontrado en [{fila}][{columna}]")

    # Método para verificar si un número es primo
    def es_primo(self, numero):
        if numero <= 1:
            return False
        if numero == 2:
            return True
        if numero % 2 == 0:
            return False
        raiz = int(numero ** 0.5)
        for divisor in range(3, raiz + 1, 2):
            if numero % divisor == 0:
                return False
        return True


# Función principal
def main():
    # Obtener tamaño de la matriz
    filas = int(input("Ingrese el número de filas: "))
    columnas = int(input("Ingrese el número de columnas: "))

    matriz = MatrizNumeros(filas, columnas)
    matriz.generar_numeros_aleatorios(1, 100)  # Generar números entre 1 y 100

    print("\nMatriz generada:")
    matriz.imprimir_matriz()

    inicio_tiempo = time.time()
    matriz.buscar_numeros_primos()
    fin_tiempo = time.time()

    # Convertir el tiempo a segundos
    tiempo_segundos = fin_tiempo - inicio_tiempo
    print(f"\nTiempo estimado para encontrar primos: {tiempo_segundos:.4f} segundos")


if __name__ == "__main__":
    main()
