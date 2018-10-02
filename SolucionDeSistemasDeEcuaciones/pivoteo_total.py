def pivoteo_total(A, b, marcas, n, k):
    mayor = 0
    filaMayor = k
    columnaMayor = k
    for r in range(k, n):
        for s in range(k, n):
            if abs(A[r][s]) > mayor:
                mayor = abs(A[r][s])
                filaMayor = r
                columnaMayor = s
    if mayor == 0:
        print("El sistema no tiene solución unica")
    else:
        if filaMayor != k:
            A = intercambiar(A, filaMayor, k)
            b = intercambiar(b, filaMayor, k)
        if columnaMayor != k:
            A = intercol(A, columnaMayor, k)
            marcas = intercambiar(marcas, columnaMayor, k)
        return (A, b, marcas)
def intercambiar(M, i, j):
    temp = M[i]
    M[i] = M[j]
    M[j] = temp
    return M
def intercol(M, i, j):
    for k in range(0, len(M)):
        temp = M[k][i]
        M[k][i] = M[k][j]
        M[k][j] = temp
    return M