import determinante #Codigo propio

def escalonamiento(A,b):
    n = len(A) #Calculo el numero de filas de A
    if n != len(A[0]): #Revisa si el vector es cuadrado
        print("La matriz no es cuadrada")
        return
    if len(b) != n: #Revisa que el numero de filas de la matriz sea igual al del arreglo de terminos independientes
        print("Hay diferente numero de terminos independientes e incognitas")
        return
    if determinante.det(A,n) == 0: #Revisa que la matriz es invertible     
        print("La matriz es no invertible")
        return

    for k in range(0,n-1):
        if A[k][k] == 0: #Evitar division por cero al encontrar el multuplicador
            j = k+1
            while j<n and A[j][k] == 0: #Buscar un fila donde el valor en la columna K sea difernte de 0
                j= j+1
            if j<n: #Sumarle a la fila K otra donde quite el cero de la columna K
                for l in range(k,n):
                    A[k][l] = A[k][l] + A[j][l]
                b[k] = b[k] + b[j] 
            else: #Si no hay valor que quite el 0 de la fila K significa que esta escalonada
                continue
        for i in range(k+1,n): #Si es cero significa que no se requiere realizar ninguna operacion en la fila
            if A[i][k] != 0:
                multiplicador = A[i][k]/A[k][k]
                for l in range(k,n):
                    A[i][l] = A[i][l] - multiplicador * A[k][l]
                b[i] = b[i] - multiplicador*b[k] 

    return (A,b)
