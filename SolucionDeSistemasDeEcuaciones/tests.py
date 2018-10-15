import eliminacion_gaussiana
import lu_directo
import matriz
import numpy as np
class SistemaDeEcuaciones:
    def __init__(self):
        pass
    def eliminacion_gaussiana_simple(self, A, b):
        """
        Reliza eliminacion gaussiana simple,
        dado A y b
        """
        eg = eliminacion_gaussiana.EliminacionGaussiana()
        eu = eliminacion_gaussiana.GaussUtilities()
        (U, b_a) = eg.escalonar(A, b, u=True, b_a=True)
        x = eu.solucionar_regr(U, b_a)
        return x
    def eliminacion_gaussiana_pivoteo_parcial(self, A, b):
        """
        Reliza eliminacion gaussiana con pivoteo parcial,
        dado A y b
        """
        eg = eliminacion_gaussiana.EliminacionGaussiana()
        eu = eliminacion_gaussiana.GaussUtilities()
        (U, b_a) = eg.escalonar(A, b, pivot=1, u=True, b_a=True)
        x = eu.solucionar_regr(U, b_a)
        return x
    def eliminacion_gaussiana_pivoteo_total(self, A, b):
        """
        Reliza eliminacion gaussiana con pivoteo total,
        dado A y b
        """
        eg = eliminacion_gaussiana.EliminacionGaussiana()
        eu = eliminacion_gaussiana.GaussUtilities()
        (U, b_a) = eg.escalonar(A, b, pivot=2, u=True, b_a=True)
        x = eu.solucionar_regr(U, b_a, marcs=eg.marcas())
        return x
    def factorizacion_LU_gauss_simple(self, A, b):
        """
        Reliza la factorizacion A = LU,
        Lb = z, Ux = b y devuelve x,
        dado A y b
        """
        eg = eliminacion_gaussiana.EliminacionGaussiana()
        eu = eliminacion_gaussiana.GaussUtilities()
        (L, U) = eg.escalonar(A, b, pivot=0, l=True, u=True)
        z = eu.solucionar_prog(L, b)
        x = eu.solucionar_regr(U, z)
        return x
<<<<<<< HEAD
    
    def factorizacion_LU_cholesky(self, A, b):
        """
        Realiza la factorizacion A = LU,
        Lb = z, Ux = b y devuelve x,
        dado A y b.
        """

        directo = lu_directo.LU_Directo()
        eu = eliminacion_gaussiana.GaussUtilities()
        (L, U) = directo.cholesky(A)
        print(L)
        print(U)
        z = eu.solucionar_prog(L, b)
        x = eu.solucionar_regr(U, z)
        return x

if __name__== "__main__":
    #A = [[2, -3, 4, 1], [-4, 2, 1, -2], [1, 3, -5, 3], [-3, 1, 1, -1]]
    b = [10, -10, 32, -21]
    A = [[8, 2, 2, 5], [4, 5, 7, -8], [-4, 7, 12, 11], [8, -3, -11, 28]]

=======
>>>>>>> ff2edd0... Corrigiendo implementacion en tests.py
    def factorizacion_LU_gauss_pivoteo_parcial(self, A, b):
        """
        Reliza la factorizacion A = LU,
        Lb = z, Ux = b y devuelve x,
        dado A y b
        """
        eg = eliminacion_gaussiana.EliminacionGaussiana()
        eu = eliminacion_gaussiana.GaussUtilities()
        (L, U) = eg.escalonar(A, b, pivot=1, l=True, u=True)
        z = eu.solucionar_prog(L, b)
        x = eu.solucionar_regr(U, z)
        return x
    def factorizacion_LU_cholesky(self, A, b):
        directo = lu_directo.LU_Directo()
        eu = eliminacion_gaussiana.GaussUtilities()
        (L, U) = directo.cholesky(A)
        z = eu.solucionar_prog(L, b)
        x = eu.solucionar_regr(U, z)
        return x

if __name__== "__main__":
     #A = [[2, -3, 4, 1], [-4, 2, 1, -2], [1, 3, -5, 3], [-3, 1, 1, -1]]
    b = [10, -10, 32, -21]
    A = [[8, 2, 2, 5], [4, 5, 7, -8], [-4, 7, 12, 11], [8, -3, -11, 28]]
    test = SistemaDeEcuaciones()
    x1 = test.eliminacion_gaussiana_simple(A, b)
    x2 = test.eliminacion_gaussiana_pivoteo_parcial(A, b)
    x3 = test.eliminacion_gaussiana_pivoteo_total(A, b)
    x4 = test.factorizacion_LU_gauss_simple(A, b)
    x5 = test.factorizacion_LU_cholesky(A, b)
    x6 = test.factorizacion_LU_gauss_pivoteo_parcial(A, b)
    # Testing Ax=B
    print(x1)
    print(x2)
    print(x3)
    print(x4)
    print(x5)
    print(x6)
    print(np.allclose(x1, x2))
    print(np.allclose(x2, x3))
    print(np.allclose(x3, x4))
    print(np.allclose(x4, x5))
    print(np.allclose(x5, x6))
    #Testing det
    print(np.allclose(np.linalg.det(A), matriz.MatrizUtilities().det(A)))