import numpy as np
import sympy as sp

def trazalineal(xi,fi):
    n = len(xi)
    x = sp.Symbol('x')
    polinomio = []
    tramo=1
    while not(tramo>=n):
        m =(fi[tramo]-fi[tramo-1])/(xi[tramo]-xi[tramo-1])
        inicio = fi[tramo-1]-m*xi[tramo-1]
        ptramo = inicio + m*x
        polinomio.append(ptramo)
        tramo = tramo + 1
    return(polinomio)

# PROGRAMA
# INGRESO , Datos de prueba
xi =[
    1.0000,    
    2.0000,    
    3.0000,    
    4.0000,    
    5.0000,    
    6.0000,    
    7.0000,    
    8.0000,    
    9.0000,    
   10.0000 ]
fi = [
    0.5949,
    0.2622,
    0.6028,
    0.7112,
    0.2217,
    0.1174,
    0.2967,
    0.3188,
    0.4242,
    0.5079]

resolucion = 10 # entre cada par de puntos

# PROCEDIMIENTO
n = len(xi)
# Obtiene los polinomios por tramos
polinomio = trazalineal(xi,fi)

# SALIDA
print('Polinomios por tramos: ')
for tramo in range(1,n,1):
    print(' x = ['+str(xi[tramo-1])
          +','+str(xi[tramo])+']')
    print(str(polinomio[tramo-1]))