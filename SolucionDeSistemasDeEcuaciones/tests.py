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
        print(x,U,b_a)
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
        print(x,U,b_a)
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
        print(x,U,b_a)
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
        print(x,z,L,U,b)
        return x


if __name__== "__main__":
    A = [[-1,0,0,4,-1,0],[0,-1,0,-1,4,-1],[4,-1,0,-1,0,0],[-1,5,-1,0,-1,0],[0,-1,5,0,0,-1],[0,0,-1,0,-1,6]]
    b = [61,14,8,5,9,23]
    test = SistemaDeEcuaciones()
    #x1 = test.eliminacion_gaussiana_simple(A, b)
    #x2 = test.eliminacion_gaussiana_pivoteo_parcial(A, b)
    #x3 = test.eliminacion_gaussiana_pivoteo_total(A, b)
    #x4 = test.factorizacion_LU_gauss_simple(A, b)
    #x5 = test.factorizacion_LU_gauss_pivoteo_parcial(A, b)
    # Testing Ax=B
    """print(np.allclose(x1, x2))
    print(np.allclose(x2, x3))
    print(np.allclose(x3, x4))
    print(x5)
    #Testing det
    print(np.allclose(np.linalg.det(A), matriz.MatrizUtilities().det(A)))"""