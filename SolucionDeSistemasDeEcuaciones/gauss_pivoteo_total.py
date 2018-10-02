import pivoteo_total
import determinante
def gauss_pivoteo_total(A,b, marcas):
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
        j = k
        while j < n and A[j][k] == 0:
            j+= 1
        if j >= n:
            continue
        (A, b, marcas) = pivoteo_total.pivoteo_total(A, b, marcas, n, k)
        for i in range(k+1,n): #Si es cero significa que no se requiere realizar ninguna operacion en la fila
            if A[i][k] != 0:
                multiplicador = A[i][k]/A[k][k]
                for l in range(k,n):
                    A[i][l] = A[i][l] - multiplicador * A[k][l]
                b[i] = b[i] - multiplicador*b[k] 
    return (A,b, marcas)