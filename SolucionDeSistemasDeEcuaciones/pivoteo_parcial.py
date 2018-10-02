def pivoteo_parcial(A, b, n, k):
    mayor = abs(A[k][k])
    filaMayor = k
    for s in range(k + 1, n):
        if abs(A[s][k]) > mayor:
            mayor = abs(A[s][k])
            filaMayor = s
    if mayor == 0:
        print("El sistema no tiene solución única")
    else:
        if filaMayor != k:
            A = intercambiar(A, filaMayor, k)
            b = intercambiar(b, filaMayor, k)
        return (A, b)

def intercambiar(M, i, j):
    temp = M[i]
    M[i] = M[j]
    M[j] = temp
    return M