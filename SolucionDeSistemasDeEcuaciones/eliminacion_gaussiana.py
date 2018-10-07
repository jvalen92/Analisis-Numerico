import numpy as np
import matriz
class EliminacionGaussiana:
    def __init__(self):
        self._marcas = np.array([])
    def marcas(self):
        return self._marcas
    def escalonar(self, __A, __b, pivot = 0, l=False, u=False, b_a=False, aug=False):
        """
        if pivot = 0 no realiza pivoteo
        if pivot = 1 realiza pivoteo parcial
        if pivot = 2 realiza pivoteo total
        Retorna la matriz escalonada.
        if l = True retorna la matriz inferior.
        if u = True retorna la matriz superior.
        if aug = True devuelve la matriz aumentada Ub
        if b = True devuelve b
        """
        if pivot not in [0, 1, 2]:
            raise "Debe ingresar un pivoteo valido."
        if pivot == 2:
            self._marcas = np.arange(len(__A))
        _A = np.array(__A, dtype=float)
        _b = np.array(__b, dtype=float)
        _shape = _A.shape
        n = _shape[0]
        if _shape[0] != _shape[1]:
            raise "La matriz debe ser cuadrada"
        if matriz.MatrizUtilities().det(_A) == 0:
            raise "El sistema no tiene solucion."
        A = _A
        b = _b
        inferior = np.array([])
        for k in range(0,n-1):
            if A[k][k] == 0: #Evitar division por cero al encontrar el multuplicador
                j = k+1
                while j<n and A[j][k] == 0: #Buscar un fila donde el valor en la columna K sea difernte de 0
                    j= j+1
                if pivot == 0:
                    if j<n: #Sumarle a la fila K otra donde quite el cero de la columna K
                        for p in range(k,n):
                            A[k][p] = A[k][p] + A[j][p]
                        b[k] = b[k] + b[j] 
                    else: #Si no hay valor que quite el 0 de la fila K significa que esta escalonada
                        continue
                if pivot == 1:
                    A = GaussUtilities().pivoteo_parcial(A, b, n, k)
                if pivot == 2:
                    A = GaussUtilities().pivoteo_total(A, b, self._marcas, n, k)
            for i in range(k+1,n): #Si es cero significa que no se requiere realizar ninguna operacion en la fila
                if A[i][k] != 0:
                    multiplicador = A[i][k]/A[k][k]
                    for p in range(k,n):
                        A[i][p] = A[i][p] - multiplicador * A[k][p]
                    b[i] = b[i] - multiplicador*b[k] 
                    if l:
                        inferior = np.append(inferior, multiplicador)
        gauss_utilities = GaussUtilities()
        if (l and u):
            L = gauss_utilities.obtener_L(inferior, n)           
            if aug:
                A = np.insert(A, A.shape[1], b, axis=1)
                return (L, A)
            elif b_a:
                L = gauss_utilities.obtener_L(inferior, n)
                return (L, A, b)
            else:
                L = gauss_utilities.obtener_L(inferior, n)
                return (L, A)
        if l:
            L = gauss_utilities.obtener_L(inferior, n)
            if b_a:
                return (L, b)
            return L
        if b_a:
            return (A, b)
        return A
class GaussUtilities:
    def __init__(self):
        pass
    def solucionar_regr(self, A, b,marcs=None):
        """
        Retorna x, en la solucion de Ax=b,
        usando sustitucion regresiva.
        if marcs != None encuentra la solucion
        teniendo en cuenta los cambios de columna.
        """
        n = len(A)-1
        x = np.zeros(n+1,dtype=float)
        x[n] = b[n]/A[n][n]
        for i in range(n-1,-1,-1):
            sumatoria = 0
            for p in range(i+1,n+1):
                sumatoria = sumatoria + A[i][p]*x[p]
            x[i] = (b[i] - sumatoria)/A[i][i]
        if type(marcs) != type(None):
            marcas = np.zeros(len(A))
            for i in range(0, len(A)):
                marcas[marcs[i]] = x[i]
            return marcas
        return x
    def solucionar_prog(self, A, b):
        """
        Retorna x, en la solucion de Ax=b,
        usando sustitucion progresiva.
        """
        n = len(A)-1
        x = np.zeros(n+1,dtype=float)
        x[0] = b[0]/A[0][0]
        for i in range(1, n + 1):
            sumatoria = 0
            for p in range(0,i):
                sumatoria = sumatoria + A[i][p]*x[p]
            x[i] = (b[i] - sumatoria)/A[i][i]
        return x
    def pivoteo_parcial(self, A, b, n, k):
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
                temp = A[filaMayor]
                A[filaMayor] = temp1
                A[k] = A[filaMayor]
                temp = b[filaMayor]
                b[filaMayor] = b[k]
                b[k] = b[filaMayor]
            return (A, b)
    def pivoteo_total(self, A, b, marcas, n, k):
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
            temp = 0
            if filaMayor != k:
                temp = A[k]
                A[k] = A[filaMayor]
                A[filaMayor] = temp
                temp = b[k]
                b[k] = b[filaMayor]
                b[filaMayor] = temp
            if columnaMayor != k:
                temp = A[:, columnaMayor]
                A[:,columnaMayor] = A[:, k]
                A[:, k] = temp
                temp = b[:, columnaMayor]
                b[:, columnaMayor] = b[:, k]
                b[:, k] = temp
                temp = marcas[k]
                marcas[k] = marcas[columnaMayor]
                marcas[columnaMayor] = temp
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
    def obtener_L(self, v, n):
        """
        Devuelve la matriz L en A = LU, dado
        un array de multiplicadores v.
        """
        if len(v) == 0:
            return None
        _v = v
        L = np.zeros((n, n))
        k = n-1
        for i in range(0, n):
            L[i][i] = 1
            L[i+1:, i] = _v[0:k]
            _v = _v[k:]
            k -= 1
        return L