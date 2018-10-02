import Escalonamiento 
import numpy as np

def despeje(A,b):
    (A,b) = Escalonamiento.escalonamiento(A,b) #Escalonar A
    n = len(A)-1
    x = np.zeros(n+1,float)
    x[n] = b[n]/A[n][n]
    for i in range(n-1,-1,-1):
        sumatoria = 0
        for p in range(i+1,n+1):
            sumatoria = sumatoria + A[i][p]*x[p]
        x[i] = (b[i] - sumatoria)/A[i][i]
    return x

"""n = int(input(" Ingrese el tamaño deseado: "))
A = np.zeros((n,n),np.float64)
b = np.zeros(n,np.float64)

for i in range(n):
    for j in range(n):
        A[i][j] = np.float64(input("Ingrese coeficiente de la matriz A en la fila "+str(i+1)+" y columna "+str(j+1)+": ")) 

for i in range(n):
    b[i]= np.float64(input("Ingrese el valor del termino independiente en la casilla "+str(i+1)+": "))     
"""
#A = [[14,6,-2,3],[3,15,2,-5],[-7,4,-23,2],[1,-3,-2,16]]
#b = [12,32,-24,14]
A = [[2, -1, 0, 3],
     [1, 0.5, 3, 8],
     [0, 13, -2, 11],
      [14, 5, -2, 3]]
b = [1, 1, 1, 1]
#print(despeje(A,b))    
