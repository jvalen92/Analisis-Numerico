import eliminacion_gaussiana
def factorizacion_LU_gauss_simple(A, b):
    """
    Reliza la factorizacion A = LU,
    Lb = z, Ux = b y devuelve x,
    dado A y b
    """
    eg = eliminacion_gaussiana.EliminacionGaussiana()
    eu = eliminacion_gaussiana.GaussUtilities()
    (L, U) = eg.escalonar(A, b, pivot=0, l=True, u=True)
    print("L= ",L)
    print("U= ",U)
    z = eu.solucionar_prog(L, b)
    x = eu.solucionar_regr(U, z)
    return x