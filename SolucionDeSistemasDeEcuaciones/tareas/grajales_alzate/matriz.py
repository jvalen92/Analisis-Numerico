import numpy as np
class MatrizUtilities:
    # Libreria para realizar calculos cientificos eficientes.
    def __init__(self):
        pass
    def get_matriz_aumentada(self, A, b):
        """
        Retorna la matriz aumentada Ab.
        """
        _b = np.array(b, dtype=float)
        _A = np.array(A, dtype=float)
        _Ab = np.insert(_A, _A.shape[1], _b, axis=1)
        return _Ab    
    def det(self, A):
        """
        Retorna el determinante de la matriz A.
        """
        _A = np.array(A, dtype=float)
        if _A.shape[0] != _A.shape[1]:
            raise "La matriz debe ser cuadrada."
        _det = self.__det(_A, _A.shape[0])
        return _det
    def __det(self, A, n):
        if n == 1:
            return A[0][0]
        resultado = 0.0
        for i in range(0, n):
            eliminar_columna = [c for c in range(0, n) if c != i]
            resultado += (-1)**i * A[0][i] * self.__det(A[1:, eliminar_columna], n - 1)
        return resultado