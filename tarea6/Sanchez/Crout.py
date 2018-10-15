import numpy as np

def solucionar_regr(A, b):
    n = len(A)-1
    x = np.zeros(n+1,dtype=float)
    x[n] = b[n]/A[n][n]
    for i in range(n-1,-1,-1):
        sumatoria = 0
        for p in range(i+1,n+1):
            sumatoria = sumatoria + A[i][p]*x[p]
        x[i] = (b[i] - sumatoria)/A[i][i]
    return x
    
def solucionar_prog(A, b):
    n = len(A)-1
    x = np.zeros(n+1,dtype=float)
    x[0] = b[0]/A[0][0]
    for i in range(1, n + 1):
        sumatoria = 0
        for p in range(0,i):
            sumatoria = sumatoria + A[i][p]*x[p]
        x[i] = (b[i] - sumatoria)/A[i][i]
    return x

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
    z = solucionar_prog(L, b)
    x = solucionar_regr(U, z)
    print("z= ",z)
    print("x= ",x)

crout([[36,3,-4,5],[5,-45,10,-2],[6,8,57,5],[2,3,-8,-42]],[-20,69,96,-32])