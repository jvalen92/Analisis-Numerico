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
A = [
    [9.1622,    0.4505,    0.1067,    0.4314,    0.8530,    0.4173,    0.7803,    0.2348,    0.5470,    0.5470],
    [0.7943,    9.0838,    0.9619,    0.9106,    0.6221,    0.0497,    0.3897,    0.3532,    0.2963,    0.7757],
    [0.3112,    0.2290,    9.0046,    0.1818,    0.3510,    0.9027,    0.2417,    0.8212,    0.7447,    0.4868],
    [0.5285,    0.9133,    0.7749,    9.2638,    0.5132,    0.9448,    0.4039,    0.0154,    0.1890,    0.4359],
    [0.1656,    0.1524,    0.8173,    0.1455,    9.4018,    0.4909,    0.0965,    0.0430,    0.6868,    0.4468],
    [0.6020,    0.8258,    0.8687,    0.1361,    0.0760,    9.4893,    0.1320,    0.1690,    0.1835,    0.3063],
    [0.2630,    0.5383,    0.0844,    0.8693,    0.2399,    0.3377,    9.9421,    0.6491,    0.3685,    0.5085],
    [0.6541,    0.9961,    0.3998,    0.5797,    0.1233,    0.9001,    0.9561,    9.7317,    0.6256,    0.5108],
    [0.6892,    0.0782,    0.2599,    0.5499,    0.1839,    0.3692,    0.5752,    0.6477,    9.7802,    0.8176],
    [10.0000,    0.4427,    0.8001,    0.1450,    0.2400,    0.1112,    0.0598,    0.4509,   0.0811,   20.0000] ]
b = [1,1,1,1,1,1,1,1,1,1]
crout(A,b)