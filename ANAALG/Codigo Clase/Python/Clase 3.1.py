import math
import time


# Función para calcular la serie alternante
def calcular_serie(x, n_terms):
    resultado = 0
    for n in range(n_terms):
        # Calcular el término de la serie
        termino = (x ** n) / math.factorial(n)

        # Si n es impar, restar el término (signo alternante)
        if n % 2 != 0:
            resultado -= termino
        else:
            resultado += termino
    return resultado


# Datos para evaluar
valores_x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]  # 10 valores de ejemplo

# Medir el tiempo de ejecución para los 10 valores de x
for x in valores_x:
    inicio = time.time()

    # Calcular la serie para el valor de x y 10 términos
    resultado = calcular_serie(x, 10)

    fin = time.time()
    tiempo_ejecucion = (fin - inicio) * 1000  # Tiempo en milisegundos

    # Imprimir el resultado y el tiempo de ejecución
    print(f"Resultado para x = {x}: {resultado}")
    print(f"Tiempo de ejecución para x = {x}: {tiempo_ejecucion:.6f} ms\n")
