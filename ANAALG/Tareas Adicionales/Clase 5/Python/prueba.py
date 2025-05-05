import time # Importar si quieres medir tiempo, aunque no es parte del requisito original del PDF

def llenar_matriz(n, m):
    """
    Llena una matriz de n x m con enteros desde 1 hasta n*m en un patrón espiral
    en sentido antihorario como en el ejemplo del PDF (inicia abajo-derecha,
    va hacia ARRIBA, luego IZQUIERDA, luego ABAJO, luego DERECHA, etc.).

    Args:
        n: Número de filas.
        m: Número de columnas.

    Returns:
        Una lista de listas que representa la matriz llena.
        Devuelve una lista vacía si n o m son <= 0.
    """
    if n <= 0 or m <= 0:
        print("Error: Las dimensiones deben ser números positivos.")
        return []

    matriz = [[0] * m for _ in range(n)]
    num = 1
    fila, col = n - 1, m - 1  # Empezar en la esquina inferior derecha
    arriba, abajo = 0, n - 1
    izquierda, derecha = 0, m - 1
    # Direcciones: 0: arriba, 1: izquierda, 2: abajo, 3: derecha
    direccion = 0

    # Caso especial para matriz 1x1
    if n == 1 and m == 1:
      matriz[0][0] = 1
      return matriz

    while num <= n * m:
        # Colocar el número solo si la celda está vacía (importante para espirales)
        if 0 <= fila < n and 0 <= col < m and matriz[fila][col] == 0:
             matriz[fila][col] = num
             num += 1
        else:
             # Si la celda actual ya estaba llena (puede pasar al cambiar de dirección
             # en ciertas dimensiones), o si nos salimos de los límites momentáneamente
             # antes de corregir, simplemente rompemos el bucle si ya llenamos todo.
             # O si num ya superó n*m, también salimos.
             if num > n * m:
                  break
             # Podríamos necesitar ajustar la posición si el cambio de dirección nos dejó mal
             # Pero la lógica de cambio de dirección debería manejar esto.
             # Considerar si el bucle debe ser `while arriba <= abajo and izquierda <= derecha:`

        # Calcular siguiente movimiento potencial y verificar límites/dirección
        if direccion == 0:  # Moviendo hacia arriba
            if fila > arriba:
                fila -= 1
            else: # Choca con el borde superior o la fila ya llena
                direccion = 1 # Cambiar a izquierda
                col -= 1 # Mover a la izquierda
                # Ya no se ajusta 'derecha -= 1' aquí, los límites definen el espacio vacío
        elif direccion == 1: # Moviendo hacia la izquierda
            if col > izquierda:
                col -= 1
            else: # Choca con el borde izquierdo o la columna ya llena
                direccion = 2 # Cambiar a abajo
                fila += 1 # Mover hacia abajo
                # Ya no se ajusta 'arriba += 1' aquí
        elif direccion == 2: # Moviendo hacia abajo
            if fila < abajo:
                fila += 1
            else: # Choca con el borde inferior o la fila ya llena
                direccion = 3 # Cambiar a derecha
                col += 1 # Mover a la derecha
                # Ya no se ajusta 'izquierda += 1' aquí
        elif direccion == 3: # Moviendo hacia la derecha
            if col < derecha:
                col += 1
            else: # Choca con el borde derecho o la columna ya llena
                direccion = 0 # Cambiar a arriba
                fila -= 1 # Mover hacia arriba
                # Ya no se ajusta 'abajo -= 1' aquí

        # Ajustar límites después de completar una vuelta de espiral
        # Esto es crucial y faltaba en la versión anterior.
        # Se ajusta el límite DESPUÉS de que la primera celda de la nueva línea se llena.
        if matriz[fila][col] != 0: # Asegurarse de que la celda se llenó
            if direccion == 1 and col == izquierda: # Si acabamos de movernos a la izquierda hasta el límite
                 arriba += 1 # La fila superior ya está completa, mover límite hacia abajo
            elif direccion == 2 and fila == abajo: # Si acabamos de movernos hacia abajo hasta el límite
                 izquierda += 1 # La columna izquierda ya está completa, mover límite hacia la derecha
            elif direccion == 3 and col == derecha: # Si acabamos de movernos a la derecha hasta el límite
                 abajo -= 1 # La fila inferior ya está completa, mover límite hacia arriba
            elif direccion == 0 and fila == arriba: # Si acabamos de movernos hacia arriba hasta el límite
                 derecha -= 1 # La columna derecha ya está completa, mover límite hacia la izquierda


        # Salvaguarda por si n*m es muy grande o hay un error lógico
        # y 'num' se excede antes de llenar la matriz (no debería pasar)
        # O si los límites se cruzan (indica que se llenó)
        if num > n * m or arriba > abajo or izquierda > derecha:
            break

    return matriz


