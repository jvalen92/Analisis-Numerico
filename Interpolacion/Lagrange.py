# Interpolacion de Lagrange
# Polinomio en forma simbólica
import numpy as np
import sympy as sym

# INGRESO , Datos de prueba
xi = [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]
fi = [0.5949 , 0.2622 , 0.6028 ,  0.7112 , 0.2217 , 0.1174 , 0.2967 , 0.3188 , 0.4242 , 0.5079]

# PROCEDIMIENTO
n = len(xi)
x = sym.Symbol('x')
# Polinomio
polinomio = 0
for i in range(0,n,1):
    # Termino de Lagrange
    termino = 1
    for j  in range(0,n,1):
        if (j!=i):
            termino = termino*(x-xi[j])/(xi[i]-xi[j])
    polinomio = polinomio + termino*fi[i]
# Expande el polinomio
px = polinomio.expand()
# para evaluacion numérica
pxn = sym.lambdify(x,polinomio)

# Salida
print('Polinomio de Lagrange, expresiones')
print(polinomio)
print()
print('Polinomio de Lagrange: ')
print(px)

