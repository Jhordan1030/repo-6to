import time

# Leer los números desde el archivo
numeros = []
with open("multiple_data_1_to_1000_corrected.txt", "r") as archivo:
    for linea in archivo:
        # Solo leer las líneas con números (ignorando encabezados)
        if not linea.startswith("Data Size"):
            numeros.extend(map(int, linea.split()))

# Tamaños de datos a ordenar
tamaños = [50, 100, 200, 300, 400, 500, 1000, 10000]

# Procesar y ordenar los diferentes tamaños
for tamaño in tamaños:
    # Tomar solo los primeros 'tamaño' números
    sub_lista = numeros[:tamaño]

    # Medir el tiempo de ejecución
    inicio = time.time()

    # Algoritmo de ordenamiento por burbuja
    for indiceFila in range(len(sub_lista)):
        for indiceColumna in range(len(sub_lista) - 1 - indiceFila):
            if sub_lista[indiceColumna] > sub_lista[indiceColumna + 1]:
                # Intercambiar
                sub_lista[indiceColumna], sub_lista[indiceColumna + 1] = sub_lista[indiceColumna + 1], sub_lista[indiceColumna]

    fin = time.time()
    tiempo_ejecucion = fin - inicio

    # Convertir tiempo de segundos a milisegundos
    tiempo_ejecucion_en_milisegundos = tiempo_ejecucion * 1000

    # Imprimir los números ordenados y el tiempo de ejecución
    print(f"Primeros {tamaño} números ordenados:")
    print(sub_lista)
    print(f"Tiempo de ejecución para ordenar {tamaño} números (en milisegundos): {tiempo_ejecucion_en_milisegundos:.6f}")
    print("--------------------------------------------------------")
