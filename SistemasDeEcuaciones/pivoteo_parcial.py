def PivoteoParcial(Ab, n, k):
    mayor = abs(Ab[k][k])
    filaMayor = k
    for s in range(k + 1, n):
        if A[s][k] > mayor:
            mayor = abs(A[s][k])
            filaMayor = s
    if mayor == 0:
        print("El sistema no tiene solución unica")
    else:
        if filaMayor != k:
            Ab = IntercambieFilas(Ab, filaMayor, k)
        return Ab

