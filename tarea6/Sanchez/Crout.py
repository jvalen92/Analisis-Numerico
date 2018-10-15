import numpy as np
def conseguirL(A,L,U,i,j):
    suma = A[i][j]
    for numero in range(j):
        suma -= L[i][numero] * U[numero][j] 
    return suma

def conseguirU(A,L,U,i,j):
    u = A[i][j]
    for numero in range(i):
        u -= L[i][numero]*U[numero][j]
    return u/L[i][i]

def crout(A,b):
    A = np.array(A)
    if A.shape[0] != A.shape[1]:
        print("Matriz no cuadrada: ")
        return
    n = A.shape[0]
    #Crear la matriz L y u
    L = np.zeros(A.shape)
    U = np.identity(n)
    for i in range(n):
        for j in range(i,n):
            L[j][i] = conseguirL(A,L,U,j,i)
        for l in range(i+1,n):
            U[i][l] = conseguirU(A,L,U,i,l)

    print("A= ",A)
    print("L= ",L)
    print("U= ",U)
