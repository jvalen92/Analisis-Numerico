#Puntos = [(x1,y1),(x2,y2)]
import numpy as np
def Vandermonde(puntos):
    A = []
    b = []
    auxA = []
    n = len(puntos)
    for punto in puntos:
        b.append(punto[1])
        for i in range(1,n+1):
            auxA.append(punto[0]**(n-i))
        A.append(auxA)
        auxA = []
    a = np.linalg.solve(A,b)
    polinomio = str(a[0]) + "x^" + str((n-1))
    for i in range(1,n):
        polinomio += "+" if (a[0]>0) else "" 
        polinomio += str(a[0]) + "x^" + str((n-(1+i)))
    return polinomio

puntos = [(0,0),(1,1),(2,1),(3,0)]
polinomio = Vandermonde(puntos)
print(polinomio)