def imprimir_matriz(matriz):
    """Imprime la matriz de forma formateada."""
    if not matriz:
        # El mensaje de error ya se dio en llenar_matriz si las dimensiones eran inválidas
        # print("Matriz vacía.")
        return
    ancho_max = 0
    for fila in matriz:
        for item in fila:
            ancho = len(str(item))
            if ancho > ancho_max:
                ancho_max = ancho

    print("\nMatriz generada:")
    for fila in matriz:
        print(" ".join(str(item).rjust(ancho_max) for item in fila))

# --- Algoritmos de Ordenamiento (Sin cambios) ---

def ordenamiento_seleccion(arr):
    """Ordena un arreglo usando Ordenamiento por Selección."""
    n = len(arr)
    for i in range(n):
        min_idx = i
        for j in range(i+1, n):
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr

def ordenamiento_insercion(arr):
    """Ordena un arreglo usando Ordenamiento por Inserción."""
    for i in range(1, len(arr)):
        clave = arr[i]
        j = i-1
        while j >=0 and clave < arr[j] :
                arr[j+1] = arr[j]
                j -= 1
        arr[j+1] = clave
    return arr

def ordenamiento_shell(arr):
    """Ordena un arreglo usando Ordenamiento Shell."""
    n = len(arr)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2
    return arr


def ordenar_fila_matriz(matriz, indice_fila, numero_grupo):
    """
    Ordena una fila específica de la matriz según el número de grupo.
    Modifica la matriz in-place.
    """
    if not matriz or not (0 <= indice_fila < len(matriz)):
        print(f"Error: Índice de fila inválido {indice_fila}.")
        return

    # Crear una copia de la fila para no afectar la matriz original directamente
    # si la función de ordenamiento no modifica in-place, aunque estas sí lo hacen.
    # Es más seguro trabajar con la referencia directa si se modifica in-place.
    fila_a_ordenar = matriz[indice_fila] # Referencia a la fila

    print(f"\nFila original {indice_fila}: {list(fila_a_ordenar)}") # Imprimir como lista para verla clara

    if numero_grupo == 1:
        print("Ordenando usando Ordenamiento por Selección...")
        ordenamiento_seleccion(fila_a_ordenar)
    elif numero_grupo == 2:
        print("Ordenando usando Ordenamiento por Inserción...")
        ordenamiento_insercion(fila_a_ordenar)
    elif numero_grupo == 3:
        print("Ordenando usando Ordenamiento Shell...")
        ordenamiento_shell(fila_a_ordenar)
    else:
        print(f"Error: Número de grupo inválido {numero_grupo}. No se aplicó ordenamiento.")
        return # No imprimir si no se ordenó

    print(f"Fila ordenada {indice_fila}:   {list(fila_a_ordenar)}")


# --- Bloque Principal de Ejecución ---
if __name__ == "__main__":
    try:
        n_filas = int(input("Ingrese el número de filas (n): "))
        m_columnas = int(input("Ingrese el número de columnas (m): "))
    except ValueError:
        print("Entrada inválida para dimensiones. Terminando.")
        exit() # Salir si las dimensiones no son válidas

    # Llenar la matriz con la lógica corregida
    matriz_llena = llenar_matriz(n_filas, m_columnas)

    # Imprimir la matriz original si se generó correctamente
    if matriz_llena:
        imprimir_matriz(matriz_llena)

        # Obtener índice de fila y número de grupo para ordenar
        try:
            indice_fila_ordenar = int(input(f"\nIngrese el índice de la fila a ordenar (0 a {n_filas-1}): "))
            if not (0 <= indice_fila_ordenar < n_filas):
                 raise ValueError("Índice de fila fuera de rango")

            grupo_num = int(input("Ingrese el número de grupo (1, 2 o 3) para el método de ordenamiento: "))
            if grupo_num not in [1, 2, 3]:
                 raise ValueError("Número de grupo debe ser 1, 2 o 3")

            # Ordenar la fila especificada
            ordenar_fila_matriz(matriz_llena, indice_fila_ordenar, grupo_num)

            # Imprimir la matriz nuevamente para mostrar la fila ordenada
            print("\nMatriz después de ordenar la fila:")
            imprimir_matriz(matriz_llena)

        except ValueError as e:
            print(f"Entrada inválida ({e}). No se ordenará la fila.")
        except Exception as e:
            print(f"Ocurrió un error inesperado: {e}")