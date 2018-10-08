import eliminacion_gaussiana
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

if __name__== "__main__":
    A = [[2, -3, 4, 1], [-4, 2, 1, -2], [1, 3, -5, 3], [-3, 1, 1, -1]]
    b = [10, -10, 32, -21]
    test = SistemaDeEcuaciones()
    x1 = test.eliminacion_gaussiana_simple(A, b)
    x2 = test.eliminacion_gaussiana_pivoteo_parcial(A, b)
    x3 = test.eliminacion_gaussiana_pivoteo_total(A, b)
    x4 = test.factorizacion_LU_gauss_simple(A, b)
    # Testing Ax=B
    print(np.allclose(x1, x2))
    print(np.allclose(x2, x3))
    print(np.allclose(x3, x4))
    #Testing det
    print(np.allclose(np.linalg.det(A), matriz.MatrizUtilities().det(A)))
