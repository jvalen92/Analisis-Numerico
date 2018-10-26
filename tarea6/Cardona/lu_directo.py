import numpy as np
class LU_Directo:
    def __init__(self):
        pass
    def cholesky(self, A):
        n = len(A)
        L = np.zeros((n, n))
        U = np.zeros((n, n))
        for k in range(0, n):
            suma1 = 0.0
            for p in range(0, k):
                suma1 += L[k][p]*U[p][k]
            L[k][k] = (A[k][k]-suma1)**0.5
            U[k][k] = L[k][k]
            for i in range(k,n):
                suma2 = 0.0
                for p in range(k):
                    suma2 += L[i][p]*U[p][k]
                L[i][k] = (A[i][k]-suma2)/U[k][k]
            for j in range(k+1,n):
                suma3 = 0
                for p in range(k):
                    suma3+= L[k][p]*U[p][j]
                U[k][j]= (A[k][j]-suma3)/L[k][k]
        return L, U
